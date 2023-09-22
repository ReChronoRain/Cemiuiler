package com.sevtinge.cemiuiler.module.hook.powerkeeper;

import com.sevtinge.cemiuiler.module.base.BaseHook;

public class PreventBatteryWitelist extends BaseHook {
    @Override
    public void init() {
        Helpers.hookAllMethods("com.miui.powerkeeper.utils.CommonAdapter", lpparam.getClassLoader(), "addPowerSaveWhitelistApps", HookerClassHelper.DO_NOTHING);
    }
}
