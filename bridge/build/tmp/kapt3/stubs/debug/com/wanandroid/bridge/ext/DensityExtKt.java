package com.wanandroid.bridge.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u0006\u0010\u0007\u001a\u00020\u0001\u001a\u0006\u0010\b\u001a\u00020\u0001\u001a\u0006\u0010\t\u001a\u00020\u0001\u001a\u000e\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\u000e\u0010\r\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0004\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u0004\u001a\n\u0010\u0010\u001a\u00020\u0004*\u00020\u0004\u001a\n\u0010\u0011\u001a\u00020\u0001*\u00020\u0004\u00a8\u0006\u0012"}, d2 = {"getAllScreenHeight", "", "getBottomBarHeight", "getDisplayMetrics", "", "getScreenHeight", "getScreenHeight2", "getScreenWidth", "getScreenWidth2", "getStatusBarHeight", "getStatusBarHeight2", "activity", "Landroid/app/Activity;", "getTitleBarHeight", "dp2px", "px2dp", "px2sp", "sp2px", "bridge_debug"})
public final class DensityExtKt {
    
    /**
     * 像素密度
     */
    public static final float getDisplayMetrics() {
        return 0.0F;
    }
    
    /**
     * dp 转成为 px
     */
    public static final int dp2px(float $this$dp2px) {
        return 0;
    }
    
    /**
     * px 转成为 dp
     */
    public static final int px2dp(float $this$px2dp) {
        return 0;
    }
    
    /**
     * sp转px
     */
    public static final int sp2px(float $this$sp2px) {
        return 0;
    }
    
    /**
     * px转sp
     */
    public static final float px2sp(float $this$px2sp) {
        return 0.0F;
    }
    
    /**
     * 获取屏幕宽
     */
    public static final int getScreenWidth() {
        return 0;
    }
    
    /**
     * 获取屏幕高，包含状态栏，但不包含虚拟按键，如1920屏幕只有1794
     */
    public static final int getScreenHeight() {
        return 0;
    }
    
    /**
     * 获取屏幕宽
     */
    public static final int getScreenWidth2() {
        return 0;
    }
    
    /**
     * 获取屏幕高，包含状态栏，但不包含某些手机最下面的【HOME键那一栏】，如1920屏幕只有1794
     */
    public static final int getScreenHeight2() {
        return 0;
    }
    
    /**
     * 获取屏幕原始尺寸高度，包括状态栏以及虚拟功能键高度
     */
    public static final int getAllScreenHeight() {
        return 0;
    }
    
    /**
     * 状态栏高度，单位px，一般为25dp
     */
    public static final int getStatusBarHeight() {
        return 0;
    }
    
    /**
     * 状态栏高度，单位px，【注意】要在onWindowFocusChanged中获取才可以
     */
    public static final int getStatusBarHeight2(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return 0;
    }
    
    /**
     * 标题栏的高度，【注意】要在onWindowFocusChanged中获取才可以
     */
    public static final int getTitleBarHeight(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return 0;
    }
    
    /**
     * 获取 虚拟按键的高度
     */
    public static final int getBottomBarHeight() {
        return 0;
    }
}