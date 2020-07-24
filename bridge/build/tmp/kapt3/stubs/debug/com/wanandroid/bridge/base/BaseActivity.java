package com.wanandroid.bridge.base;

import java.lang.System;

/**
 * @description:Activity基类
 * @author xcl qq:244672784
 * @Date 2020/7/5
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\u00020\u0006B\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010,\u001a\u00020-H\u0014J\b\u0010.\u001a\u00020/H&J\u0012\u00100\u001a\u0002012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\b\u00102\u001a\u000201H\u0014J\u0014\u00103\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u00104\u001a\u000205H\u0014J\b\u00106\u001a\u000201H\u0014J\u0010\u00107\u001a\u0002012\u0006\u00108\u001a\u00020\'H\u0014J\r\u00109\u001a\u00028\u0001H\u0014\u00a2\u0006\u0002\u0010\nJ\u0015\u0010:\u001a\u0002012\u0006\u0010;\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010<J\u0012\u0010=\u001a\u0002012\b\u0010>\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010?\u001a\u000201H\u0016J\b\u0010@\u001a\u000201H\u0016J\b\u0010A\u001a\u000201H\u0014J\b\u0010B\u001a\u000201H\u0016J\u001c\u0010C\u001a\u0002012\b\b\u0002\u0010D\u001a\u00020/2\b\b\u0002\u0010E\u001a\u00020/H\u0016J\u0017\u0010F\u001a\u0002012\b\u0010G\u001a\u0004\u0018\u00018\u0000H&\u00a2\u0006\u0002\u0010<J\b\u0010H\u001a\u00020IH\u0014J\b\u0010J\u001a\u00020IH\u0014R\u001c\u0010\b\u001a\u00028\u0001X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0015X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\'X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+\u00a8\u0006K"}, d2 = {"Lcom/wanandroid/bridge/base/BaseActivity;", "T", "VM", "Lcom/wanandroid/bridge/base/BaseViewModel;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroidx/lifecycle/Observer;", "Lcom/zhixinhuixue/library/widget/custom/ToolbarClickListener;", "()V", "baseVm", "getBaseVm", "()Lcom/wanandroid/bridge/base/BaseViewModel;", "setBaseVm", "(Lcom/wanandroid/bridge/base/BaseViewModel;)V", "Lcom/wanandroid/bridge/base/BaseViewModel;", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "setBundle", "(Landroid/os/Bundle;)V", "loadService", "Lcom/kingja/loadsir/core/LoadService;", "getLoadService", "()Lcom/kingja/loadsir/core/LoadService;", "setLoadService", "(Lcom/kingja/loadsir/core/LoadService;)V", "mDrawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "getMDrawerLayout", "()Landroidx/drawerlayout/widget/DrawerLayout;", "setMDrawerLayout", "(Landroidx/drawerlayout/widget/DrawerLayout;)V", "mDrawerMenu", "Landroidx/recyclerview/widget/RecyclerView;", "getMDrawerMenu", "()Landroidx/recyclerview/widget/RecyclerView;", "setMDrawerMenu", "(Landroidx/recyclerview/widget/RecyclerView;)V", "mToolbar", "Lcom/zhixinhuixue/library/widget/custom/CustomToolbar;", "getMToolbar", "()Lcom/zhixinhuixue/library/widget/custom/CustomToolbar;", "setMToolbar", "(Lcom/zhixinhuixue/library/widget/custom/CustomToolbar;)V", "createFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getLayoutId", "", "initCreate", "", "initLiveEventBus", "initLoadService", "view", "Landroid/view/View;", "initObserver", "initToolbar", "toolbar", "initViewMode", "onChanged", "t", "(Ljava/lang/Object;)V", "onCreate", "savedInstanceState", "onFinishClick", "onMenuClick", "onNetRetry", "onTitleClick", "refreshLoadStatus", "loadStatus", "requestType", "refreshView", "data", "showDrawerMenu", "", "showToolbar", "bridge_debug"})
public abstract class BaseActivity<T extends java.lang.Object, VM extends com.wanandroid.bridge.base.BaseViewModel> extends androidx.appcompat.app.AppCompatActivity implements androidx.lifecycle.Observer<T>, com.zhixinhuixue.library.widget.custom.ToolbarClickListener {
    @org.jetbrains.annotations.NotNull()
    public VM baseVm;
    @org.jetbrains.annotations.NotNull()
    public com.zhixinhuixue.library.widget.custom.CustomToolbar mToolbar;
    @org.jetbrains.annotations.NotNull()
    public androidx.drawerlayout.widget.DrawerLayout mDrawerLayout;
    @org.jetbrains.annotations.NotNull()
    public androidx.recyclerview.widget.RecyclerView mDrawerMenu;
    @org.jetbrains.annotations.NotNull()
    public com.kingja.loadsir.core.LoadService<?> loadService;
    @org.jetbrains.annotations.Nullable()
    private android.os.Bundle bundle;
    private java.util.HashMap _$_findViewCache;
    
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
    public final androidx.drawerlayout.widget.DrawerLayout getMDrawerLayout() {
        return null;
    }
    
    public final void setMDrawerLayout(@org.jetbrains.annotations.NotNull()
    androidx.drawerlayout.widget.DrawerLayout p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.recyclerview.widget.RecyclerView getMDrawerMenu() {
        return null;
    }
    
    public final void setMDrawerMenu(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.kingja.loadsir.core.LoadService<?> getLoadService() {
        return null;
    }
    
    public final void setLoadService(@org.jetbrains.annotations.NotNull()
    com.kingja.loadsir.core.LoadService<?> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.os.Bundle getBundle() {
        return null;
    }
    
    public final void setBundle(@org.jetbrains.annotations.Nullable()
    android.os.Bundle p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * 布局Id
     */
    public abstract int getLayoutId();
    
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
    T data);
    
    /**
     * 是否显示Toolbar 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    protected boolean showToolbar() {
        return false;
    }
    
    /**
     * 是否显示DrawerMenu 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    protected boolean showDrawerMenu() {
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
    public void refreshLoadStatus(@com.zhixinhuixue.library.net.NetViewModel.LoadStatus()
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
    public void onChanged(T t) {
    }
    
    public BaseActivity() {
        super();
    }
}