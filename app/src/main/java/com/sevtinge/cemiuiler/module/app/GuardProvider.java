package com.sevtinge.cemiuiler.module.app;

import com.sevtinge.cemiuiler.module.base.BaseModule;
import com.sevtinge.cemiuiler.module.hook.guardprovider.DisableUploadAppListNew;

public class GuardProvider extends BaseModule {
    @Override
    public void handleLoadPackage() {
        initHook(new DisableUploadAppListNew(), mPrefsMap.getBoolean("disable_upload_applist"));
    }
}
