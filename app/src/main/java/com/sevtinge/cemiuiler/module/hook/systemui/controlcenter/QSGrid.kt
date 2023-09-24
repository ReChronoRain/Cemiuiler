package com.sevtinge.cemiuiler.module.hook.systemui.controlcenter

import android.content.res.Configuration
import android.view.View
import android.view.ViewGroup

import com.sevtinge.cemiuiler.R
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.Helpers

import de.robv.android.xposed.XposedHelpers

class QSGrid : BaseHook() {
    override fun init() {
        val cols = mPrefsMap.getInt("system_control_center_old_qs_columns", 4)
        //val colsHorizontal = mPrefsMap.getInt("system_control_center_old_qs_columns_horizontal", 5)
        val rows = mPrefsMap.getInt("system_control_center_old_qs_rows", 3)
        val rowsHorizontal = mPrefsMap.getInt("system_control_center_old_qs_rows_horizontal", 2)

        Helpers.findAndHookMethod(
            "com.android.systemui.qs.MiuiTileLayout",
            lpparam.classLoader,
            "updateColumns",
            object : MethodHook() {
                override fun after(param: MethodHookParam) {
                    val viewGroup = param.thisObject as ViewGroup
                    val mConfiguration: Configuration = viewGroup.context.resources.configuration
                    if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        XposedHelpers.setObjectField (
                            param.thisObject,
                            "mColumns",
                            cols
                        )
                    } else {
                        XposedHelpers.setObjectField (
                            param.thisObject,
                            "mColumns",
                            cols //colsHorizontal
                        )
                    }
                }
            }
        )

        Helpers.findAndHookMethod(
            "com.android.systemui.qs.MiuiTileLayout",
            lpparam.classLoader,
            "updateRows",
            object : MethodHook() {
                override fun after(param: MethodHookParam) {
                    val viewGroup = param.thisObject as ViewGroup
                    val mConfiguration: Configuration = viewGroup.context.resources.configuration
                    if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        XposedHelpers.setObjectField (
                            param.thisObject,
                            "mMaxAllowedRows",
                            rows
                        )
                    } else {
                        XposedHelpers.setObjectField (
                            param.thisObject,
                            "mMaxAllowedRows",
                            rowsHorizontal
                        )
                    }
                    viewGroup.requestLayout()
                }
            }
        )
    }
}
