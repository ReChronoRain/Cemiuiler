package com.sevtinge.cemiuiler.module.hook.personalassistant;

import com.sevtinge.cemiuiler.module.base.BaseHook;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class PersonalAssistantDexKit extends BaseHook {

    // public static Map<String, List<DexMethodDescriptor>> mPersonalAssistantResultMethodsMap;

    @Override
    public void init() {
      /*  System.loadLibrary("dexkit");
        String apkPath = lpparam.appInfo.sourceDir;
        DexKitBridge bridge = DexKitBridge.create(apkPath);
        try {
            if (bridge == null) {
                return;
            }
            mPersonalAssistantResultMethodsMap =
                bridge.batchFindMethodsUsingStrings(
                    BatchFindArgs.builder()
                        .addQuery("ScrollStateManager", Set.of("ScrollStateManager", "Manager must be init before using"))
                        .matchType(MatchType.FULL)
                        .build()
                );
        } catch (Throwable e) {
            e.printStackTrace();
        }
        bridge.close();*/
    }
}
