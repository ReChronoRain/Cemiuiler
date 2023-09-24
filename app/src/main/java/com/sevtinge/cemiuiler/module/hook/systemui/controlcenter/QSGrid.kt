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
        val rowsHorizontal = mPrefsMap.getInt("system_control_center_old_qs_rows_horizontal", 0)
        if (rows > 1) {
            val rowsRes = rows
        } else {
            val rowsRes = 3
        }

        if (rowsHorizontal > 0) {
            val rowsHorizontalRes = rows
        } else {
            val rowsHorizontalRes = 2
        }

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
                            cols
                        )
                        viewGroup.requestLayout()
                    }
                }
            )
        }

        if (rows > 1 || rowsHorizontal > 0) {
            Helpers.findAndHookMethod(
                "com.android.systemui.qs.MiuiTileLayout",
                lpparam.classLoader,
                "updateResources",
                object : MethodHook() {
                    override fun after(param: MethodHookParam) {
                        val viewGroup = param.thisObject as ViewGroup
                        val mConfiguration: Configuration = viewGroup.context.resources.configuration
                        if (rows > 1 && mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                            XposedHelpers.setObjectField (
                                param.thisObject,
                                "mMaxAllowedRows",
                                rowsRes
                            )
                        } else if (rowsHorizontal > 0 || ) {
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
