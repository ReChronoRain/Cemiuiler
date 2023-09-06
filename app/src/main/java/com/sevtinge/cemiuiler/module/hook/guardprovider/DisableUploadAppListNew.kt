package com.sevtinge.cemiuiler.module.hook.guardprovider

import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.DexKit
import com.sevtinge.cemiuiler.utils.DexKit.dexKitBridge
import com.sevtinge.cemiuiler.utils.Helpers
import com.sevtinge.cemiuiler.utils.replaceMethod
import org.luckypray.dexkit.query.enums.StringMatchType
import java.lang.reflect.Method

class DisableUploadAppListNew : BaseHook() {

    @Throws(NoSuchMethodException::class)
    override fun init() {
        /*val antiDefraudAppManager = mGuardProviderResultMethodsMap["AntiDefraudAppManager"]!!
        assert(antiDefraudAppManager.size == 1)
        val antiDefraudAppManagerDescriptor = antiDefraudAppManager.first()
        val antiDefraudAppManagerMethod: Method = antiDefraudAppManagerDescriptor.getMethodInstance(lpparam.classLoader)
        antiDefraudAppManagerMethod.replaceMethod {
            return@replaceMethod null
        }*/
        dexKitBridge.findMethod {
            matcher {
                usingStrings = listOf("AntiDefraudAppManager", "https://flash.sec.miui.com/detect/app")
                StringMatchType.Equals
            }
        }.forEach {
            it.getMethodInstance(lpparam.classLoader).replaceMethod {
                return@replaceMethod null
            }
        }
    }
}
