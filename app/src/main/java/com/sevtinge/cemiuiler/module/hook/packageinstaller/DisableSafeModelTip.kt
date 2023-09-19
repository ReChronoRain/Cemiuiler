package com.sevtinge.cemiuiler.module.hook.packageinstaller

import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.DexKit.addUsingStringsEquals
import com.sevtinge.cemiuiler.utils.DexKit.dexKitBridge
import com.sevtinge.cemiuiler.utils.findClassOrNull
import com.sevtinge.cemiuiler.utils.setBooleanField

object DisableSafeModelTip : BaseHook() {
    override fun init() {
        /*val result = Objects.requireNonNull(
            mPackageInstallerResultMethodsMap!!["DisableSafeModelTip"]
        )
        for (descriptor in result) {
            val mDisableSafeModelTip = descriptor.getMethodInstance(lpparam.classLoader)
            mDisableSafeModelTip.createHook {
                returnConstant(false)
            }

        }*/

        dexKitBridge.findMethod {
            matcher {
                addUsingStringsEquals("android.provider.MiuiSettings\$Ad")
            }
        }.forEach {
            it.getMethodInstance(lpparam.classLoader).createHook {
                returnConstant(false)
            }
        }

        var letter = 'a'
        for (i in 0..25) {
            try {
                val classIfExists =
                    "com.miui.packageInstaller.ui.listcomponets.${letter}0".findClassOrNull()
                classIfExists?.let {
                    it.methodFinder().filterByName("a").first().createHook {
                        after { hookParam ->
                            try {
                                hookParam.thisObject.setBooleanField("m", false)
                            } catch (t: Throwable) {
                                hookParam.thisObject.setBooleanField("l", false)
                            }
                        }
                    }
                }
            } catch (t: Throwable) {
                letter++
            }
        }
    }
}
