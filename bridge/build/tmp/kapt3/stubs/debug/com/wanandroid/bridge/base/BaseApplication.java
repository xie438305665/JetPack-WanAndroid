package com.wanandroid.bridge.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 \"2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000fH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u001a\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u0019H\u0016J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010!\u001a\u00020\u0013H\u0016R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/wanandroid/bridge/base/BaseApplication;", "Landroid/app/Application;", "Landroidx/lifecycle/ViewModelStoreOwner;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "()V", "loadStatusCallbackList", "", "Lcom/wanandroid/bridge/base/BaseLoadCallback;", "getLoadStatusCallbackList", "()Ljava/util/List;", "loadStatusCallbackList$delegate", "Lkotlin/Lazy;", "mAppViewModelStore", "Landroidx/lifecycle/ViewModelStore;", "defaultCallback", "Ljava/lang/Class;", "Lcom/kingja/loadsir/callback/Callback;", "getViewModelStore", "initLoadSir", "", "liveEventBusConfig", "onActivityCreated", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "onCreate", "Companion", "bridge_debug"})
public class BaseApplication extends android.app.Application implements androidx.lifecycle.ViewModelStoreOwner, android.app.Application.ActivityLifecycleCallbacks {
    private androidx.lifecycle.ViewModelStore mAppViewModelStore;
    
    /**
     * 加载布局集合根据加载状态显示  根据业务可以重写函数
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy loadStatusCallbackList$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static com.wanandroid.bridge.base.BaseApplication instance;
    public static final com.wanandroid.bridge.base.BaseApplication.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.ViewModelStore getViewModelStore() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    /**
     * 初始化状态布局
     */
    private final void initLoadSir() {
    }
    
    /**
     * 加载布局集合根据加载状态显示  根据业务可以重写函数
     */
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.wanandroid.bridge.base.BaseLoadCallback> getLoadStatusCallbackList() {
        return null;
    }
    
    /**
     * 默认的加载布局 根据业务可以重写函数
     */
    @org.jetbrains.annotations.NotNull()
    public java.lang.Class<? extends com.kingja.loadsir.callback.Callback> defaultCallback() {
        return null;
    }
    
    /**
     * LiveEventBus配置  根据业务可以重写函数
     */
    public void liveEventBusConfig() {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityStarted(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onActivityResumed(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onActivityPaused(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onActivityStopped(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onActivityDestroyed(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onActivitySaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    android.os.Bundle outState) {
    }
    
    public BaseApplication() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/wanandroid/bridge/base/BaseApplication$Companion;", "", "()V", "instance", "Lcom/wanandroid/bridge/base/BaseApplication;", "getInstance", "()Lcom/wanandroid/bridge/base/BaseApplication;", "setInstance", "(Lcom/wanandroid/bridge/base/BaseApplication;)V", "bridge_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.wanandroid.bridge.base.BaseApplication getInstance() {
            return null;
        }
        
        public final void setInstance(@org.jetbrains.annotations.NotNull()
        com.wanandroid.bridge.base.BaseApplication p0) {
        }
        
        private Companion() {
            super();
        }
    }
}