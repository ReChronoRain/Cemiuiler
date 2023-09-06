package com.sevtinge.cemiuiler.utils.api

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass

// by StarVoyager
object LazyClass {
    val clazzMiuiBuild by lazy {
        loadClass("miui.os.Build")
    }

    val AndroidBuildCls by lazy {
        loadClass("android.os.Build")
    }
}
