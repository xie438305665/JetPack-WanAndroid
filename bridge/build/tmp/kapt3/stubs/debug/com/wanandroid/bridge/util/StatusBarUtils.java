package com.wanandroid.bridge.util;

import java.lang.System;

/**
 * @description:修改状态栏背景色
 * @author xcl qq:244672784
 * @Date 2020/7/1
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0010\u001a\u00020\fJ\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u000eH\u0002J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\fH\u0002J,\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00042\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0010\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/wanandroid/bridge/util/StatusBarUtils;", "", "()V", "DARK_CONTENT", "", "LIGHT_CONTENT", "TAG", "darkStyle", "", "activity", "Landroid/app/Activity;", "darkColor", "", "isMiUI", "", "lightStyle", "lightColor", "miUIStatusBar", "dark", "setColor", "id", "setStyle", "style", "Utils", "bridge_debug"})
public final class StatusBarUtils {
    private static final java.lang.String LIGHT_CONTENT = "light-content";
    private static final java.lang.String DARK_CONTENT = "dark-content";
    private static final java.lang.String TAG = "StatusBarUtils";
    public static final com.wanandroid.bridge.util.StatusBarUtils INSTANCE = null;
    
    public final void darkStyle(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @androidx.annotation.ColorInt()
    int darkColor) {
    }
    
    public final void lightStyle(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @androidx.annotation.ColorInt()
    int lightColor) {
    }
    
    private final void setStyle(android.app.Activity activity, java.lang.String style, @androidx.annotation.ColorInt()
    int darkColor, @androidx.annotation.ColorInt()
    int lightColor) {
    }
    
    private final void setColor(android.app.Activity activity, int id) {
    }
    
    private final boolean isMiUI() {
        return false;
    }
    
    private final void miUIStatusBar(android.app.Activity activity, boolean dark) {
    }
    
    private StatusBarUtils() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\t\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0011\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014\u00a8\u0006\u0017"}, d2 = {"Lcom/wanandroid/bridge/util/StatusBarUtils$Utils;", "", "()V", "KEY_EMUI_API_LEVEL", "", "KEY_EMUI_CONFIG_HW_SYS_VERSION", "KEY_EMUI_VERSION", "KEY_MIUI_INTERNAL_STORAGE", "KEY_MIUI_VERSION_CODE", "KEY_MIUI_VERSION_NAME", "SYS_EMUI", "SYS_FLYME", "SYS_MIUI", "isLollipop_Mr1", "", "()Z", "isM", "isMiUI", "system", "getSystem", "()Ljava/lang/String;", "systemProperty", "getSystemProperty", "bridge_debug"})
    static final class Utils {
        private static final java.lang.String SYS_EMUI = "sys_emui";
        private static final java.lang.String SYS_MIUI = "sys_miui";
        private static final java.lang.String SYS_FLYME = "sys_flyme";
        private static final java.lang.String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
        private static final java.lang.String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
        private static final java.lang.String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
        private static final java.lang.String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
        private static final java.lang.String KEY_EMUI_VERSION = "ro.build.version.emui";
        private static final java.lang.String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";
        public static final com.wanandroid.bridge.util.StatusBarUtils.Utils INSTANCE = null;
        
        public final boolean isLollipop_Mr1() {
            return false;
        }
        
        public final boolean isM() {
            return false;
        }
        
        public final boolean isMiUI() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSystem() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSystemProperty() {
            return null;
        }
        
        private Utils() {
            super();
        }
    }
}