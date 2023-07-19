package com.sevtinge.cemiuiler.module.home.layout;

import static com.sevtinge.cemiuiler.utils.Helpers.getPackageVersionCode;

import android.content.Context;

import com.sevtinge.cemiuiler.module.base.BaseHook;
import com.sevtinge.cemiuiler.utils.DisplayUtils;

public class WorkspacePadding extends BaseHook {

    Context mContext;
    Class<?> mDeviceConfig;

    static int mHomeVersionCode;
    final static int NEW_HOME_WORKSPACE_PADDING_TOP_VERSION = 439096586;

    @Override
    public void init() {

        mDeviceConfig = findClassIfExists("com.miui.home.launcher.DeviceConfig");
        mHomeVersionCode = getPackageVersionCode(lpparam);

        findAndHookMethod(mDeviceConfig, "Init", Context.class, boolean.class, new MethodHook() {
            @Override
            protected void before(MethodHookParam param) {
                mContext = (Context) param.args[0];
            }
        });

        if (mPrefsMap.getBoolean("home_layout_workspace_padding_bottom_enable")) {
            findAndHookMethod(mDeviceConfig, "getWorkspaceCellPaddingBottom", new MethodHook() {
                @Override
                protected void before(MethodHookParam param) {
                    param.setResult(DisplayUtils.dip2px(mContext, mPrefsMap.getInt("home_layout_workspace_padding_bottom", 0)));
                }
            });
        }

        if (mPrefsMap.getBoolean("home_layout_workspace_padding_top_enable")) {
            if (mHomeVersionCode >= NEW_HOME_WORKSPACE_PADDING_TOP_VERSION) {
                findAndHookMethod(mDeviceConfig, "getWorkspaceCellPaddingTop", Context.class, new MethodHook() {
                    @Override
                    protected void before(MethodHookParam param) {
                        param.setResult(DisplayUtils.dip2px(mContext, mPrefsMap.getInt("home_layout_workspace_padding_top", 0)));
                    }
                });
            } else {
                findAndHookMethod(mDeviceConfig, "getWorkspaceCellPaddingTop", new MethodHook() {
                    @Override
                    protected void before(MethodHookParam param) {
                        param.setResult(DisplayUtils.dip2px(mContext, mPrefsMap.getInt("home_layout_workspace_padding_top", 0)));
                    }
                });
            }
        }
    }
}
