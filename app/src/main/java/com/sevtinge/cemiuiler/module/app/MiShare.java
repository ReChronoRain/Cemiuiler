package com.sevtinge.cemiuiler.module.app;

import com.sevtinge.cemiuiler.module.base.BaseModule;
import com.sevtinge.cemiuiler.module.hook.mishare.NoAutoTurnOff;

public class MiShare extends BaseModule {

    @Override
    public void handleLoadPackage() {
        initHook(NoAutoTurnOff.INSTANCE, mPrefsMap.getBoolean("disable_mishare_auto_off"));
    }
}


