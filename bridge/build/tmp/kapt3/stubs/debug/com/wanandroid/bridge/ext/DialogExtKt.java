package com.wanandroid.bridge.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0005\u001aX\u0010\u0006\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e\u001aP\u0010\u0006\u001a\u00020\u0003*\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e\u001a\u0014\u0010\u0011\u001a\u00020\u0003*\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n\u001a\u0014\u0010\u0011\u001a\u00020\u0003*\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"loadingDialog", "Lcom/afollestad/materialdialogs/MaterialDialog;", "dismissLoading", "", "Landroid/app/Activity;", "Landroidx/fragment/app/Fragment;", "showDialogMessage", "Landroidx/appcompat/app/AppCompatActivity;", "activity", "message", "", "title", "positiveButtonText", "positiveAction", "Lkotlin/Function0;", "negativeButtonText", "negativeAction", "showLoading", "bridge_debug"})
public final class DialogExtKt {
    
    /**
     * ***************************************loading框
     */
    private static com.afollestad.materialdialogs.MaterialDialog loadingDialog;
    
    /**
     * 显示消息弹窗
     * @param message 显示对话框的内容 必填项
     * @param title 显示对话框的标题 默认 温馨提示
     * @param positiveButtonText 确定按钮文字 默认确定
     * @param positiveAction 点击确定按钮触发的方法 默认空方法
     * @param negativeButtonText 取消按钮文字 默认空 不为空时显示该按钮
     * @param negativeAction 点击取消按钮触发的方法 默认空方法
     */
    public static final void showDialogMessage(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity $this$showDialogMessage, @org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String positiveButtonText, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> positiveAction, @org.jetbrains.annotations.NotNull()
    java.lang.String negativeButtonText, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> negativeAction) {
    }
    
    /**
     * @param message 显示对话框的内容 必填项
     * @param title 显示对话框的标题 默认 温馨提示
     * @param positiveButtonText 确定按钮文字 默认确定
     * @param positiveAction 点击确定按钮触发的方法 默认空方法
     * @param negativeButtonText 取消按钮文字 默认空 不为空时显示该按钮
     * @param negativeAction 点击取消按钮触发的方法 默认空方法
     */
    public static final void showDialogMessage(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showDialogMessage, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String positiveButtonText, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> positiveAction, @org.jetbrains.annotations.NotNull()
    java.lang.String negativeButtonText, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> negativeAction) {
    }
    
    /**
     * 打开等待框
     */
    public static final void showLoading(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity $this$showLoading, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    /**
     * 打开等待框
     */
    public static final void showLoading(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showLoading, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    /**
     * 关闭等待框
     */
    public static final void dismissLoading(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$dismissLoading) {
    }
    
    /**
     * 关闭等待框
     */
    public static final void dismissLoading(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$dismissLoading) {
    }
}