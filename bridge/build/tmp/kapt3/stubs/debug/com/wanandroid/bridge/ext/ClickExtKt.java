package com.wanandroid.bridge.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000*\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0006\u001a\u00020\u00072\u0016\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\t\"\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\u0002\u0010\r\u001aA\u0010\u000e\u001a\u00020\u00072\u0016\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\t\"\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\u0002\u0010\u0010\u001a7\u0010\u0011\u001a\u00020\u0007*\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00012!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00070\f\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0016"}, d2 = {"lastClickTime", "", "getLastClickTime", "()J", "setLastClickTime", "(J)V", "setOnclick", "", "views", "", "Landroid/view/View;", "onClick", "Lkotlin/Function1;", "([Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "setOnclickNoRepeat", "interval", "([Landroid/view/View;JLkotlin/jvm/functions/Function1;)V", "clickNoRepeat", "action", "Lkotlin/ParameterName;", "name", "view", "bridge_debug"})
public final class ClickExtKt {
    
    /**
     * 防止重复点击事件 默认0.5秒内不可重复点击
     * @param interval 时间间隔 默认0.5秒
     * @param action 执行方法
     */
    private static long lastClickTime = 0L;
    
    /**
     * 设置防止重复点击事件
     * @param views 需要设置点击事件的view
     * @param interval 时间间隔 默认0.5秒
     * @param onClick 点击触发的方法
     */
    public static final void setOnclickNoRepeat(@org.jetbrains.annotations.NotNull()
    android.view.View[] views, long interval, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.view.View, kotlin.Unit> onClick) {
    }
    
    public static final long getLastClickTime() {
        return 0L;
    }
    
    public static final void setLastClickTime(long p0) {
    }
    
    public static final void clickNoRepeat(@org.jetbrains.annotations.NotNull()
    android.view.View $this$clickNoRepeat, long interval, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.view.View, kotlin.Unit> action) {
    }
    
    /**
     * 设置点击事件
     * @param views 需要设置点击事件的view
     * @param onClick 点击触发的方法
     */
    public static final void setOnclick(@org.jetbrains.annotations.NotNull()
    android.view.View[] views, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.view.View, kotlin.Unit> onClick) {
    }
}