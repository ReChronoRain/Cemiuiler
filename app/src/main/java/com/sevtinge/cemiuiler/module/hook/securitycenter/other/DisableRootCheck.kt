package com.sevtinge.cemiuiler.module.hook.securitycenter.other

import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.module.hook.securitycenter.SecurityCenterDexKit
import com.sevtinge.cemiuiler.utils.DexKit.addUsingStringsEquals
import com.sevtinge.cemiuiler.utils.DexKit.dexKitBridge
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedBridge
import java.lang.reflect.Method
import java.util.Objects

object DisableRootCheck : BaseHook() {
    override fun init() {
        dexKitBridge.findMethod {
            matcher {
                addUsingStringsEquals("key_check_item_root")
                returnType = "boolean"
            }
        }.forEach {
            it.getMethodInstance(lpparam.classLoader).createHook {
                returnConstant(false)
            }
        }

        /*try {
            val result: List<DexMethodDescriptor> =
                Objects.requireNonNull<List<DexMethodDescriptor>>(
                    SecurityCenterDexKit.mSecurityCenterResultMap["rootCheck"]
                )
            for (descriptor in result) {
                val checkIsRooted: Method = descriptor.getMethodInstance(lpparam.classLoader)
                if (checkIsRooted.returnType == Boolean::class.javaPrimitiveType) {
                    XposedBridge.hookMethod(
                        checkIsRooted,
                        XC_MethodReplacement.returnConstant(false)
                    )
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }*/
    }
}
