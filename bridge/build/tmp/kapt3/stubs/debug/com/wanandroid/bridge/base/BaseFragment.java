package com.wanandroid.bridge.base;

import java.lang.System;

/**
 * @description:Fragment基类
 * @author xcl qq:244672784
 * @Date 2020/7/5
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020!H&J\u001a\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&J\u0014\u0010&\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\'\u001a\u00020%H\u0014J\b\u0010(\u001a\u00020#H\u0016J\r\u0010)\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0016J\u0012\u0010-\u001a\u00020#2\b\u0010.\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010/\u001a\u0004\u0018\u00010%2\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u0001032\b\u0010.\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u00104\u001a\u00020#H\u0016J\u001a\u00105\u001a\u00020#2\u0006\u0010\'\u001a\u00020%2\b\u0010.\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u00106\u001a\u00020#2\u0006\u00107\u001a\u00020!2\u0006\u00108\u001a\u00020!H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u00028\u0001X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u00a8\u00069"}, d2 = {"Lcom/wanandroid/bridge/base/BaseFragment;", "T", "VM", "Lcom/wanandroid/bridge/base/BaseViewModel;", "Landroidx/fragment/app/Fragment;", "()V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "baseVm", "getBaseVm", "()Lcom/wanandroid/bridge/base/BaseViewModel;", "setBaseVm", "(Lcom/wanandroid/bridge/base/BaseViewModel;)V", "Lcom/wanandroid/bridge/base/BaseViewModel;", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "setBundle", "(Landroid/os/Bundle;)V", "loadService", "Lcom/kingja/loadsir/core/LoadService;", "getLoadService", "()Lcom/kingja/loadsir/core/LoadService;", "setLoadService", "(Lcom/kingja/loadsir/core/LoadService;)V", "createFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getLayoutId", "", "initCreate", "", "root", "Landroid/view/View;", "initLoadService", "view", "initObserver", "initViewMode", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onNetRetry", "onViewCreated", "refreshLoadStatus", "loadStatus", "requestType", "bridge_debug"})
public abstract class BaseFragment<T extends java.lang.Object, VM extends com.wanandroid.bridge.base.BaseViewModel> extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private android.os.Bundle bundle;
    @org.jetbrains.annotations.NotNull()
    public VM baseVm;
    @org.jetbrains.annotations.NotNull()
    public com.kingja.loadsir.core.LoadService<?> loadService;
    @org.jetbrains.annotations.NotNull()
    public android.app.Activity activity;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.Nullable()
    public final android.os.Bundle getBundle() {
        return null;
    }
    
    public final void setBundle(@org.jetbrains.annotations.Nullable()
    android.os.Bundle p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final VM getBaseVm() {
        return null;
    }
    
    public final void setBaseVm(@org.jetbrains.annotations.NotNull()
    VM p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.kingja.loadsir.core.LoadService<?> getLoadService() {
        return null;
    }
    
    public final void setLoadService(@org.jetbrains.annotations.NotNull()
    com.kingja.loadsir.core.LoadService<?> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Activity getActivity() {
        return null;
    }
    
    public final void setActivity(@org.jetbrains.annotations.NotNull()
    android.app.Activity p0) {
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
     * 布局Id
     */
    public abstract int getLayoutId();
    
    /**
     * 初始化
     */
    public abstract void initCreate(@org.jetbrains.annotations.NotNull()
    android.view.View root, @org.jetbrains.annotations.Nullable()
    android.os.Bundle bundle);
    
    /**
     * liveData 跟 ViewMode 绑定   根据业务可以重写函数
     */
    public void initObserver() {
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
     * 网络请求重试 根据业务可以重写函数
     */
    public void onNetRetry() {
    }
    
    /**
     * 获取ViewMode 根据业务可以重写函数
     */
    @org.jetbrains.annotations.NotNull()
    public VM initViewMode() {
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
    public androidx.lifecycle.ViewModelProvider.Factory createFactory() {
        return null;
    }
    
    public BaseFragment() {
        super();
    }
}