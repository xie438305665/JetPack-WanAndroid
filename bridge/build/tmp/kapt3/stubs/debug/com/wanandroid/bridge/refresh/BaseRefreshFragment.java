package com.wanandroid.bridge.refresh;

import java.lang.System;

/**
 * @description:上拉加载 下拉刷新Fragment基类
 * @author xcl qq:244672784
 * @Date 2020/7/5
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u0003*\u0014\b\u0002\u0010\u0004*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\u00052\u00020\u00072\b\u0012\u0004\u0012\u0002H\u00010\b2\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\tB\u0005\u00a2\u0006\u0002\u0010\nJ\b\u0010/\u001a\u000200H\u0014J\u000f\u00101\u001a\u0004\u0018\u00018\u0002H&\u00a2\u0006\u0002\u0010\u000fJ\b\u00102\u001a\u00020&H\u0014J\b\u00103\u001a\u000204H\u0014J\u001a\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH&J\u0014\u00108\u001a\u0006\u0012\u0002\b\u00030\"2\u0006\u00109\u001a\u000207H\u0014J\b\u0010:\u001a\u000204H\u0014J\r\u0010;\u001a\u00028\u0001H\u0014\u00a2\u0006\u0002\u0010\u0017J\u0010\u0010<\u001a\u0002042\u0006\u0010=\u001a\u00020>H\u0016J\u0018\u0010?\u001a\u0002042\u000e\u0010@\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010AH\u0016J\u0012\u0010B\u001a\u0002042\b\u0010C\u001a\u0004\u0018\u00010\u001cH\u0016J&\u0010D\u001a\u0004\u0018\u0001072\u0006\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010H2\b\u0010C\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010I\u001a\u000204H\u0014J\u001a\u0010J\u001a\u0002042\u0006\u00109\u001a\u0002072\b\u0010C\u001a\u0004\u0018\u00010\u001cH\u0016J\u0018\u0010K\u001a\u0002042\u0006\u0010L\u001a\u00020&2\u0006\u0010M\u001a\u00020&H\u0014J\u0016\u0010N\u001a\u0002042\f\u0010O\u001a\b\u0012\u0004\u0012\u00028\u00000AH\u0014J\b\u0010P\u001a\u00020QH\u0014J\b\u0010R\u001a\u00020QH\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u00028\u0002X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u00028\u0001X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0012\u0010!\u001a\u0006\u0012\u0002\b\u00030\"X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020&X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006S"}, d2 = {"Lcom/wanandroid/bridge/refresh/BaseRefreshFragment;", "T", "VM", "Lcom/wanandroid/bridge/base/BaseViewModel;", "A", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Landroidx/fragment/app/Fragment;", "Lcom/wanandroid/bridge/refresh/BaseRefreshObserver;", "Lcom/wanandroid/bridge/adapter/SimpleAdapterListener;", "()V", "activity", "Landroid/app/Activity;", "adapter", "getAdapter", "()Lcom/chad/library/adapter/base/BaseQuickAdapter;", "setAdapter", "(Lcom/chad/library/adapter/base/BaseQuickAdapter;)V", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "ballPulseFooter", "Lcom/scwang/smart/refresh/footer/BallPulseFooter;", "baseVm", "getBaseVm", "()Lcom/wanandroid/bridge/base/BaseViewModel;", "setBaseVm", "(Lcom/wanandroid/bridge/base/BaseViewModel;)V", "Lcom/wanandroid/bridge/base/BaseViewModel;", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "setBundle", "(Landroid/os/Bundle;)V", "loadService", "Lcom/kingja/loadsir/core/LoadService;", "materialHeader", "Lcom/scwang/smart/refresh/header/MaterialHeader;", "page", "", "getPage", "()I", "setPage", "(I)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "refreshLayout", "Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;", "createFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getBaseQuickAdapter", "getLayoutId", "initAdapter", "", "initCreate", "root", "Landroid/view/View;", "initLoadService", "view", "initObserver", "initViewMode", "onAttach", "context", "Landroid/content/Context;", "onChanged", "t", "", "onCreate", "savedInstanceState", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onNetRetry", "onViewCreated", "refreshLoadStatus", "loadStatus", "requestType", "refreshView", "data", "showBallPulseFooter", "", "showMaterialHeader", "bridge_debug"})
public abstract class BaseRefreshFragment<T extends java.lang.Object, VM extends com.wanandroid.bridge.base.BaseViewModel, A extends com.chad.library.adapter.base.BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder>> extends androidx.fragment.app.Fragment implements com.wanandroid.bridge.refresh.BaseRefreshObserver<T>, com.wanandroid.bridge.adapter.SimpleAdapterListener<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> {
    private int page = 0;
    @org.jetbrains.annotations.Nullable()
    private android.os.Bundle bundle;
    @org.jetbrains.annotations.NotNull()
    protected A adapter;
    @org.jetbrains.annotations.NotNull()
    protected VM baseVm;
    private com.scwang.smart.refresh.layout.SmartRefreshLayout refreshLayout;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private com.scwang.smart.refresh.footer.BallPulseFooter ballPulseFooter;
    private com.scwang.smart.refresh.header.MaterialHeader materialHeader;
    private com.kingja.loadsir.core.LoadService<?> loadService;
    private android.app.Activity activity;
    private java.util.HashMap _$_findViewCache;
    
    public final int getPage() {
        return 0;
    }
    
    public final void setPage(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.os.Bundle getBundle() {
        return null;
    }
    
    public final void setBundle(@org.jetbrains.annotations.Nullable()
    android.os.Bundle p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final A getAdapter() {
        return null;
    }
    
    protected final void setAdapter(@org.jetbrains.annotations.NotNull()
    A p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final VM getBaseVm() {
        return null;
    }
    
    protected final void setBaseVm(@org.jetbrains.annotations.NotNull()
    VM p0) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * 初始化
     */
    public abstract void initCreate(@org.jetbrains.annotations.NotNull()
    android.view.View root, @org.jetbrains.annotations.Nullable()
    android.os.Bundle bundle);
    
    /**
     * Adapter
     */
    @org.jetbrains.annotations.Nullable()
    public abstract A getBaseQuickAdapter();
    
    /**
     * 布局Id 根据业务可以重写函数
     */
    protected int getLayoutId() {
        return 0;
    }
    
    /**
     * liveData 跟 ViewMode 绑定   根据业务可以重写函数
     */
    protected void initObserver() {
    }
    
    /**
     * LiveData发生改变刷新Load  根据业务可以重写函数
     * @param loadStatus  @link[LoadStatus] 加载状态
     * @param requestType  @link[requestType] 请求方式
     */
    protected void refreshLoadStatus(@com.zhixinhuixue.library.net.NetViewModel.LoadStatus()
    int loadStatus, @com.zhixinhuixue.library.net.NetViewModel.RequestType()
    int requestType) {
    }
    
    /**
     * 网络请求重试 根据业务可以重写函数
     */
    protected void onNetRetry() {
    }
    
    /**
     * 获取ViewMode 根据业务可以重写函数
     */
    @org.jetbrains.annotations.NotNull()
    protected VM initViewMode() {
        return null;
    }
    
    /**
     * 初始化LoadService 根据业务可以重写函数
     */
    @org.jetbrains.annotations.NotNull()
    protected com.kingja.loadsir.core.LoadService<?> initLoadService(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
        return null;
    }
    
    /**
     * 创建Factory 根据业务可以重写函数
     */
    @org.jetbrains.annotations.NotNull()
    protected androidx.lifecycle.ViewModelProvider.Factory createFactory() {
        return null;
    }
    
    /**
     * 是否显示BallPulseFooter
     */
    protected boolean showBallPulseFooter() {
        return false;
    }
    
    /**
     * 是否显示MaterialHeader
     */
    protected boolean showMaterialHeader() {
        return false;
    }
    
    /**
     * 初始化Adapter
     */
    protected void initAdapter() {
    }
    
    /**
     * 刷新Adapter
     * @param data MutableList<T>
     */
    protected void refreshView(@org.jetbrains.annotations.NotNull()
    java.util.List<T> data) {
    }
    
    /**
     * BaseRefreshObserver
     */
    @java.lang.Override()
    public void onChanged(@org.jetbrains.annotations.Nullable()
    java.util.List<T> t) {
    }
    
    public BaseRefreshFragment() {
        super();
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
     * 设置Item点击事件
     */
    public void onBindItemClick(@org.jetbrains.annotations.NotNull()
    com.chad.library.adapter.base.BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> adapter, @org.jetbrains.annotations.NotNull()
    android.view.View view, T item, int position) {
    }
    
    /**
     * Item绑定
     */
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.chad.library.adapter.base.viewholder.BaseViewHolder holder, T item, int position) {
    }
}