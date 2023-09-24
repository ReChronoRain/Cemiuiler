package com.sevtinge.cemiuiler.module.hook.systemui.controlcenter

import android.content.res.Configuration
import android.view.View
import android.view.ViewGroup

import com.sevtinge.cemiuiler.R
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.Helpers

import de.robv.android.xposed.XposedHelpers

class QQSGrid : BaseHook() {
    override fun init() {
        val cols = mPrefsMap.getInt("system_control_center_old_qs_grid_columns", 5);
        //val colsHorizontal = mPrefsMap.getInt("system_control_center_old_qs_grid_columns_horizontal", 6);

        Helpers.findAndHookMethod(
            "com.android.systemui.qs.MiuiQuickQSPanel",
            lpparam.classLoader,
            "setMaxTiles",
            object : MethodHook() {
                override fun before(param: MethodHookParam) {
                    val viewGroup = param.thisObject as ViewGroup
                    val mConfiguration: Configuration = viewGroup.context.resources.configuration
                    if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        param.args[0] = cols
                    } else {
                        param.args[0] = 7
                    }
                    viewGroup.requestLayout()
                }
            }
        )
        //mResHook.setResReplacement("com.android.systemui", "integer", "quick_settings_qqs_count", colsResId);
    }
}
