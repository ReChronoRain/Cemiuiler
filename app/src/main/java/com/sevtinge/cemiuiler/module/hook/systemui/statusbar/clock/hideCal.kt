package com.sevtinge.cemiuiler.module.hook.systemui.statusbar.clock

import com.github.kyuubiran.ezxhelper.ClassUtils
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.sevtinge.cemiuiler.module.base.BaseHook

class hideCalendar:BaseHook() {
    override fun init() {

        ClassUtils.loadClass("com.android.systemui.statusbar.policy.MiuiStatusBarClockController")
            .methodFinder().first {
                name == "getCalendar"
            }.createHook {
                before {
                    it.result = null
                }
            }
    }
}
