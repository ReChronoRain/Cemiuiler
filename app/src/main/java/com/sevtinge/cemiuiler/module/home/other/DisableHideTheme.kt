package com.sevtinge.cemiuiler.module.home.other

import android.content.ComponentName
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.api.findAllMethods
import com.sevtinge.cemiuiler.utils.api.isPad
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import miui.os.Build

class DisableHideTheme : BaseHook() {

    override fun init() {
        if (!isPad()){
            return
        }
        hookAllMethods("com.miui.home.launcher.DeviceConfig", "needHideThemeManager",
            object : MethodHook() {
                override fun before(param: MethodHookParam) {
                    param.result = false
                }
            }
        )
    }

}
