package com.sevtinge.cemiuiler.module.hook.joyose

import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.DexKit.dexKitBridge
import org.luckypray.dexkit.query.enums.StringMatchType

object DisableCloudControl : BaseHook() {
    override fun init() {
        dexKitBridge.findMethod {
            matcher {
                usingStrings = listOf("job exist, sync local...")
                returnType = "void"
                StringMatchType.Equals
            }
        }.forEach {
            it.getMethodInstance(lpparam.classLoader).createHook {
                returnConstant(null)
            }
        }
    }
}
