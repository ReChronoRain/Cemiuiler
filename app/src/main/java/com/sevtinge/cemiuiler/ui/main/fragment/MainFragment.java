package com.sevtinge.cemiuiler.ui.main.fragment;

import android.os.Build;
import android.os.Bundle;

import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.base.SubFragment;

import com.sevtinge.cemiuiler.utils.SdkHelper;
import moralnorm.os.SdkVersion;
import moralnorm.preference.Preference;
import moralnorm.preference.PreferenceCategory;

import static com.sevtinge.cemiuiler.module.base.BaseHook.mPrefsMap;

public class MainFragment extends SubFragment {

    Preference mPowerSetting;
    Preference mFoolsA;
    Preference mFoolsB;

    PreferenceCategory mFools;
    PreferenceCategory mModuleDo;
    PreferenceCategory mModuleSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentResId() {
        return R.xml.prefs_main;
    }

    @Override
    public void initPrefs() {
        mPowerSetting = findPreference("prefs_key_powerkeeper");
        mPowerSetting.setVisible(SdkHelper.isAndroidTiramisu());
        if (!getSharedPreferences().getBoolean("prefs_key_fools_seen_b", false)) {
            mFools = findPreference("prefs_key_fools");
            if (getSharedPreferences().getBoolean("prefs_key_fools_enable_a", false) || getSharedPreferences().getString("prefs_key_fools_enable_b", "").equals("71Z9")) {
                mFools.setVisible(true);
            }
            mFoolsA = findPreference("prefs_key_fools_a");
            if (getSharedPreferences().getBoolean("prefs_key_fools_enable_a", false)) {
                mFoolsA.setVisible(true);
            }
            mFoolsB = findPreference("prefs_key_fools_b");
            mModuleDo = findPreference("prefs_key_module_do");
            mModuleSettings = findPreference("prefs_key_module_settings");
            if (getSharedPreferences().getString("prefs_key_fools_enable_b", "").equals("71Z9")) {
                mFoolsA.setVisible(false);
                mFoolsB.setVisible(true);
                mModuleDo.setVisible(false);
                mModuleSettings.setVisible(false);
            }
        } else {
            mFoolsA = findPreference("prefs_key_fools_a");
            mFoolsB = findPreference("prefs_key_fools_b");
            mModuleDo = findPreference("prefs_key_module_do");
            mModuleSettings = findPreference("prefs_key_module_settings");
            mFoolsA.setVisible(false);
            mFoolsB.setVisible(false);
            mModuleDo.setVisible(true);
            mModuleSettings.setVisible(true);
        }
    }
}
