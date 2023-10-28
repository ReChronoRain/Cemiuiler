package com.sevtinge.cemiuiler.ui;

import android.annotation.SuppressLint;
import android.app.backup.BackupManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileObserver;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.data.adapter.ModSearchAdapter;
import com.sevtinge.cemiuiler.provider.SharedPrefsProvider;
import com.sevtinge.cemiuiler.ui.base.SettingsActivity;
import com.sevtinge.cemiuiler.ui.fragment.AboutFragment;
import com.sevtinge.cemiuiler.ui.fragment.MainFragment;
import com.sevtinge.cemiuiler.utils.CtaUtils;
import com.sevtinge.cemiuiler.utils.Helpers;
import com.sevtinge.cemiuiler.utils.PrefsUtils;
import com.sevtinge.cemiuiler.utils.SearchHelper;
import com.sevtinge.cemiuiler.utils.SettingLauncherHelper;
import com.sevtinge.cemiuiler.utils.ShellUtils;
import com.sevtinge.cemiuiler.view.RestartAlertDialog;

import java.util.Set;

import moralnorm.view.SearchActionMode;

public class MainActivity extends SettingsActivity {

    View mFrameContent;
    View mSearchView;
    SearchActionMode mSearchActionMode;
    TextView mSearchInputView;
    RecyclerView mSearchResultView;
    TextWatcher mSearchResultListener;
    ModSearchAdapter mSearchAdapter;
    String lastFilter;
    String mPermission = "Cemiuiler_Permission";
    private final MainFragment mMainFrag = new MainFragment();
    private final int REQUEST_CODE = 2038;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) requestCta();
        new Thread(new Runnable() {
            public void run() {
                SearchHelper.getAllMods(MainActivity.this, savedInstanceState != null);
            }
        }).start();
        initView();
        initData();
        setImmersionMenuEnabled(true);
        setFragment(mMainFrag);
        ShellUtils.execCommand("chmod 0777 " + getPackageCodePath()
                + " ; chmod 0777 " + PrefsUtils.mPrefsFile
                + " ; chown root:root " + PrefsUtils.mPrefsFile,
            true, false);
        // ShellUtils.execCommand("chmod 0777 " + PrefsUtils.mPrefsFile, true, false);
        // ShellUtils.execCommand("chown root:root " + PrefsUtils.mPrefsFile, true, false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        requestCta();
    }

    private void requestCta() {
        if (!CtaUtils.isCtaEnabled(this)) {
            CtaUtils.showCtaDialog(this, REQUEST_CODE);
        }
    }

    private void initView() {
        mFrameContent = findViewById(R.id.frame_content);
        mSearchView = findViewById(R.id.search_view);
        mSearchInputView = findViewById(android.R.id.input);
        mSearchResultView = findViewById(R.id.search_result_view);

        mSearchAdapter = new ModSearchAdapter();
        mSearchInputView.setHint(getResources().getString(R.string.search));
        mSearchResultView.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultView.setAdapter(mSearchAdapter);
        mSearchView.setOnClickListener(v -> startSearchMode());

        mSearchAdapter.setOnItemClickListener((view, ad) -> {
            SettingLauncherHelper.onStartSettingsForArguments(this,
                SubSettings.class,
                ad.fragment,
                null,
                ad.catTitleResId);
        });

        mSearchResultListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                findMod(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                findMod(s.toString());
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.restart) {
            RestartAlertDialog dialog = new RestartAlertDialog(this);
            dialog.setTitle(item.getTitle());
            dialog.show();
        } else if (itemId == R.id.settings) {
            Intent intent = new Intent(this, ModuleSettingsActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.about) {
            SettingLauncherHelper.onStartSettings(this, SubSettings.class, AboutFragment.class, item.getTitle().toString());
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

        SharedPreferences.OnSharedPreferenceChangeListener mPreferenceChangeListener = (sharedPreferences, s) -> {
            Log.i("prefs", "Changed: " + s);
            requestBackup();
            Object val = sharedPreferences.getAll().get(s);
            String path = "";
            if (val instanceof String)
                path = "string/";
            else if (val instanceof Set<?>)
                path = "stringset/";
            else if (val instanceof Integer)
                path = "integer/";
            else if (val instanceof Boolean)
                path = "boolean/";
            getContentResolver().notifyChange(Uri.parse("content://" + SharedPrefsProvider.AUTHORITY + "/" + path + s), null);
            if (!"".equals(path))
                getContentResolver().notifyChange(Uri.parse("content://" + SharedPrefsProvider.AUTHORITY + "/pref/" + path + s), null);
        };

        PrefsUtils.mSharedPreferences.registerOnSharedPreferenceChangeListener(mPreferenceChangeListener);
        Helpers.fixPermissionsAsync(getApplicationContext());

        try {
            FileObserver mFileObserver = new FileObserver(PrefsUtils.getSharedPrefsPath(), FileObserver.CLOSE_WRITE) {
                @Override
                public void onEvent(int event, String path) {
                    Helpers.fixPermissionsAsync(getApplicationContext());
                }
            };
            mFileObserver.startWatching();
        } catch (Throwable t) {
            Log.e("prefs", "Failed to start FileObserver!");
        }
    }

    void findMod(String filter) {
        lastFilter = filter;
        mSearchResultView.setVisibility("".equals(filter) ? View.GONE : View.VISIBLE);
        ModSearchAdapter adapter = (ModSearchAdapter) mSearchResultView.getAdapter();
        if (adapter == null) return;
        adapter.getFilter().filter(filter);
    }

    private void startSearchMode() {
        mFrameContent.setVisibility(View.GONE);
        SearchActionMode startActionMode = (SearchActionMode) startActionMode(new SearchActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                SearchActionMode searchActionMode = (SearchActionMode) actionMode;
                searchActionMode.setAnchorView(mSearchView);
                searchActionMode.setAnimateView(findViewById(android.R.id.list_container));
                searchActionMode.getSearchInput().addTextChangedListener(mSearchResultListener);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                SearchActionMode searchActionMode = (SearchActionMode) actionMode;
                searchActionMode.getSearchInput().removeTextChangedListener(mSearchResultListener);
                exitSearchMode();
                updateData();
            }
        });

        if (startActionMode == null) {
            throw new NullPointerException("null cannot be cast to non-null type moralnorm.appcompat.internal.view.SearchActionMode");
        }
        mSearchActionMode = startActionMode;
    }

    private void exitSearchMode() {
        if (mSearchActionMode != null) {
            mSearchActionMode = null;
        }
        mFrameContent.setVisibility(View.VISIBLE);
    }

    public void requestBackup() {
        new BackupManager(getApplicationContext()).dataChanged();
    }

    private void updateData() {
        mFrameContent.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                finish();
            } else if (resultCode == RESULT_FIRST_USER) {
                CtaUtils.setCtaEnabled(this);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @noinspection deprecation
     */
    private boolean PermissionRecord(boolean iNeedGet) {
        @SuppressLint({"WorldWriteableFiles", "WorldReadableFiles"})
        SharedPreferences sharedPreferences =
            getSharedPreferences(mPermission, MODE_WORLD_WRITEABLE | MODE_WORLD_READABLE);
        if (!iNeedGet) {
            boolean mPermissionRecord = sharedPreferences.getBoolean("mPermissionRecord", false);
        /*
        需要每次更新软件重新弹出界面可以使用下面的方法
        String PackageCodePath = sharedPreferences.getString("mPackageCodePath", "null");
        String Now_PackageCodePath = getPackageCodePath();
        if (!hasExecutedCommand || !PackageCodePath.equals(Now_PackageCodePath)) {
        */
            if (!mPermissionRecord) {
                // 标记得到允许
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("mPermissionRecord", true);
                /*editor.putString("packageCodePath", Now_PackageCodePath);*/
                editor.apply();
            }
        }
        return sharedPreferences.getBoolean("mPermissionRecord", false);
    }
}
