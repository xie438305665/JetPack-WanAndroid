package com.wanandroid.bridge.adapter;

import java.lang.System;

/**
 * @description:BaseMultiItemQuickAdapter 扩展
 * @author xcl qq:244672784
 * @date 2020/7/15
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B5\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0014R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/wanandroid/bridge/adapter/SimpleMultipleAdapter;", "Lcom/chad/library/adapter/base/BaseMultiItemQuickAdapter;", "Lcom/wanandroid/bridge/adapter/SimpleMultipleItem;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "datas", "", "listener", "Lcom/wanandroid/bridge/adapter/SimpleAdapterListener;", "types", "Lcom/wanandroid/bridge/adapter/SimpleMultipleType;", "(Ljava/util/List;Lcom/wanandroid/bridge/adapter/SimpleAdapterListener;Ljava/util/List;)V", "convert", "", "holder", "item", "bridge_debug"})
public final class SimpleMultipleAdapter extends com.chad.library.adapter.base.BaseMultiItemQuickAdapter<com.wanandroid.bridge.adapter.SimpleMultipleItem, com.chad.library.adapter.base.viewholder.BaseViewHolder> {
    private final com.wanandroid.bridge.adapter.SimpleAdapterListener<com.wanandroid.bridge.adapter.SimpleMultipleItem, com.chad.library.adapter.base.viewholder.BaseViewHolder> listener = null;
    
    @java.lang.Override()
    protected void convert(@org.jetbrains.annotations.NotNull()
    com.chad.library.adapter.base.viewholder.BaseViewHolder holder, @org.jetbrains.annotations.NotNull()
    com.wanandroid.bridge.adapter.SimpleMultipleItem item) {
    }
    
    public SimpleMultipleAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<com.wanandroid.bridge.adapter.SimpleMultipleItem> datas, @org.jetbrains.annotations.NotNull()
    com.wanandroid.bridge.adapter.SimpleAdapterListener<com.wanandroid.bridge.adapter.SimpleMultipleItem, com.chad.library.adapter.base.viewholder.BaseViewHolder> listener, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wanandroid.bridge.adapter.SimpleMultipleType> types) {
        super(null);
    }
}