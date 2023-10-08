package com.sevtinge.cemiuiler.module.app;

import com.sevtinge.cemiuiler.module.base.BaseModule;
import com.sevtinge.cemiuiler.module.hook.voiceassist.UseThirdPartyBrowser;

public class VoiceAssist extends BaseModule {

    @Override
    public void handleLoadPackage() {
        initHook(UseThirdPartyBrowser.INSTANCE, mPrefsMap.getBoolean("content_extension_browser"));
    }
}

