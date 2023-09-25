package com.sevtinge.cemiuiler.ui.fragment;

import static com.sevtinge.cemiuiler.utils.api.VoyagerApisKt.isPad;
import static com.sevtinge.cemiuiler.utils.devicesdk.SystemSDKKt.isAndroidT;
import static com.sevtinge.cemiuiler.utils.devicesdk.SystemSDKKt.isMoreAndroidVersion;
import static com.sevtinge.cemiuiler.utils.devicesdk.SystemSDKKt.isMoreMiuiVersion;

import android.content.Context;

import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.fragment.base.SettingsPreferenceFragment;
import com.sevtinge.cemiuiler.utils.ALPermissionManager;

import moralnorm.appcompat.app.AlertDialog;
import moralnorm.preference.Preference;
import moralnorm.preference.SwitchPreference;

public class VariousFragment extends SettingsPreferenceFragment {

    SwitchPreference mDisableBluetoothRestrict; // 禁用蓝牙临时关闭
    SwitchPreference mDisableDeviceLog; // 关闭访问设备日志确认
    Preference mMipad; // 平板相关功能

    @Override
    public int getContentResId() {
        return R.xml.various;
    }

    @Override
    public void initPrefs() {
        mDisableBluetoothRestrict = findPreference("prefs_key_various_disable_bluetooth_restrict");
        mDisableDeviceLog = findPreference("prefs_key_various_disable_access_device_logs");
        mMipad = findPreference("prefs_key_various_mipad");

        mDisableBluetoothRestrict.setVisible(isMoreMiuiVersion(14f) && isMoreAndroidVersion(31));
        mDisableDeviceLog.setVisible(isAndroidT());
        mMipad.setVisible(isPad());

        mDisableBluetoothRestrict.setOnPreferenceChangeListener((preference, o) -> true);
        mDisableDeviceLog.setOnPreferenceChangeListener((preference, o) -> true);

        SwitchPreference linkTurboToast = findPreference("prefs_key_various_disable_link_turbo_toast");
        linkTurboToast.setOnPreferenceChangeListener((preference, o) -> {
            alertDialog();
            return true;
        });
    }

    public void alertDialog() {
        Context context = getActivity();
        new AlertDialog.Builder(context)
            .setCancelable(false)
            .setTitle(getResources().getString(R.string.soft_reboot) + " " + getResources().getString(R.string.reboot_networkboost))
            .setMessage(getResources().getString(R.string.restart_app_desc, " " + getResources().getString(R.string.reboot_networkboost) + " "))
            .setHapticFeedbackEnabled(true)
            .setPositiveButton(android.R.string.ok, (dialog, which) -> ALPermissionManager.RootCommand("killall com.xiaomi.NetworkBoost"))
            .setNegativeButton(android.R.string.cancel, null)
            .show();
    }

}
