package com.sevtinge.cemiuiler.module.home.dock

import com.sevtinge.cemiuiler.module.base.BaseHook

class DisableRecentsIcon : BaseHook() {
    override fun init() {
        findAndHookMethod(
            "com.miui.home.launcher.hotseats.HotSeatsListRecentsAppProvider",
            "updateFinalRecommendTasks",
            object : BaseHook.MethodHook() {
                override fun before(param: MethodHookParam) {
                    param.result = true
                }
            })
    }
}
