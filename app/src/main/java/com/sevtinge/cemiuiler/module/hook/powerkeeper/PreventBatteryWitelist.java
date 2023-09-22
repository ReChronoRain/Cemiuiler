package com.sevtinge.cemiuiler.module.hook.powerkeeper;

import com.sevtinge.cemiuiler.module.base.BaseHook;
import io.github.libxposed.api.XposedInterface;

public class PreventBatteryWitelist extends BaseHook {
    @Override
    public void init() {
        Helpers.hookAllMethods("com.miui.powerkeeper.utils.CommonAdapter", lpparam.getClassLoader(), "addPowerSaveWhitelistApps", new MethodHook(20000) {
            @Override
            protected void before(MethodHookParam param) throws Throwable {
                param.setResult(null);
            }
        });
    }
}
