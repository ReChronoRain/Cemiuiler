package com.sevtinge.cemiuiler.module.hook.securityadd;

import android.app.Activity;
import android.os.Bundle;

import com.sevtinge.cemiuiler.module.base.BaseHook;

public class XspaceAppStartActivity extends BaseHook {
    @Override
    public void init() {
        findAndHookMethod("com.miui.securityadd.ui.activity.XSpaceAppStartActivity",
            "onCreate",
            Bundle.class,
            new MethodHook() {
                @Override
                protected void before(MethodHookParam param) {
                    Activity activity = (Activity) param.thisObject;
                    activity.finish();
                }
            }
        );
    }
}
