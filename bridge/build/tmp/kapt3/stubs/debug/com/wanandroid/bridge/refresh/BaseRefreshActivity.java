package com.wanandroid.bridge.refresh;

import java.lang.System;

/**
 * @description:Activity基类
 * @author xcl qq:244672784
 * @Date 2020/7/5
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u0003*\u0014\b\u0002\u0010\u0004*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\u00052\u00020\u00072\b\u0012\u0004\u0012\u0002H\u00010\b2\u00020\t2\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\nB\u0005\u00a2\u0006\u0002\u0010\u000bJ\b\u0010B\u001a\u00020CH\u0014J\u000f\u0010D\u001a\u0004\u0018\u00018\u0002H&\u00a2\u0006\u0002\u0010\u000eJ\b\u0010E\u001a\u00020FH\u0014J\u0012\u0010G\u001a\u00020F2\b\u0010H\u001a\u0004\u0018\u00010%H&J\b\u0010I\u001a\u00020FH\u0014J\u0014\u0010J\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010K\u001a\u00020LH\u0014J\b\u0010M\u001a\u00020FH\u0014J\u0010\u0010N\u001a\u00020F2\u0006\u0010O\u001a\u00020+H\u0014J\r\u0010P\u001a\u00028\u0001H\u0014\u00a2\u0006\u0002\u0010\u001aJ\u0018\u0010Q\u001a\u00020F2\u000e\u0010R\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010SH\u0016J\u0012\u0010T\u001a\u00020F2\b\u0010U\u001a\u0004\u0018\u00010%H\u0014J\b\u0010V\u001a\u00020FH\u0016J\b\u0010W\u001a\u00020FH\u0016J\b\u0010X\u001a\u00020FH\u0014J\b\u0010Y\u001a\u00020FH\u0016J\u001c\u0010Z\u001a\u00020F2\b\b\u0002\u0010[\u001a\u00020\\2\b\b\u0002\u0010]\u001a\u00020\\H\u0014J\u0018\u0010^\u001a\u00020F2\u000e\u0010_\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010SH&J\b\u0010`\u001a\u00020aH\u0014J\b\u0010b\u001a\u00020aH\u0014J\b\u0010c\u001a\u00020aH\u0014R\u001c\u0010\f\u001a\u00028\u0002X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u00028\u0001X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020+X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u000201X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u000207X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020=X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010A\u00a8\u0006d"}, d2 = {"Lcom/wanandroid/bridge/refresh/BaseRefreshActivity;", "T", "VM", "Lcom/wanandroid/bridge/base/BaseViewModel;", "A", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/wanandroid/bridge/refresh/BaseRefreshObserver;", "Lcom/zhixinhuixue/library/widget/custom/ToolbarClickListener;", "Lcom/wanandroid/bridge/adapter/SimpleAdapterListener;", "()V", "adapter", "getAdapter", "()Lcom/chad/library/adapter/base/BaseQuickAdapter;", "setAdapter", "(Lcom/chad/library/adapter/base/BaseQuickAdapter;)V", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "ballPulseFooter", "Lcom/scwang/smart/refresh/footer/BallPulseFooter;", "getBallPulseFooter", "()Lcom/scwang/smart/refresh/footer/BallPulseFooter;", "setBallPulseFooter", "(Lcom/scwang/smart/refresh/footer/BallPulseFooter;)V", "baseVm", "getBaseVm", "()Lcom/wanandroid/bridge/base/BaseViewModel;", "setBaseVm", "(Lcom/wanandroid/bridge/base/BaseViewModel;)V", "Lcom/wanandroid/bridge/base/BaseViewModel;", "loadService", "Lcom/kingja/loadsir/core/LoadService;", "getLoadService", "()Lcom/kingja/loadsir/core/LoadService;", "setLoadService", "(Lcom/kingja/loadsir/core/LoadService;)V", "mBundle", "Landroid/os/Bundle;", "getMBundle", "()Landroid/os/Bundle;", "setMBundle", "(Landroid/os/Bundle;)V", "mToolbar", "Lcom/zhixinhuixue/library/widget/custom/CustomToolbar;", "getMToolbar", "()Lcom/zhixinhuixue/library/widget/custom/CustomToolbar;", "setMToolbar", "(Lcom/zhixinhuixue/library/widget/custom/CustomToolbar;)V", "materialHeader", "Lcom/scwang/smart/refresh/header/MaterialHeader;", "getMaterialHeader", "()Lcom/scwang/smart/refresh/header/MaterialHeader;", "setMaterialHeader", "(Lcom/scwang/smart/refresh/header/MaterialHeader;)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "refreshLayout", "Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;", "getRefreshLayout", "()Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;", "setRefreshLayout", "(Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;)V", "createFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getBaseQuickAdapter", "initAdapter", "", "initCreate", "bundle", "initLiveEventBus", "initLoadService", "view", "Landroid/view/View;", "initObserver", "initToolbar", "toolbar", "initViewMode", "onChanged", "t", "", "onCreate", "savedInstanceState", "onFinishClick", "onMenuClick", "onNetRetry", "onTitleClick", "refreshLoadStatus", "loadStatus", "", "requestType", "refreshView", "data", "showBallPulseFooter", "", "showMaterialHeader", "showToolbar", "bridge_debug"})
public abstract class BaseRefreshActivity<T extends java.lang.Object, VM extends com.wanandroid.bridge.base.BaseViewModel, A extends com.chad.library.adapter.base.BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder>> extends androidx.appcompat.app.AppCompatActivity implements com.wanandroid.bridge.refresh.BaseRefreshObserver<T>, com.zhixinhuixue.library.widget.custom.ToolbarClickListener, com.wanandroid.bridge.adapter.SimpleAdapterListener<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> {
    @org.jetbrains.annotations.Nullable()
    private android.os.Bundle mBundle;
    @org.jetbrains.annotations.NotNull()
    public A adapter;
    @org.jetbrains.annotations.NotNull()
    public VM baseVm;
    @org.jetbrains.annotations.NotNull()
    public com.zhixinhuixue.library.widget.custom.CustomToolbar mToolbar;
    @org.jetbrains.annotations.NotNull()
    public com.scwang.smart.refresh.layout.SmartRefreshLayout refreshLayout;
    @org.jetbrains.annotations.NotNull()
    public androidx.recyclerview.widget.RecyclerView recyclerView;
    @org.jetbrains.annotations.NotNull()
    public com.scwang.smart.refresh.footer.BallPulseFooter ballPulseFooter;
    @org.jetbrains.annotations.NotNull()
    public com.scwang.smart.refresh.header.MaterialHeader materialHeader;
    @org.jetbrains.annotations.NotNull()
    public com.kingja.loadsir.core.LoadService<?> loadService;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.Nullable()
    public final android.os.Bundle getMBundle() {
        return null;
    }
    
    public final void setMBundle(@org.jetbrains.annotations.Nullable()
    android.os.Bundle p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final A getAdapter() {
        return null;
    }
    
    public final void setAdapter(@org.jetbrains.annotations.NotNull()
    A p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final VM getBaseVm() {
        return null;
    }
    
    public final void setBaseVm(@org.jetbrains.annotations.NotNull()
    VM p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.zhixinhuixue.library.widget.custom.CustomToolbar getMToolbar() {
        return null;
    }
    
    public final void setMToolbar(@org.jetbrains.annotations.NotNull()
    com.zhixinhuixue.library.widget.custom.CustomToolbar p0) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final com.kingja.loadsir.core.LoadService<?> getLoadService() {
        return null;
    }
    
    public final void setLoadService(@org.jetbrains.annotations.NotNull()
    com.kingja.loadsir.core.LoadService<?> p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * 初始化
     */
    public abstract void initCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle bundle);
    
    /**
     * LiveData发生改变刷新UI
     * @param data 数据
     */
    public abstract void refreshView(@org.jetbrains.annotations.Nullable()
    java.util.List<T> data);
    
    /**
     * Adapter
     */
    @org.jetbrains.annotations.Nullable()
    public abstract A getBaseQuickAdapter();
    
    /**
     * 初始化Adapter
     */
    protected void initAdapter() {
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
     * 是否显示Toolbar 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    protected boolean showToolbar() {
        return false;
    }
    
    /**
     * liveData 跟 ViewMode 绑定   根据业务可以重写函数
     */
    protected void initObserver() {
    }
    
    /**
     * 初始化Toolbar 根据业务可以重写函数
     */
    protected void initToolbar(@org.jetbrains.annotations.NotNull()
    com.zhixinhuixue.library.widget.custom.CustomToolbar toolbar) {
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
     * Toolbar左边Finish点击事件
     */
    @java.lang.Override()
    public void onFinishClick() {
    }
    
    /**
     * Toolbar中间Title点击事件
     */
    @java.lang.Override()
    public void onTitleClick() {
    }
    
    /**
     * Toolbar右边Menu点击事件
     */
    @java.lang.Override()
    public void onMenuClick() {
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
     * LiveEventBus消息监听 根据业务可以重写函数
     */
    protected void initLiveEventBus() {
    }
    
    /**
     * 网络请求 重试
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
     * 创建Factory 根据业务可以重写函数
     */
    @org.jetbrains.annotations.NotNull()
    protected androidx.lifecycle.ViewModelProvider.Factory createFactory() {
        return null;
    }
    
    /**
     * Observer接口实现
     */
    @java.lang.Override()
    public void onChanged(@org.jetbrains.annotations.Nullable()
    java.util.List<T> t) {
    }
    
    public BaseRefreshActivity() {
        super();
    }
    
    public void onLoadMore() {
    }
    
    public void onRefresh() {
    }
    
    public void onBindItemClick(@org.jetbrains.annotations.NotNull()
    com.chad.library.adapter.base.BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> adapter, @org.jetbrains.annotations.NotNull()
    android.view.View view, T item, int position) {
    }
    
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.chad.library.adapter.base.viewholder.BaseViewHolder holder, T item, int position) {
    }
}