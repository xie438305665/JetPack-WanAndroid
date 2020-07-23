package com.wanandroid.bridge.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a#\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003\"\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0005\u001a#\u0010\u0006\u001a\u00020\u00012\u0016\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003\"\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0005\u001a\f\u0010\u0007\u001a\u00020\u0001*\u0004\u0018\u00010\u0004\u001a\f\u0010\b\u001a\u00020\u0001*\u0004\u0018\u00010\u0004\u001a\f\u0010\t\u001a\u00020\n*\u0004\u0018\u00010\u0004\u001a\f\u0010\u000b\u001a\u00020\n*\u0004\u0018\u00010\u0004\u001a\f\u0010\f\u001a\u00020\n*\u0004\u0018\u00010\u0004\u001a\f\u0010\r\u001a\u00020\u0001*\u0004\u0018\u00010\u0004\u001a\u0014\u0010\u000e\u001a\u00020\u0001*\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\n\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\n\u00a8\u0006\u0010"}, d2 = {"goneViews", "", "views", "", "Landroid/view/View;", "([Landroid/view/View;)V", "visibleViews", "gone", "inVisible", "isGone", "", "isInVisible", "isVisible", "visible", "visibleOrGone", "visibleOrInvisible", "bridge_debug"})
public final class ViewExtKt {
    
    /**
     * View显示
     */
    public static final void visible(@org.jetbrains.annotations.Nullable()
    android.view.View $this$visible) {
    }
    
    /**
     * View隐藏
     */
    public static final void gone(@org.jetbrains.annotations.Nullable()
    android.view.View $this$gone) {
    }
    
    /**
     * View占位隐藏
     */
    public static final void inVisible(@org.jetbrains.annotations.Nullable()
    android.view.View $this$inVisible) {
    }
    
    /**
     * View是否显示
     */
    public static final boolean isVisible(@org.jetbrains.annotations.Nullable()
    android.view.View $this$isVisible) {
        return false;
    }
    
    /**
     * View是否隐藏
     */
    public static final boolean isGone(@org.jetbrains.annotations.Nullable()
    android.view.View $this$isGone) {
        return false;
    }
    
    /**
     * View是否占位隐藏
     */
    public static final boolean isInVisible(@org.jetbrains.annotations.Nullable()
    android.view.View $this$isInVisible) {
        return false;
    }
    
    /**
     * @param visible 如果为true 该View显示 否则隐藏
     */
    public static final void visibleOrGone(@org.jetbrains.annotations.Nullable()
    android.view.View $this$visibleOrGone, boolean visible) {
    }
    
    /**
     * @param visible 如果为true 该View显示 否则占位隐藏
     */
    public static final void visibleOrInvisible(@org.jetbrains.annotations.Nullable()
    android.view.View $this$visibleOrInvisible, boolean visible) {
    }
    
    /**
     * 显示传入的view集合
     */
    public static final void visibleViews(@org.jetbrains.annotations.NotNull()
    android.view.View... views) {
    }
    
    /**
     * 隐藏传入的view集合
     */
    public static final void goneViews(@org.jetbrains.annotations.NotNull()
    android.view.View... views) {
    }
}