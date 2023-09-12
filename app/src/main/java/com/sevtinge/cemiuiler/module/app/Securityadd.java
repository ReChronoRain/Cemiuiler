package com.sevtinge.cemiuiler.module.app;

import com.sevtinge.cemiuiler.module.base.BaseModule;
import com.sevtinge.cemiuiler.module.hook.securityadd.XspaceAppStartActivity;

public class Securityadd extends BaseModule {
    @Override
    public void handleLoadPackage() {
        initHook(new XspaceAppStartActivity(), mPrefsMap.getBoolean("xspaceappstartactivity"));
    }
}
