package com.wanandroid.bridge.ext

import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes


/**
 *  @description:TextView扩展类
 *  @author xcl qq:244672784
 *  @Date 2020/6/30
 **/

/**
 * 优化输入框 只抽取afterTextChanged方法
 */
fun EditText.afterTextChange(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

/**
 * 获取文本
 */
fun TextView.textString(): String {
    return text.toString()
}
/**
 * 获取去除空字符串的文本
 */
fun TextView.textStringTrim(): String {
    return textString().trim()
}
/**
 * 文本是否为空
 */
fun TextView.isEmpty(): Boolean {
    return textString().isEmpty()
}
/**
 * 去空字符串后文本是否为空
 */
fun TextView.isTrimEmpty(): Boolean {
    return this.textStringTrim().isEmpty()
}

/**
 * 设置start Drawable
 * @receiver TextView
 * @param drawableRes Int
 */
fun TextView.startCompoundDrawablesRelative(@DrawableRes drawableRes: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        drawableRes.getDrawable(),
        null,
        null,
        null
    )
}

/**
 * 设置end Drawable
 * @receiver TextView
 * @param drawableRes Int
 */
fun TextView.endCompoundDrawablesRelative(@DrawableRes drawableRes: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        null,
        null,
        drawableRes.getDrawable(),
        null
    )
}

/**
 * 设置top Drawable
 * @receiver TextView
 * @param drawableRes Int
 */
fun TextView.topCompoundDrawablesRelative(@DrawableRes drawableRes: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        null,
        drawableRes.getDrawable(),
        null,
        null
    )
}

/**
 * 设置bottom Drawable
 * @receiver TextView
 * @param drawableRes Int
 */
fun TextView.bottomCompoundDrawablesRelative(@DrawableRes drawableRes: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        null,
        null,
        null,
        drawableRes.getDrawable()
    )
}

/**
 *
 * @receiver TextView
 * @param id Int
 */
fun TextView.textAppearance(@StyleRes id: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.setTextAppearance(id)
    } else {
        this.setTextAppearance(context, id)
    }
}

fun EditText.showPwd(isChecked:Boolean){
    inputType = if (isChecked) {
        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    } else {
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }
    setSelection(textString().length)
}