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
        var colsRes = R.integer.quick_settings_num_columns_4
        var rowsRes = R.integer.quick_settings_num_rows_3

        when (cols) {
            3 -> colsRes = R.integer.quick_settings_num_columns_3
            4 -> colsRes = R.integer.quick_settings_num_columns_4
            5 -> colsRes = R.integer.quick_settings_num_columns_5
            6 -> colsRes = R.integer.quick_settings_num_columns_6
            7 -> colsRes = R.integer.quick_settings_num_columns_7
        }

        when (rows) {
            2 -> rowsRes = R.integer.quick_settings_num_rows_2
            3 -> rowsRes = R.integer.quick_settings_num_rows_3
            4 -> rowsRes = R.integer.quick_settings_num_rows_4
            5 -> rowsRes = R.integer.quick_settings_num_rows_5
        }

        var mRowsHorizontal = R.integer.quick_settings_num_rows_2
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
                                rowsRes
                            )
                        } else {
                            XposedHelpers.setObjectField (
                                param.thisObject,
                                "mMaxAllowedRows",
                                mRowsHorizontal
                            )
                        }
                        viewGroup.requestLayout()
                    }
                }
            )
        }
    }
}
