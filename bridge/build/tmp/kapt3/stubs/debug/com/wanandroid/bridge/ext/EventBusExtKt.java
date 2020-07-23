package com.wanandroid.bridge.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u001a<\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0018\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u00010\t\u001a<\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000b2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0018\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u00010\t\u001a<\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0018\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u00010\t\u001a<\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000b2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0018\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u00010\t\u001a%\u0010\r\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u0002H\u0002\u00a2\u0006\u0002\u0010\u000f\u001a\'\u0010\r\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u0002H\u0002\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"observe", "", "T", "Lcom/jeremyliao/liveeventbus/LiveEventBus;", "key", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "block", "Lkotlin/Function1;", "Lcom/wanandroid/bridge/base/BaseLiveEventBus;", "", "observeSticky", "postEvent", "data", "(Lcom/jeremyliao/liveeventbus/LiveEventBus;ILjava/lang/Object;)V", "(Lcom/jeremyliao/liveeventbus/LiveEventBus;Ljava/lang/String;Ljava/lang/Object;)V", "bridge_debug"})
public final class EventBusExtKt {
    
    /**
     * LiveEventBus 发送消息
     */
    public static final <T extends java.lang.Object>void postEvent(@org.jetbrains.annotations.NotNull()
    com.jeremyliao.liveeventbus.LiveEventBus $this$postEvent, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.Nullable()
    java.lang.String key, T data) {
    }
    
    /**
     * LiveEventBus 发送消息
     */
    public static final <T extends java.lang.Object>void postEvent(@org.jetbrains.annotations.NotNull()
    com.jeremyliao.liveeventbus.LiveEventBus $this$postEvent, int key, T data) {
    }
    
    /**
     * LiveEventBus 接收消息
     */
    public static final <T extends java.lang.Object>void observe(@org.jetbrains.annotations.NotNull()
    com.jeremyliao.liveeventbus.LiveEventBus $this$observe, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    androidx.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.wanandroid.bridge.base.BaseLiveEventBus<T>, kotlin.Unit> block) {
    }
    
    /**
     * LiveEventBus 接收消息
     */
    public static final <T extends java.lang.Object>void observe(@org.jetbrains.annotations.NotNull()
    com.jeremyliao.liveeventbus.LiveEventBus $this$observe, int key, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    androidx.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.wanandroid.bridge.base.BaseLiveEventBus<T>, kotlin.Unit> block) {
    }
    
    /**
     * LiveEventBus 接收消息
     */
    public static final <T extends java.lang.Object>void observeSticky(@org.jetbrains.annotations.NotNull()
    com.jeremyliao.liveeventbus.LiveEventBus $this$observeSticky, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    androidx.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.wanandroid.bridge.base.BaseLiveEventBus<T>, kotlin.Unit> block) {
    }
    
    /**
     * LiveEventBus 接收消息
     */
    public static final <T extends java.lang.Object>void observeSticky(@org.jetbrains.annotations.NotNull()
    com.jeremyliao.liveeventbus.LiveEventBus $this$observeSticky, int key, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    androidx.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.wanandroid.bridge.base.BaseLiveEventBus<T>, kotlin.Unit> block) {
    }
}