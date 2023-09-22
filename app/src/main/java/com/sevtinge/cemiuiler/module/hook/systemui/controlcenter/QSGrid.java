package com.sevtinge.cemiuiler.module.hook.systemui.controlcenter;

import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;

import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.module.base.BaseHook;

import com.sevtinge.cemiuiler.utils.Helpers;

import de.robv.android.xposed.XposedHelpers;

public class QSGrid extends BaseHook {

    @Override
    public void init() {
        int cols = mPrefsMap.getInt("system_control_center_old_qs_columns", 2);
        
        int colsRes = R.integer.quick_settings_num_columns_3;
        int rows = mPrefsMap.getInt("system_control_center_old_qs_rows", 1);
                int rowsRes = R.integer.quick_settings_num_rows_4;
                switch (rows) {
            case 2 -> rowsRes = R.integer.quick_settings_num_rows_2;
            case 3 -> rowsRes = R.integer.quick_settings_num_rows_3;
            case 4 -> rowsRes = R.integer.quick_settings_num_rows_4;
            case 5 -> rowsRes = R.integer.quick_settings_num_rows_5;

        switch (cols) {
            case 3 -> colsRes = R.integer.quick_settings_num_columns_3;
            case 4 -> colsRes = R.integer.quick_settings_num_columns_4;
            case 5 -> colsRes = R.integer.quick_settings_num_columns_5;
            case 6 -> colsRes = R.integer.quick_settings_num_columns_6;
            case 7 -> colsRes = R.integer.quick_settings_num_columns_7;
        }

        

        if (cols > 2)
            mResHook.setResReplacement("com.android.systemui", "integer", "quick_settings_num_columns", colsRes);
        if (rows > 1) 
            mResHook.setResReplacement("com.android.systemui", "integer", "quick_settings_num_rows", 5);

        Helpers.findAndHookMethod("com.android.systemui.qs.MiuiTileLayout", lpparam.classLoader, "changeTile", new MethodHook() {
            @Override
            protected void after(MethodHookParam param) {
                
        }
                if (rows > 1 && ((ViewGroup) param.thisObject).getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    mResHook.setResReplacement("com.android.systemui", "integer", "quick_settings_num_rows", 4);
                } else {
                    mResHook.setResReplacement("com.android.systemui", "integer", "quick_settings_num_rows", 2);
                }
                //((ViewGroup) param.thisObject).requestLayout();
                //updateLabelsVisibility(param.args[0], XposedHelpers.getIntField(param.thisObject, "mRows"), ((ViewGroup) param.thisObject).getResources().getConfiguration().orientation);
            }
        });
    }
}
