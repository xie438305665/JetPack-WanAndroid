package com.wanandroid.bridge.annotation;

import java.lang.System;

/**
 * ARout路径
 */
@androidx.annotation.StringDef(value = {"/app/main", "/user/login"})
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000\u00a8\u0006\u0003"}, d2 = {"Lcom/wanandroid/bridge/annotation/ARouterPath;", "", "Companion", "bridge_debug"})
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.SOURCE)
@kotlin.annotation.Retention(value = kotlin.annotation.AnnotationRetention.SOURCE)
public abstract @interface ARouterPath {
    public static final com.wanandroid.bridge.annotation.ARouterPath.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MAIN = "/app/main";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN = "/user/login";
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/wanandroid/bridge/annotation/ARouterPath$Companion;", "", "()V", "LOGIN", "", "MAIN", "bridge_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String MAIN = "/app/main";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String LOGIN = "/user/login";
        
        private Companion() {
            super();
        }
    }
}