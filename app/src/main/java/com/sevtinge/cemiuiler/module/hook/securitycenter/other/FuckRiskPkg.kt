package com.sevtinge.cemiuiler.module.hook.securitycenter.other

import android.annotation.SuppressLint
import com.github.kyuubiran.ezxhelper.EzXHelper
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHooks
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.module.hook.securitycenter.SecurityCenterDexKit
import com.sevtinge.cemiuiler.utils.DexKit.addUsingStringsEquals
import com.sevtinge.cemiuiler.utils.DexKit.dexKitBridge
import com.sevtinge.cemiuiler.utils.Helpers.getPackageVersionCode

object FuckRiskPkg : BaseHook() {
    private val pkg by lazy {
        dexKitBridge.findMethod {
            matcher {
                addUsingStringsEquals(
                    "riskPkgList", "key_virus_pkg_list", "show_virus_notification"
                )
            }
        }.map { it.getMethodInstance(EzXHelper.classLoader) }.toList()
    }

    override fun init() {
        pkg.createHooks {
            before { param ->
                param.result = null
            }
        }
    }
}
