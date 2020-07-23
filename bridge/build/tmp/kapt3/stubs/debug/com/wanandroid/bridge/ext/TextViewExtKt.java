package com.wanandroid.bridge.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\u0007\u001a\n\u0010\r\u001a\u00020\f*\u00020\u0007\u001a\u0012\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f\u001a\u0014\u0010\u0010\u001a\u00020\u0001*\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t\u001a\u0014\u0010\u0011\u001a\u00020\u0001*\u00020\u00072\b\b\u0001\u0010\u0012\u001a\u00020\t\u001a\n\u0010\u0013\u001a\u00020\u0005*\u00020\u0007\u001a\n\u0010\u0014\u001a\u00020\u0005*\u00020\u0007\u001a\u0014\u0010\u0015\u001a\u00020\u0001*\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t\u00a8\u0006\u0016"}, d2 = {"afterTextChange", "", "Landroid/widget/EditText;", "afterTextChanged", "Lkotlin/Function1;", "", "bottomCompoundDrawablesRelative", "Landroid/widget/TextView;", "drawableRes", "", "endCompoundDrawablesRelative", "isEmpty", "", "isTrimEmpty", "showPwd", "isChecked", "startCompoundDrawablesRelative", "textAppearance", "id", "textString", "textStringTrim", "topCompoundDrawablesRelative", "bridge_debug"})
public final class TextViewExtKt {
    
    /**
     * 优化输入框 只抽取afterTextChanged方法
     */
    public static final void afterTextChange(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$afterTextChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> afterTextChanged) {
    }
    
    /**
     * 获取文本
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String textString(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$textString) {
        return null;
    }
    
    /**
     * 获取去除空字符串的文本
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String textStringTrim(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$textStringTrim) {
        return null;
    }
    
    /**
     * 文本是否为空
     */
    public static final boolean isEmpty(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$isEmpty) {
        return false;
    }
    
    /**
     * 去空字符串后文本是否为空
     */
    public static final boolean isTrimEmpty(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$isTrimEmpty) {
        return false;
    }
    
    /**
     * 设置start Drawable
     * @receiver TextView
     * @param drawableRes Int
     */
    public static final void startCompoundDrawablesRelative(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$startCompoundDrawablesRelative, @androidx.annotation.DrawableRes()
    int drawableRes) {
    }
    
    /**
     * 设置end Drawable
     * @receiver TextView
     * @param drawableRes Int
     */
    public static final void endCompoundDrawablesRelative(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$endCompoundDrawablesRelative, @androidx.annotation.DrawableRes()
    int drawableRes) {
    }
    
    /**
     * 设置top Drawable
     * @receiver TextView
     * @param drawableRes Int
     */
    public static final void topCompoundDrawablesRelative(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$topCompoundDrawablesRelative, @androidx.annotation.DrawableRes()
    int drawableRes) {
    }
    
    /**
     * 设置bottom Drawable
     * @receiver TextView
     * @param drawableRes Int
     */
    public static final void bottomCompoundDrawablesRelative(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$bottomCompoundDrawablesRelative, @androidx.annotation.DrawableRes()
    int drawableRes) {
    }
    
    /**
     * @receiver TextView
     * @param id Int
     */
    public static final void textAppearance(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$textAppearance, @androidx.annotation.StyleRes()
    int id) {
    }
    
    public static final void showPwd(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$showPwd, boolean isChecked) {
    }
}