package com.sevtinge.cemiuiler.module.hook.systemui.controlcenter

import android.content.res.Configuration
import android.view.View
import android.view.ViewGroup

import com.sevtinge.cemiuiler.R
import com.sevtinge.cemiuiler.module.base.BaseHook
import com.sevtinge.cemiuiler.utils.Helpers

import de.robv.android.xposed.XposedHelpers;

class QSGrid : BaseHook() {
    override fun init() {
        val cols = mPrefsMap.getInt("system_control_center_old_qs_columns", 2)
        val rows = mPrefsMap.getInt("system_control_center_old_qs_rows", 1)

        val colsRes = when (cols) {
            3 -> R.integer.quick_settings_num_columns_3
            4 -> R.integer.quick_settings_num_columns_4
            5 -> R.integer.quick_settings_num_columns_5
            6 -> R.integer.quick_settings_num_columns_6
            7 -> R.integer.quick_settings_num_columns_7
            else -> R.integer.quick_settings_num_columns_4
        }

        val rowsRes = when (rows) {
            2 -> R.integer.quick_settings_num_rows_2
            3 -> R.integer.quick_settings_num_rows_3
            4 -> R.integer.quick_settings_num_rows_4
            5 -> R.integer.quick_settings_num_rows_5
            else -> R.integer.quick_settings_num_rows_3
        }

        val rowsHorizontalRes = R.integer.quick_settings_num_rows_2
/*
        if (cols > 2) {
            Helpers.findAndHookMethod(
                "com.android.systemui.qs.MiuiTileLayout",
                lpparam.classLoader,
                "updateColumns",
                object : MethodHook() {
                    override fun after(param: MethodHookParam) {
                        val viewGroup = param.thisObject as ViewGroup
                        XposedHelpers.setObjectField (
                            param.thisObject,
                            "mColumns",
                            colsRes
                        )
                        viewGroup.requestLayout()
                    }
                }
            )
        }
*/
        if (rows > 1) {
            Helpers.findAndHookMethod(
                "com.android.systemui.qs.MiuiTileLayout",
                lpparam.classLoader,
                "updateResources",
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
                                rowsHorizontalRes
                            )
                        }
                        viewGroup.requestLayout()
                    }
                }
            )
        }
    }
}
