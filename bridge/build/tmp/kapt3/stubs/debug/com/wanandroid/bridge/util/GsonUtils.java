package com.wanandroid.bridge.util;

import java.lang.System;

/**
 * @description:Gson工具类
 * @author xcl qq:244672784
 * @Date 2020/7/1
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u000e\u001a\u00020\u000f\"\u0006\b\u0000\u0010\u0010\u0018\u00012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u0002H\u0010\u0018\u00010\u0012H\u0086\b\u00a2\u0006\u0002\u0010\u0013J!\u0010\u0014\u001a\u00020\u000f\"\u0006\b\u0000\u0010\u0010\u0018\u00012\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u0002H\u0010\u0018\u00010\u0016H\u0086\bJ/\u0010\u0017\u001a\u00020\u000f\"\u0006\b\u0000\u0010\u0018\u0018\u0001\"\u0006\b\u0001\u0010\u0019\u0018\u00012\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001bH\u0086\bJ2\u0010\u001c\u001a\u0004\u0018\u0001H\u0010\"\u0006\b\u0000\u0010\u0010\u0018\u00012\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u0002H\u0010\u0018\u00010\u001fH\u0086\b\u00a2\u0006\u0002\u0010 J\u0010\u0010!\u001a\u00020\u000f2\b\u0010\"\u001a\u0004\u0018\u00010\u0001J7\u0010#\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0016\"\u0006\b\u0000\u0010\u0010\u0018\u00012\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u0014\u0010$\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0010\u0018\u00010\u00160%H\u0086\bJM\u0010&\u001a\u0010\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001b\"\u0006\b\u0000\u0010\u0018\u0018\u0001\"\u0006\b\u0001\u0010\u0019\u0018\u00012\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u001a\u0010$\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001b0%H\u0086\bR\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\'"}, d2 = {"Lcom/wanandroid/bridge/util/GsonUtils;", "", "()V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "gson$delegate", "Lkotlin/Lazy;", "gsonBuilder", "Lcom/google/gson/GsonBuilder;", "getGsonBuilder", "()Lcom/google/gson/GsonBuilder;", "gsonBuilder$delegate", "arrayToValue", "", "T", "paramArrayOfT", "", "([Ljava/lang/Object;)Ljava/lang/String;", "listToValue", "paramList", "", "mapToValue", "K", "V", "paramMap", "", "toClazz", "paramString", "paramClass", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "toJson", "paramObject", "toList", "paramTypeToken", "Lcom/google/gson/reflect/TypeToken;", "toMap", "bridge_debug"})
public final class GsonUtils {
    private static final kotlin.Lazy gsonBuilder$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy gson$delegate = null;
    public static final com.wanandroid.bridge.util.GsonUtils INSTANCE = null;
    
    private final com.google.gson.GsonBuilder getGsonBuilder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.gson.Gson getGson() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toJson(@org.jetbrains.annotations.Nullable()
    java.lang.Object paramObject) {
        return null;
    }
    
    private GsonUtils() {
        super();
    }
}