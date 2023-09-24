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

        when (cols) {
            3 -> val colsRes = R.integer.quick_settings_num_columns_3
            4 -> val colsRes = R.integer.quick_settings_num_columns_4
            5 -> val colsRes = R.integer.quick_settings_num_columns_5
            6 -> val colsRes = R.integer.quick_settings_num_columns_6
            7 -> val colsRes = R.integer.quick_settings_num_columns_7
            else -> val colsRes = R.integer.quick_settings_num_columns_4
        }

        when (rows) {
            2 -> val rowsRes = R.integer.quick_settings_num_rows_2
            3 -> val rowsRes = R.integer.quick_settings_num_rows_3
            4 -> val rowsRes = R.integer.quick_settings_num_rows_4
            5 -> val rowsRes = R.integer.quick_settings_num_rows_5
            else -> val rowsRes = R.integer.quick_settings_num_rows_3
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
                                rowsRes
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
