package com.wanandroid.bridge.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001aZ\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u000b\u001a\u00020\f\u001aZ\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u000b\u001a\u00020\f\u00a8\u0006\u000e"}, d2 = {"showDialogMessage", "", "Landroidx/appcompat/app/AppCompatActivity;", "message", "", "title", "positiveButtonText", "positiveAction", "Lkotlin/Function0;", "negativeButtonText", "negativeAction", "cancelable", "", "Landroidx/fragment/app/Fragment;", "bridge_debug"})
public final class DialogExtKt {
    
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
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String positiveButtonText, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> positiveAction, @org.jetbrains.annotations.NotNull()
    java.lang.String negativeButtonText, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> negativeAction, boolean cancelable) {
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
    kotlin.jvm.functions.Function0<kotlin.Unit> negativeAction, boolean cancelable) {
    }
}