package com.wanandroid.bridge.adapter;

import java.lang.System;

/**
 * @description:Adapter Bind
 * @author xcl qq:244672784
 * @Date 2020/7/14
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004J9\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\rH\u0016\u00a2\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\rH\u0016\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/wanandroid/bridge/adapter/SimpleAdapterListener;", "T", "H", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "", "onBindItemClick", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "view", "Landroid/view/View;", "item", "position", "", "(Lcom/chad/library/adapter/base/BaseQuickAdapter;Landroid/view/View;Ljava/lang/Object;I)V", "onBindViewHolder", "holder", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;I)V", "bridge_debug"})
public abstract interface SimpleAdapterListener<T extends java.lang.Object, H extends com.chad.library.adapter.base.viewholder.BaseViewHolder> {
    
    /**
     * 设置Item点击事件
     */
    public abstract void onBindItemClick(@org.jetbrains.annotations.NotNull()
    com.chad.library.adapter.base.BaseQuickAdapter<T, H> adapter, @org.jetbrains.annotations.NotNull()
    android.view.View view, T item, int position);
    
    /**
     * Item绑定
     */
    public abstract void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    H holder, T item, int position);
    
    /**
     * @description:Adapter Bind
     * @author xcl qq:244672784
     * @Date 2020/7/14
     */
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
        
        /**
         * 设置Item点击事件
         */
        public static <T extends java.lang.Object, H extends com.chad.library.adapter.base.viewholder.BaseViewHolder>void onBindItemClick(com.wanandroid.bridge.adapter.SimpleAdapterListener<T, H> $this, @org.jetbrains.annotations.NotNull()
        com.chad.library.adapter.base.BaseQuickAdapter<T, H> adapter, @org.jetbrains.annotations.NotNull()
        android.view.View view, T item, int position) {
        }
        
        /**
         * Item绑定
         */
        public static <T extends java.lang.Object, H extends com.chad.library.adapter.base.viewholder.BaseViewHolder>void onBindViewHolder(com.wanandroid.bridge.adapter.SimpleAdapterListener<T, H> $this, @org.jetbrains.annotations.NotNull()
        H holder, T item, int position) {
        }
    }
}