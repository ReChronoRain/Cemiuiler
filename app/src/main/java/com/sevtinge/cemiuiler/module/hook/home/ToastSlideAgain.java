package com.sevtinge.cemiuiler.module.hook.home;

import android.view.MotionEvent;

import com.sevtinge.cemiuiler.module.base.BaseHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class ToastSlideAgain extends BaseHook {
    public XC_MethodHook.Unhook unhook = null;
    public XC_MethodHook.Unhook unhookA = null;

    @Override
    public void init() {
        findAndHookMethod("com.miui.home.recents.NavStubView",
            "onPointerEvent",
            MotionEvent.class,
            new MethodHook() {
                @Override
                protected void before(MethodHookParam param) throws Throwable {
                    super.before(param);
                    unhook =
                        XposedHelpers.findAndHookMethod(findClassIfExists("android.widget.Toast"),
                            "show", new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) {
                                    param.setResult(null);
                                    logI("im hook Toast show");
                                }
                            }
                        );
                    logI("im hook onPointerEvent");
                }

                @Override
                protected void after(MethodHookParam param) throws Throwable {
                    super.after(param);
                    if (unhook != null) {
                        unhook.unhook();
                        logI("the unhook is: " + unhook);
                    } else {
                        logE("the unhook is: null");
                    }
                }
            }
        );

        findAndHookMethod("com.miui.home.recents.GestureModeApp",
            "onStartGesture", new MethodHook() {
                @Override
                protected void before(MethodHookParam param) {
                    unhookA =
                        XposedHelpers.findAndHookMethod(findClassIfExists("android.widget.Toast"),
                            "show", new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) {
                                    param.setResult(null);
                                    logI("im hook Toast show");
                                }
                            }
                        );
                    logI("im hook onStartGesture");
                }

                @Override
                protected void after(MethodHookParam param) {
                    if (unhookA != null) {
                        unhookA.unhook();
                        logI("the unhook is: " + unhookA);
                    } else {
                        logE("the unhook is: null");
                    }
                }
            }
        );
    }
}
