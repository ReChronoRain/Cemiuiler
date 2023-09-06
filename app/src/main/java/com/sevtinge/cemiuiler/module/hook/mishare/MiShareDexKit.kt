package com.sevtinge.cemiuiler.module.hook.mishare

import com.sevtinge.cemiuiler.module.base.BaseHook


class MiShareDexKit : BaseHook() {
    override fun init() {
       /* System.loadLibrary("dexkit")
        initDexKit(lpparam)
        try {
            mMiShareResultMethodsMap = dexKitBridge.batchFindMethodsUsingStrings {
                addQuery("qwq", setOf("EnabledState", "mishare_enabled"))
                matchType = MatchType.FULL
            }
            mMiShareResultClassMap = dexKitBridge.batchFindClassesUsingStrings {
                addQuery("qwq2", setOf("null context", "cta_agree"))
                matchType = MatchType.FULL
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        closeDexKit()
    }

    companion object {
        var mMiShareResultMethodsMap: Map<String, List<DexMethodDescriptor>>? = null
        var mMiShareResultClassMap: Map<String, List<DexClassDescriptor>>? = null*/
    }
}
