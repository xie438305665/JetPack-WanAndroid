package com.wanandroid.bridge.util;

import java.lang.System;

/**
 * @description:SP工具类
 * @author xcl qq:244672784
 * @Date 2020/6/30
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J!\u0010\t\u001a\u00020\n2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\u0002\b\u000eH\u0002J\u0006\u0010\u000f\u001a\u00020\nJ(\u0010\u0010\u001a\u0002H\u0011\"\u0006\b\u0000\u0010\u0011\u0018\u00012\b\b\u0001\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0001H\u0086\b\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\n2\b\b\u0001\u0010\u0012\u001a\u00020\u0013J\u0018\u0010\u0017\u001a\u00020\n2\b\b\u0001\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0001R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0018"}, d2 = {"Lcom/wanandroid/bridge/util/SpUtils;", "", "()V", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "sharedPreferences$delegate", "Lkotlin/Lazy;", "applyValue", "", "block", "Lkotlin/Function1;", "Landroid/content/SharedPreferences$Editor;", "Lkotlin/ExtensionFunctionType;", "clear", "getValue", "T", "key", "", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "remove", "setValue", "bridge_debug"})
public final class SpUtils {
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy sharedPreferences$delegate = null;
    public static final com.wanandroid.bridge.util.SpUtils INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences getSharedPreferences() {
        return null;
    }
    
    /**
     * 设置Value
     */
    public final void setValue(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.Object defaultValue) {
    }
    
    /**
     * 扩展函数
     */
    private final void applyValue(kotlin.jvm.functions.Function1<? super android.content.SharedPreferences.Editor, kotlin.Unit> block) {
    }
    
    /**
     * 根据Key 删除
     */
    public final void remove(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    java.lang.String key) {
    }
    
    /**
     * 全部删除
     */
    public final void clear() {
    }
    
    private SpUtils() {
        super();
    }
}