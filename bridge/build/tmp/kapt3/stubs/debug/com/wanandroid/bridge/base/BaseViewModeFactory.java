package com.wanandroid.bridge.base;

import java.lang.System;

/**
 * @description:Factory基类
 * @author xcl qq:244672784
 * @Date 2020/7/5
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0001\u0010\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0016\u00a2\u0006\u0002\u0010\u000bR\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0005\u00a8\u0006\f"}, d2 = {"Lcom/wanandroid/bridge/base/BaseViewModeFactory;", "P", "Landroidx/lifecycle/ViewModelProvider$Factory;", "defaultValue", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "bridge_debug"})
public abstract class BaseViewModeFactory<P extends java.lang.Object> implements androidx.lifecycle.ViewModelProvider.Factory {
    private final P defaultValue = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
    
    public BaseViewModeFactory(P defaultValue) {
        super();
    }
}