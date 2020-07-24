package com.wanandroid.bridge.base;

import java.lang.System;

/**
 * @description:WebView
 * @author xcl qq:244672784
 * @date 2020/7/23
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0001\'B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nB\'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0003J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010\u001b\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u001aJ\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\tH\u0002J(\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\tH\u0014J\u0006\u0010$\u001a\u00020\u0012J\u000e\u0010%\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010&\u001a\u00020\u0012R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/wanandroid/bridge/base/BaseWebView;", "Landroid/webkit/WebView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "listener", "Lcom/wanandroid/bridge/base/BaseWebView$PageFinishedListener;", "progressbar", "Landroid/widget/ProgressBar;", "canChildScrollUp", "", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "init", "javaScriptData", "params", "", "funName", "", "loadDataUrl", "url", "newProgressBar", "newProgress", "onScrollChanged", "l", "t", "oldl", "oldt", "openProgress", "registerFinishedListener", "reset", "PageFinishedListener", "bridge_debug"})
public final class BaseWebView extends android.webkit.WebView {
    private android.widget.ProgressBar progressbar;
    private com.wanandroid.bridge.base.BaseWebView.PageFinishedListener listener;
    private java.util.HashMap _$_findViewCache;
    
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    private final void init() {
    }
    
    public final void openProgress() {
    }
    
    private final void newProgressBar(int newProgress) {
    }
    
    @java.lang.Override()
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    }
    
    /**
     * 加载html
     * @param url
     */
    public final void loadDataUrl(@org.jetbrains.annotations.Nullable()
    java.lang.String url) {
    }
    
    /**
     * 与JS交互
     * @param params Any 传参
     * @param funName String  交互定义的方法名
     */
    public final void javaScriptData(@org.jetbrains.annotations.NotNull()
    java.lang.Object params, @org.jetbrains.annotations.NotNull()
    java.lang.String funName) {
    }
    
    /**
     * 解决SwipeRefreshLayout 滑动冲突
     * @param swipeRefreshLayout SwipeRefreshLayout?
     */
    public final void canChildScrollUp(@org.jetbrains.annotations.Nullable()
    androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefreshLayout) {
    }
    
    /**
     * WebView加载完成回调监听
     * @param listener PageFinishedListener
     */
    public final void registerFinishedListener(@org.jetbrains.annotations.NotNull()
    com.wanandroid.bridge.base.BaseWebView.PageFinishedListener listener) {
    }
    
    /**
     * 销毁WebView
     */
    public final void reset() {
    }
    
    public BaseWebView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public BaseWebView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attributeSet) {
        super(null);
    }
    
    public BaseWebView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attributeSet, int defStyleAttr) {
        super(null);
    }
    
    public BaseWebView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lcom/wanandroid/bridge/base/BaseWebView$PageFinishedListener;", "", "onPageFinished", "", "bridge_debug"})
    public static abstract interface PageFinishedListener {
        
        public abstract void onPageFinished();
    }
}