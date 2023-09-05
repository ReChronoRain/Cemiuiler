package com.sevtinge.cemiuiler.module.systemsettings

import com.github.kyuubiran.ezxhelper.ClassUtils
import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.sevtinge.cemiuiler.module.base.BaseHook

class EnableFlodArea :BaseHook(){
    override fun init() {
        ClassUtils.setStaticObject(
            ClassUtils.loadClass("com.android.settings.utils.SettingsFeatures"),
            "IS_SUPPORT_FOLD_SCREEN_SETTINGS",
            true
        )

        loadClass("com.android.settings.utils.SettingsFeatures").methodFinder().first(){
            name == "isSupportFoldScreenSettings"
        }.createHook {
            before{
                it.result = true
            }
        }
    }
}
