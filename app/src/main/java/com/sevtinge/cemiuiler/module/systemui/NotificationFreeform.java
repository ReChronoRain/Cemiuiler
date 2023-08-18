package com.sevtinge.cemiuiler.module.systemui;

import android.os.Build;

import com.sevtinge.cemiuiler.module.base.BaseHook;

import de.robv.android.xposed.XposedHelpers;

public class NotificationFreeform extends BaseHook {
    @Override
    public void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            findAndHookMethod(findClassIfExists("com.android.systemui.statusbar.notification.row.MiuiExpandableNotificationRow"), "updateMiniWindowBar", new MethodHook() {
                @Override
                protected void after(MethodHookParam param) throws Throwable {
                    super.after(param);
                    XposedHelpers.setObjectField(param.thisObject, "mCanSlide", true);
                }
            });
        } else {
            findAndHookMethod(findClassIfExists("com.android.systemui.statusbar.notification.NotificationSettingsManager"), "canSlide", String.class, new MethodHook() {
                @Override
                protected void after(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });
        }
    }
}
