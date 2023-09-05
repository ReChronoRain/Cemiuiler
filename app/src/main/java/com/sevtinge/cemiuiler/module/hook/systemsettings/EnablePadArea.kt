package com.sevtinge.cemiuiler.module.systemsettings

import com.github.kyuubiran.ezxhelper.ClassUtils
import com.sevtinge.cemiuiler.module.base.BaseHook

class EnablePadArea :BaseHook(){
    override fun init() {
        ClassUtils.setStaticObject(
            ClassUtils.loadClass("com.android.settings.utils.SettingsFeatures"),
            "IS_SUPPORT_TABLET_SCREEN_SETTINGS",
            true
        )
    }
}
