package com.sevtinge.cemiuiler.module.app;

import com.sevtinge.cemiuiler.module.base.BaseModule;
import com.sevtinge.cemiuiler.module.hook.joyose.DisableCloudControl;
import com.sevtinge.cemiuiler.module.hook.joyose.EnableGpuTuner;

public class Joyose extends BaseModule {

    @Override
    public void handleLoadPackage() {
        // initHook(new JoyoseDexKit());
        initHook(DisableCloudControl.INSTANCE, mPrefsMap.getBoolean("various_disable_cloud_control"));
        initHook(EnableGpuTuner.INSTANCE, mPrefsMap.getBoolean("joyose_enable_gpu_tuner"));
    }
}
