package com.wanandroid.bridge.base;

import java.lang.System;

/**
 * @description:LiveEventBus 数据携带者   key作为唯一标识
 * @author xcl qq:244672784
 * @Date 2020/7/11
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0006R\u0013\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/wanandroid/bridge/base/BaseLiveEventBus;", "T", "Lcom/jeremyliao/liveeventbus/core/LiveEvent;", "key", "", "data", "(ILjava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getKey", "()I", "bridge_debug"})
public final class BaseLiveEventBus<T extends java.lang.Object> implements com.jeremyliao.liveeventbus.core.LiveEvent {
    private final int key = 0;
    private final T data = null;
    
    public final int getKey() {
        return 0;
    }
    
    public final T getData() {
        return null;
    }
    
    public BaseLiveEventBus(int key, T data) {
        super();
    }
}