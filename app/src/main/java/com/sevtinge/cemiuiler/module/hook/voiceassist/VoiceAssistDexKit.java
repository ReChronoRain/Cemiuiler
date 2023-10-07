package com.sevtinge.cemiuiler.module.hook.voiceassist;

import com.sevtinge.cemiuiler.module.base.BaseHook;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class VoiceAssistDexKit extends BaseHook {

/*    public static Map<String, List<DexMethodDescriptor>> mVoiceAssistResultMethodsMap;*/

    @Override
    public void init() {
       /* System.loadLibrary("dexkit");
        String apkPath = lpparam.appInfo.sourceDir;
        DexKitBridge bridge = DexKitBridge.create(apkPath);
        try {
            if (bridge == null) {
                return;
            }
            mVoiceAssistResultMethodsMap =
                bridge.batchFindMethodsUsingStrings(
                    BatchFindArgs.builder()
                        .addQuery("BrowserActivityWithIntent", Set.of("IntentUtils", "permission click No Application can handle your intent"))
                        .matchType(MatchType.CONTAINS)
                        .build()
                );
        } catch (Throwable e) {
            e.printStackTrace();
        }
        bridge.close();*/
    }
}
