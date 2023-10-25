package com.sevtinge.cemiuiler.module.hook.home.gesture;

import com.sevtinge.cemiuiler.module.base.BaseHook;

public class QuickBack extends BaseHook {
    @Override
    public void init() {
        findAndHookMethod("com.miui.home.recents.GestureStubView",
            "isDisableQuickSwitch", new MethodHook() {
                @Override
                protected void before(MethodHookParam param) {
                    param.setResult(false);
                }
            }
        );
    }
}
