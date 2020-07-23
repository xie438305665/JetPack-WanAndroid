package com.wanandroid.bridge.base;

import java.lang.System;

/**
 * @description:上拉加载 下拉刷新Fragment基类
 * @author xcl qq:244672784
 * @Date 2020/7/5
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u0003*\u0014\b\u0002\u0010\u0004*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\u00052\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00072\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\t0\b2\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\nB\u0005\u00a2\u0006\u0002\u0010\u000bJ\u000f\u00100\u001a\u0004\u0018\u00018\u0002H&\u00a2\u0006\u0002\u0010\u000eJ\b\u00101\u001a\u00020\u001fH\u0016J\b\u00102\u001a\u000203H\u0016J\u0016\u00104\u001a\u0002032\f\u00105\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0016J&\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\b\u0010>\u001a\u000203H\u0016J\b\u0010?\u001a\u000203H\u0016J\u0018\u0010@\u001a\u0002032\u0006\u0010A\u001a\u00020\u001f2\u0006\u0010B\u001a\u00020\u001fH\u0016J\u0016\u0010C\u001a\u0002032\f\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0016J\b\u0010E\u001a\u00020FH\u0016J\b\u0010G\u001a\u00020FH\u0016R\u001c\u0010\f\u001a\u00028\u0002X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020+X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/\u00a8\u0006H"}, d2 = {"Lcom/wanandroid/bridge/base/BaseRefreshFragment;", "T", "VM", "Lcom/wanandroid/bridge/base/BaseViewModel;", "A", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Lcom/wanandroid/bridge/base/BaseFragment;", "Landroidx/lifecycle/Observer;", "", "Lcom/wanandroid/bridge/adapter/SimpleAdapterListener;", "()V", "adapter", "getAdapter", "()Lcom/chad/library/adapter/base/BaseQuickAdapter;", "setAdapter", "(Lcom/chad/library/adapter/base/BaseQuickAdapter;)V", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "ballPulseFooter", "Lcom/scwang/smart/refresh/footer/BallPulseFooter;", "getBallPulseFooter", "()Lcom/scwang/smart/refresh/footer/BallPulseFooter;", "setBallPulseFooter", "(Lcom/scwang/smart/refresh/footer/BallPulseFooter;)V", "materialHeader", "Lcom/scwang/smart/refresh/header/MaterialHeader;", "getMaterialHeader", "()Lcom/scwang/smart/refresh/header/MaterialHeader;", "setMaterialHeader", "(Lcom/scwang/smart/refresh/header/MaterialHeader;)V", "page", "", "getPage", "()I", "setPage", "(I)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "refreshLayout", "Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;", "getRefreshLayout", "()Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;", "setRefreshLayout", "(Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;)V", "getBaseQuickAdapter", "getLayoutId", "initAdapter", "", "onChanged", "t", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onLoadMore", "onRefresh", "refreshLoadStatus", "loadStatus", "requestType", "refreshView", "data", "showBallPulseFooter", "", "showMaterialHeader", "bridge_debug"})
public abstract class BaseRefreshFragment<T extends java.lang.Object, VM extends com.wanandroid.bridge.base.BaseViewModel, A extends com.chad.library.adapter.base.BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder>> extends com.wanandroid.bridge.base.BaseFragment<T, VM> implements androidx.lifecycle.Observer<java.util.List<T>>, com.wanandroid.bridge.adapter.SimpleAdapterListener<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> {
    private int page = 0;
    @org.jetbrains.annotations.NotNull()
    public A adapter;
    @org.jetbrains.annotations.NotNull()
    public com.scwang.smart.refresh.layout.SmartRefreshLayout refreshLayout;
    @org.jetbrains.annotations.NotNull()
    public androidx.recyclerview.widget.RecyclerView recyclerView;
    @org.jetbrains.annotations.NotNull()
    public com.scwang.smart.refresh.footer.BallPulseFooter ballPulseFooter;
    @org.jetbrains.annotations.NotNull()
    public com.scwang.smart.refresh.header.MaterialHeader materialHeader;
    private java.util.HashMap _$_findViewCache;
    
    public final int getPage() {
        return 0;
    }
    
    public final void setPage(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final A getAdapter() {
        return null;
    }
    
    public final void setAdapter(@org.jetbrains.annotations.NotNull()
    A p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.scwang.smart.refresh.layout.SmartRefreshLayout getRefreshLayout() {
        return null;
    }
    
    public final void setRefreshLayout(@org.jetbrains.annotations.NotNull()
    com.scwang.smart.refresh.layout.SmartRefreshLayout p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.recyclerview.widget.RecyclerView getRecyclerView() {
        return null;
    }
    
    public final void setRecyclerView(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.scwang.smart.refresh.footer.BallPulseFooter getBallPulseFooter() {
        return null;
    }
    
    public final void setBallPulseFooter(@org.jetbrains.annotations.NotNull()
    com.scwang.smart.refresh.footer.BallPulseFooter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.scwang.smart.refresh.header.MaterialHeader getMaterialHeader() {
        return null;
    }
    
    public final void setMaterialHeader(@org.jetbrains.annotations.NotNull()
    com.scwang.smart.refresh.header.MaterialHeader p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    /**
     * 布局Id
     */
    @java.lang.Override()
    public int getLayoutId() {
        return 0;
    }
    
    /**
     * Adapter
     */
    @org.jetbrains.annotations.Nullable()
    public abstract A getBaseQuickAdapter();
    
    /**
     * 是否显示BallPulseFooter
     */
    public boolean showBallPulseFooter() {
        return false;
    }
    
    /**
     * 是否显示MaterialHeader
     */
    public boolean showMaterialHeader() {
        return false;
    }
    
    /**
     * 下拉刷新
     */
    public void onRefresh() {
    }
    
    /**
     * 上拉加载
     */
    public void onLoadMore() {
    }
    
    /**
     * 初始化Adapter
     */
    public void initAdapter() {
    }
    
    /**
     * 刷新Adapter
     * @param data MutableList<T>
     */
    public void refreshView(@org.jetbrains.annotations.NotNull()
    java.util.List<T> data) {
    }
    
    /**
     * @param loadStatus Int
     * @param requestType Int
     */
    @java.lang.Override()
    public void refreshLoadStatus(int loadStatus, int requestType) {
    }
    
    /**
     * Observer接口实现
     */
    @java.lang.Override()
    public void onChanged(@org.jetbrains.annotations.NotNull()
    java.util.List<T> t) {
    }
    
    public BaseRefreshFragment() {
        super();
    }
    
    /**
     * 设置Item点击事件
     */
    public void onBindItemClick(@org.jetbrains.annotations.NotNull()
    com.chad.library.adapter.base.BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> adapter, @org.jetbrains.annotations.NotNull()
    android.view.View view, int position) {
    }
    
    /**
     * Item绑定
     */
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.chad.library.adapter.base.viewholder.BaseViewHolder holder, T item, int position) {
    }
}