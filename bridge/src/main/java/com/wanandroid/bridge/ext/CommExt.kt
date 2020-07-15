package com.wanandroid.bridge.ext

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.hjq.toast.ToastUtils
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.util.GsonUtils
import java.lang.reflect.ParameterizedType

/**
 *  @description:公用类
 *  @author xcl qq:244672784
 *  @Date 2020/6/30
 **/

/**
 * 关闭键盘
 */
fun offKeyboard(editText: EditText) {
    val imm = appContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(
        editText.windowToken,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

/**
 * 打开键盘
 */
fun openKeyboard(editText: EditText) {
    editText.apply {
        isFocusable = true
        isFocusableInTouchMode = true
        requestFocus()
    }
    val inputManager =
        appContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(editText, 0)
}

/**
 * 关闭键盘焦点
 */
fun forceOffKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive && activity.currentFocus != null) {
        if (activity.currentFocus?.windowToken != null) {
            imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}

fun toStartActivity(@NonNull clz: Class<*>) {
    val intent = Intent(appContext, clz)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    appContext.startActivity(intent)
}

fun toStartActivity(@NonNull clz: Class<*>, @NonNull bundle: Bundle) {
    val intent = Intent(appContext, clz)
    intent.apply {
        putExtras(bundle)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    appContext.startActivity(intent)
}

fun toStartActivity(activity: Activity, @NonNull clz: Class<*>, code: Int, @NonNull bundle: Bundle) {
    activity.startActivityForResult(Intent(appContext, clz).putExtras(bundle), code)
}

fun toStartActivity(fragment: Fragment, @NonNull clz: Class<*>, code: Int, @NonNull bundle: Bundle) {
    fragment.startActivityForResult(Intent(appContext, clz).putExtras(bundle), code)
}

fun toStartActivity(activity: Activity, @NonNull intent: Intent, code: Int) {
    activity.startActivityForResult(intent, code)
}

fun toStartActivity(@NonNull type: Any, @NonNull clz: Class<*>, code: Int, @NonNull bundle: Bundle) {
    if (type is Activity) {
        toStartActivity(type, clz, code, bundle)
    } else if (type is Fragment) {
        toStartActivity(type, clz, code, bundle)
    }
}

/**
 * 隐藏状态栏
 */
fun hideStatusBar(activity: Activity) {
    val attrs = activity.window.attributes
    attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
    activity.window.attributes = attrs
}

/**
 * 显示状态栏
 */
fun showStatusBar(activity: Activity) {
    val attrs = activity.window.attributes
    attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
    activity.window.attributes = attrs
}

/**
 * 横竖屏
 */
fun isLandscape(context: Context) =
    context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

/**
 * 应用商店
 */
fun gotoStore() {
    val uri =
        Uri.parse("market://details?id=" + appContext.packageName)
    val goToMarket = Intent(Intent.ACTION_VIEW, uri)
    try {
        goToMarket.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        appContext.startActivity(goToMarket)
    } catch (ignored: ActivityNotFoundException) {
    }
}

/**
 * 获取VM类型
 * @param context   跟ViewModel 绑定的上下文
 * @param defaultIndex   根据反射获取泛型集合  利用index 获取当前VM类型
 * @return VM ViewModel
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(any: Any, defaultIndex: Int = 1): VM {
    var index = defaultIndex
    val arguments = (any.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
    if (arguments.isNullOrEmpty()) {
        throw NullPointerException("getVmClazz is null")
    }
    if (arguments.size < index) {
        index = arguments.size
    }
    return (any.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[index] as VM
}

/**
 * 扩展函数
 *
 */

fun String?.formHtml(flag: Int = HtmlCompat.FROM_HTML_MODE_LEGACY) =
    if (this.isNullOrEmpty()) "" else HtmlCompat.fromHtml(this, flag)

fun String?.isEquals(value: String?) =
    if (value.isNullOrEmpty() || this.isNullOrEmpty()) false else TextUtils.equals(value, this)

fun Any?.toast() {
    ToastUtils.show(this)
}

fun Any?.toJson() = GsonUtils.toJson(this)

fun Int.isEquals(value: Int) = value == this

fun Int.getDrawable(): Drawable? = ContextCompat.getDrawable(appContext, this)

fun Int.getColor() = ContextCompat.getColor(appContext, this)

fun Int.getString() = appContext.resources.getString(this)

fun Int.getStringArray(): Array<String> = appContext.resources.getStringArray(this)

fun Int.getIntArray() = appContext.resources.getIntArray(this)

fun Int.getDimension() = appContext.resources.getDimension(this)



