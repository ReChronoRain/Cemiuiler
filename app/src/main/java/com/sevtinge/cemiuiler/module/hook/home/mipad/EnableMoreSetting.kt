package com.sevtinge.cemiuiler.module.home.mipad

import android.view.View
import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.getObjectField

class EnableMoreSetting : BaseHook(){
    override fun init() {
        loadClass("com.miui.home.settings.MiuiHomeSettings").methodFinder().first{
            name == "checkDevice"
        }.createHook{
            before(){
                it.result = true
            }
        }

        loadClass("com.miui.home.launcher.DeviceConfig").methodFinder().first{
            name == "needShowCellsEntry"
        }.createHook{
            before(){
                it.result = true
            }
        }

        loadClass("com.miui.home.launcher.LauncherMenu").methodFinder().first{
            name == "onShow"
        }.createHook{
            after{
                val mDefaultScreenPreview = it.thisObject.getObjectField("mDefaultScreenPreview") as View
                mDefaultScreenPreview.visibility = View.VISIBLE
            }
        }
    }
}
