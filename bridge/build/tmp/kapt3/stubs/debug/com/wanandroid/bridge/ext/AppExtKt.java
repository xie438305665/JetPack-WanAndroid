package com.wanandroid.bridge.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002\u001a\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002\u001a\u0012\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010\u001a\u0006\u0010\u0011\u001a\u00020\u0007\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0012"}, d2 = {"activityList", "Ljava/util/LinkedList;", "Landroid/app/Activity;", "currentActivity", "getCurrentActivity", "()Landroid/app/Activity;", "addActivity", "", "activity", "getAppVersion", "", "context", "Landroid/content/Context;", "getPackageNameName", "removeActivity", "cls", "Ljava/lang/Class;", "removeAllActivity", "bridge_debug"})
public final class AppExtKt {
    private static final java.util.LinkedList<android.app.Activity> activityList = null;
    
    /**
     * 获取packageName
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getPackageNameName(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * 获取versionName
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getAppVersion(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final android.app.Activity getCurrentActivity() {
        return null;
    }
    
    /**
     * 入栈
     */
    public static final void addActivity(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    /**
     * 出栈
     */
    public static final void removeActivity(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    /**
     * 出栈
     */
    public static final void removeActivity(@org.jetbrains.annotations.NotNull()
    java.lang.Class<?> cls) {
    }
    
    /**
     * 全部出栈
     */
    public static final void removeAllActivity() {
    }
}