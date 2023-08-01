package com.sevtinge.cemiuiler.ui.fragment.home;

import static com.sevtinge.cemiuiler.utils.devicesdk.SystemSDKKt.isAndroidR;

import android.view.View;

import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.base.BaseSettingsActivity;
import com.sevtinge.cemiuiler.ui.fragment.base.SettingsPreferenceFragment;

import moralnorm.preference.SwitchPreference;

public class HomeDrawerSettings extends SettingsPreferenceFragment {

    SwitchPreference mAllAppsContainerViewBlur;

    @Override
    public int getContentResId() {
        return R.xml.home_drawer;
    }

    @Override
    public View.OnClickListener addRestartListener() {
        return view -> ((BaseSettingsActivity)getActivity()).showRestartDialog(
            getResources().getString(R.string.home),
            "com.miui.home"
        );
    }

    @Override
    public void initPrefs() {
        mAllAppsContainerViewBlur = findPreference("prefs_key_home_drawer_blur");
        mAllAppsContainerViewBlur.setVisible(!isAndroidR());

        mAllAppsContainerViewBlur.setOnPreferenceChangeListener((preference, o) -> true);
    }
}
