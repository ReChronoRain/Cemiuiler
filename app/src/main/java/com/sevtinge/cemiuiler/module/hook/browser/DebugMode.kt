package com.sevtinge.cemiuiler.module.hook.browser

import com.github.kyuubiran.ezxhelper.EzXHelper.safeClassLoader
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.DexKit.dexKitBridge
import com.sevtinge.cemiuiler.utils.Helpers.getPackageVersionCode
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedBridge
import org.luckypray.dexkit.query.enums.StringMatchType

class DebugMode : BaseHook() {
    override fun init() {
        var found = false
        try {
           /* val result: List<DexMethodDescriptor> =
                Objects.requireNonNull(mBrowserResultMethodsMap.get("DebugMode"))
            for (descriptor in result) {
                val DebugMode: Method = descriptor.getMethodInstance(lpparam.classLoader)
                if (DebugMode.returnType == Boolean::class.javaPrimitiveType && DebugMode.toString()
                        .contains("getDebugMode")
                ) {
                    log("DebugMode method is $DebugMode")
                    found = true
                    XposedBridge.hookMethod(DebugMode, XC_MethodReplacement.returnConstant(true))
                }
            }
            if (!found) {
                val result1: List<DexMethodDescriptor> =
                    Objects.requireNonNull(mBrowserResultMethodsMap.get("DebugMode1"))
                for (descriptor1 in result1) {
                    val DebugMode1: Method = descriptor1.getMethodInstance(lpparam.classLoader)
                    if (DebugMode1.returnType == Boolean::class.javaPrimitiveType && DebugMode1.toString()
                            .contains("getDebugMode")
                    ) {
                        log("DebugMode1 method is $DebugMode1")
                        found = true
                        XposedBridge.hookMethod(
                            DebugMode1,
                            XC_MethodReplacement.returnConstant(true)
                        )
                    }
                }
            }
            if (!found) {
                val result2: List<DexMethodDescriptor> =
                    Objects.requireNonNull(mBrowserResultMethodsMap.get("DebugMode2"))
                for (descriptor2 in result2) {
                    val DebugMode2: Method = descriptor2.getMethodInstance(lpparam.classLoader)
                    if (DebugMode2.returnType == Boolean::class.javaPrimitiveType && DebugMode2.toString()
                            .contains("getDebugMode")
                    ) {
                        log("DebugMode2 method is $DebugMode2")
                        XposedBridge.hookMethod(
                            DebugMode2,
                            XC_MethodReplacement.returnConstant(true)
                        )
                    }
                }
            }*/

            dexKitBridge.findMethod {
                matcher {
                    usingStrings = listOf("pref_key_debug_mode_new")
                    StringMatchType.Equals
                }
            }.forEach {
                val debugMode = it.getMethodInstance(lpparam.classLoader)
                if (
                    debugMode.returnType == Boolean::class.javaPrimitiveType &&
                    debugMode.toString().contains("getDebugMode")
                ) {
                    log("DebugMode method is $debugMode")
                    found = true
                    XposedBridge.hookMethod(
                        debugMode,
                        XC_MethodReplacement.returnConstant(true)
                    )
                }
            }

            if (!found) {
                dexKitBridge.findMethod {
                    matcher {
                        usingStrings = listOf("pref_key_debug_mode")
                        StringMatchType.Equals
                    }
                }.forEach {
                    val debugMode1 = it.getMethodInstance(safeClassLoader)
                    if (
                        debugMode1.returnType == Boolean::class.javaPrimitiveType &&
                        debugMode1.toString().contains("getDebugMode")
                    ) {
                        log("DebugMode1 method is $debugMode1")
                        found = true
                        XposedBridge.hookMethod(
                            debugMode1,
                            XC_MethodReplacement.returnConstant(true)
                        )
                    }
                }
            }

            if (!found) {
                dexKitBridge.findMethod {
                    matcher {
                        usingStrings = listOf("pref_key_debug_mode_" + getPackageVersionCode(lpparam))
                        StringMatchType.Equals
                    }
                }.forEach {
                    val debugMode2 = it.getMethodInstance(lpparam.classLoader)
                    if (
                        debugMode2.returnType == Boolean::class.javaPrimitiveType &&
                        debugMode2.toString().contains("getDebugMode")
                    ) {
                        log("DebugMode2 method is $debugMode2")
                        found = true
                        XposedBridge.hookMethod(
                            debugMode2,
                            XC_MethodReplacement.returnConstant(true)
                        )
                    }
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
