package com.wanandroid.bridge.refresh;

import java.lang.System;

/**
 * @description:
 * @author xcl qq:244672784
 * @Date 2020/8/1
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002J\u0018\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/wanandroid/bridge/refresh/RefreshObserver;", "T", "Landroidx/lifecycle/Observer;", "", "onChanged", "", "t", "onLoadMore", "onRefresh", "bridge_debug"})
public abstract interface RefreshObserver<T extends java.lang.Object> extends androidx.lifecycle.Observer<java.util.List<T>> {
    
    @java.lang.Override()
    public abstract void onChanged(@org.jetbrains.annotations.Nullable()
    java.util.List<T> t);
    
    /**
     * 下拉刷新
     */
    public abstract void onRefresh();
    
    /**
     * 上拉加载
     */
    public abstract void onLoadMore();
    
    /**
     * @description:
     * @author xcl qq:244672784
     * @Date 2020/8/1
     */
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
        
        @java.lang.Override()
        public static <T extends java.lang.Object>void onChanged(com.wanandroid.bridge.refresh.RefreshObserver<T> $this, @org.jetbrains.annotations.Nullable()
        java.util.List<T> t) {
        }
        
        /**
         * 下拉刷新
         */
        public static <T extends java.lang.Object>void onRefresh(com.wanandroid.bridge.refresh.RefreshObserver<T> $this) {
        }
        
        /**
         * 上拉加载
         */
        public static <T extends java.lang.Object>void onLoadMore(com.wanandroid.bridge.refresh.RefreshObserver<T> $this) {
        }
    }
}