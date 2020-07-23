package com.wanandroid.bridge.adapter;

import java.lang.System;

/**
 * @description:BaseQuickAdapter 扩展
 * @author xcl qq:244672784
 * @Date 2020/7/14
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B1\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0014\u00a2\u0006\u0002\u0010\u0010R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/wanandroid/bridge/adapter/SimpleAdapter;", "T", "H", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "layoutRes", "", "data", "", "listener", "Lcom/wanandroid/bridge/adapter/SimpleAdapterListener;", "(ILjava/util/List;Lcom/wanandroid/bridge/adapter/SimpleAdapterListener;)V", "convert", "", "holder", "item", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V", "bridge_debug"})
public final class SimpleAdapter<T extends java.lang.Object, H extends com.chad.library.adapter.base.viewholder.BaseViewHolder> extends com.chad.library.adapter.base.BaseQuickAdapter<T, H> {
    private final com.wanandroid.bridge.adapter.SimpleAdapterListener<T, H> listener = null;
    
    @java.lang.Override()
    protected void convert(@org.jetbrains.annotations.NotNull()
    H holder, T item) {
    }
    
    public SimpleAdapter(@androidx.annotation.LayoutRes()
    int layoutRes, @org.jetbrains.annotations.NotNull()
    java.util.List<T> data, @org.jetbrains.annotations.NotNull()
    com.wanandroid.bridge.adapter.SimpleAdapterListener<T, H> listener) {
        super(0, null);
    }
}