package com.sevtinge.cemiuiler.module.hook.powerkeeper

import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.DexKit.closeDexKit
import com.sevtinge.cemiuiler.utils.DexKit.initDexKit
import com.sevtinge.cemiuiler.utils.DexKit.dexKitBridge

class PowerKeeperDexKit : BaseHook() {
    override fun init() {
        /*System.loadLibrary("dexkit")
        initDexKit(lpparam)
        try {
            mPowerKeeperResultMethodsMap = dexKitBridge.batchFindMethodsUsingStrings {
                addQuery("fucSwitch", setOf("custom_mode_switch", "fucSwitch"))
                matchType = MatchType.FULL
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        closeDexKit()
    }

    companion object {
        var mPowerKeeperResultMethodsMap: Map<String, List<DexMethodDescriptor>>? = null*/
    }
}
