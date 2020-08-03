package com.wanandroid.bridge.refresh;

import java.lang.System;

/**
 * @description:Activity基类
 * @author xcl qq:244672784
 * @Date 2020/7/5
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0011\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u0003*\u0014\b\u0002\u0010\u0004*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\u00052\u00020\u00072\b\u0012\u0004\u0012\u0002H\u00010\b2\u00020\t2\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\nB\u0005\u00a2\u0006\u0002\u0010\u000bJ\b\u0010M\u001a\u00020NH\u0014J\u000f\u0010O\u001a\u0004\u0018\u00018\u0002H&\u00a2\u0006\u0002\u0010%J\b\u0010P\u001a\u00020QH\u0014J\u0012\u0010R\u001a\u00020Q2\b\u0010S\u001a\u0004\u0018\u00010*H&J\b\u0010T\u001a\u00020QH\u0014J\u0014\u0010U\u001a\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010V\u001a\u00020WH\u0014J\b\u0010X\u001a\u00020QH\u0014J\u0010\u0010Y\u001a\u00020Q2\u0006\u0010Z\u001a\u000200H\u0014J\r\u0010[\u001a\u00028\u0001H\u0014\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\\\u001a\u00020Q2\u000e\u0010]\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010^H\u0016J\u0012\u0010_\u001a\u00020Q2\b\u0010`\u001a\u0004\u0018\u00010*H\u0014J\b\u0010a\u001a\u00020QH\u0016J\b\u0010b\u001a\u00020QH\u0016J\b\u0010c\u001a\u00020QH\u0016J\b\u0010d\u001a\u00020QH\u0014J\b\u0010e\u001a\u00020QH\u0016J\b\u0010f\u001a\u00020QH\u0016J\u001c\u0010g\u001a\u00020Q2\b\b\u0002\u0010h\u001a\u00020<2\b\b\u0002\u0010i\u001a\u00020<H\u0014J\u0018\u0010j\u001a\u00020Q2\u000e\u0010k\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010^H\u0014J\b\u0010l\u001a\u00020\u0019H\u0014J\b\u0010m\u001a\u00020\u0019H\u0014J\b\u0010n\u001a\u00020\u0019H\u0014R\u001a\u0010\f\u001a\u00020\rX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u00028\u0001X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u00028\u0002X\u0086.\u00a2\u0006\u0010\n\u0002\u0010(\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000206X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001a\u0010A\u001a\u00020BX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010G\u001a\u00020HX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010L\u00a8\u0006o"}, d2 = {"Lcom/wanandroid/bridge/refresh/RefreshActivity;", "T", "VM", "Lcom/wanandroid/bridge/base/BaseViewModel;", "A", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/wanandroid/bridge/refresh/RefreshObserver;", "Lcom/zhixinhuixue/library/widget/custom/ToolbarClickListener;", "Lcom/wanandroid/bridge/adapter/SimpleAdapterListener;", "()V", "ballPulseFooter", "Lcom/scwang/smart/refresh/footer/BallPulseFooter;", "getBallPulseFooter", "()Lcom/scwang/smart/refresh/footer/BallPulseFooter;", "setBallPulseFooter", "(Lcom/scwang/smart/refresh/footer/BallPulseFooter;)V", "baseVm", "getBaseVm", "()Lcom/wanandroid/bridge/base/BaseViewModel;", "setBaseVm", "(Lcom/wanandroid/bridge/base/BaseViewModel;)V", "Lcom/wanandroid/bridge/base/BaseViewModel;", "isLoading", "", "()Z", "setLoading", "(Z)V", "loadService", "Lcom/kingja/loadsir/core/LoadService;", "getLoadService", "()Lcom/kingja/loadsir/core/LoadService;", "setLoadService", "(Lcom/kingja/loadsir/core/LoadService;)V", "mAdapter", "getMAdapter", "()Lcom/chad/library/adapter/base/BaseQuickAdapter;", "setMAdapter", "(Lcom/chad/library/adapter/base/BaseQuickAdapter;)V", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "mBundle", "Landroid/os/Bundle;", "getMBundle", "()Landroid/os/Bundle;", "setMBundle", "(Landroid/os/Bundle;)V", "mToolbar", "Lcom/zhixinhuixue/library/widget/custom/CustomToolbar;", "getMToolbar", "()Lcom/zhixinhuixue/library/widget/custom/CustomToolbar;", "setMToolbar", "(Lcom/zhixinhuixue/library/widget/custom/CustomToolbar;)V", "materialHeader", "Lcom/scwang/smart/refresh/header/MaterialHeader;", "getMaterialHeader", "()Lcom/scwang/smart/refresh/header/MaterialHeader;", "setMaterialHeader", "(Lcom/scwang/smart/refresh/header/MaterialHeader;)V", "page", "", "getPage", "()I", "setPage", "(I)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "refreshLayout", "Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;", "getRefreshLayout", "()Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;", "setRefreshLayout", "(Lcom/scwang/smart/refresh/layout/SmartRefreshLayout;)V", "createFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getBaseQuickAdapter", "initAdapter", "", "initCreate", "bundle", "initLiveEventBus", "initLoadService", "view", "Landroid/view/View;", "initObserver", "initToolbar", "toolbar", "initViewMode", "onChanged", "t", "", "onCreate", "savedInstanceState", "onFinishClick", "onLoadMore", "onMenuClick", "onNetRetry", "onRefresh", "onTitleClick", "refreshLoadStatus", "loadStatus", "requestType", "refreshView", "data", "showBallPulseFooter", "showMaterialHeader", "showToolbar", "bridge_debug"})
public abstract class RefreshActivity<T extends java.lang.Object, VM extends com.wanandroid.bridge.base.BaseViewModel, A extends com.chad.library.adapter.base.BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder>> extends androidx.appcompat.app.AppCompatActivity implements com.wanandroid.bridge.refresh.RefreshObserver<T>, com.zhixinhuixue.library.widget.custom.ToolbarClickListener, com.wanandroid.bridge.adapter.SimpleAdapterListener<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> {
    private int page = 0;
    @org.jetbrains.annotations.Nullable()
    private android.os.Bundle mBundle;
    private boolean isLoading = false;
    @org.jetbrains.annotations.NotNull()
    public A mAdapter;
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
    
    public final int getPage() {
        return 0;
    }
    
    public final void setPage(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.os.Bundle getMBundle() {
        return null;
    }
    
    public final void setMBundle(@org.jetbrains.annotations.Nullable()
    android.os.Bundle p0) {
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final void setLoading(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final A getMAdapter() {
        return null;
    }
    
    public final void setMAdapter(@org.jetbrains.annotations.NotNull()
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
    protected void refreshView(@org.jetbrains.annotations.Nullable()
    java.util.List<T> data) {
    }
    
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
    
    @java.lang.Override()
    public void onRefresh() {
    }
    
    @java.lang.Override()
    public void onLoadMore() {
    }
    
    public RefreshActivity() {
        super();
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