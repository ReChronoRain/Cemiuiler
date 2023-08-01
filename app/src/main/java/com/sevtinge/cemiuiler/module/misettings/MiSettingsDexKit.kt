package com.sevtinge.cemiuiler.module.misettings

import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.DexKit.closeDexKit
import com.sevtinge.cemiuiler.utils.DexKit.initDexKit
import com.sevtinge.cemiuiler.utils.DexKit.safeDexKitBridge
import io.luckypray.dexkit.descriptor.member.DexClassDescriptor
import io.luckypray.dexkit.descriptor.member.DexMethodDescriptor
import io.luckypray.dexkit.enums.MatchType

class MiSettingsDexKit : BaseHook() {
    override fun init() {
        System.loadLibrary("dexkit")
        initDexKit(lpparam)
        try {
            mMiSettingsResultMethodsMap = safeDexKitBridge.batchFindMethodsUsingStrings {
                addQuery("category", setOf("btn_preferce_category"))
                matchType = MatchType.FULL
            }
            mMiSettingsResultClassMap = safeDexKitBridge.batchFindClassesUsingStrings {
                addQuery("refresh", setOf("The current device does not support refresh rate adjustment"))
                matchType = MatchType.FULL
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        closeDexKit()
    }

    companion object {
        var mMiSettingsResultMethodsMap: Map<String, List<DexMethodDescriptor>>? = null
        var mMiSettingsResultClassMap: Map<String, List<DexClassDescriptor>>? = null
    }
}
