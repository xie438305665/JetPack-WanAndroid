package com.wanandroid.bridge.base;

import java.lang.System;

/**
 * @description:ViewMode基类
 * @author xcl qq:244672784
 * @Date 2020/7/5
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H\u0016J&\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0012H\u0016J \u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00048F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u001b"}, d2 = {"Lcom/wanandroid/bridge/base/BaseViewModel;", "Lcom/zhixinhuixue/library/net/NetViewModel;", "()V", "_loadVm", "Landroidx/lifecycle/MutableLiveData;", "Lcom/zhixinhuixue/library/net/entity/NetStatusEntity;", "dataVm", "", "getDataVm", "()Landroidx/lifecycle/MutableLiveData;", "loadVm", "getLoadVm", "createDataVm", "onNetRequest", "", "requestType", "", "params", "", "", "requestLoadStatus", "showLoading", "", "loadStatus", "toastErrorMsg", "error", "Lcom/zhixinhuixue/library/net/error/NetException$ErrorBean;", "bridge_debug"})
public class BaseViewModel extends com.zhixinhuixue.library.net.NetViewModel {
    private final androidx.lifecycle.MutableLiveData<com.zhixinhuixue.library.net.entity.NetStatusEntity> _loadVm = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Object> getDataVm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.MutableLiveData<java.lang.Object> createDataVm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.zhixinhuixue.library.net.entity.NetStatusEntity> getLoadVm() {
        return null;
    }
    
    /**
     * 网络请求 根据业务可以重写函数
     * @param requestType @link[NetViewModel.RequestType]
     */
    public void onNetRequest(@com.zhixinhuixue.library.net.NetViewModel.RequestType()
    int requestType, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> params) {
    }
    
    /**
     * 请求加载UI
     * @param showLoading 请求时是否显示加载UI
     * @param requestType 请求类型
     * @param loadStatus 加载状态
     */
    @java.lang.Override()
    public void requestLoadStatus(boolean showLoading, @com.zhixinhuixue.library.net.NetViewModel.RequestType()
    int requestType, @com.zhixinhuixue.library.net.NetViewModel.LoadStatus()
    int loadStatus) {
    }
    
    /**
     * 错误信息Toast
     * @param error ErrorEnum
     */
    @java.lang.Override()
    public void toastErrorMsg(@org.jetbrains.annotations.NotNull()
    com.zhixinhuixue.library.net.error.NetException.ErrorBean error) {
    }
    
    public BaseViewModel() {
        super();
    }
}