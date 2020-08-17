package com.wanandroid.bridge.ext

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.hjq.toast.ToastUtils
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.util.GsonUtils
import com.wanandroid.bridge.util.SpUtils
import com.wanandroid.room.DbDatabase
import com.wanandroid.room.entity.HistoryEntity
import com.zhixinhuixue.library.net.entity.UserInfoEntity
import java.lang.reflect.ParameterizedType

/**
 *  @description:公用类
 *  @author xcl qq:244672784
 *  @Date 2020/6/30
 **/

/**
 * 关闭键盘
 */
fun EditText.offKeyboard() {
    val imm = appContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(
        windowToken,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

/**
 * 打开键盘
 */
fun EditText.openKeyboard() {
    apply {
        isFocusable = true
        isFocusableInTouchMode = true
        requestFocus()
    }
    val inputManager =
        appContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(this, 0)
}

/**
 * 关闭键盘焦点
 */
fun Activity.forceOffKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive && currentFocus != null) {
        if (currentFocus?.windowToken != null) {
            imm.hideSoftInputFromWindow(
                currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}

/**
 * 解决WebView 升级AndroidX 出现白屏问题
 * @param context Context
 * @return Context
 */
fun getFixedContext(context: Context): Context {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        context.createConfigurationContext(Configuration())
    } else context
}

fun toStartActivity(@NonNull clz: Class<*>) {
    appContext.startActivity(Intent(appContext, clz).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    })
}

fun toStartActivity(@NonNull clz: Class<*>, @NonNull bundle: Bundle) {
    appContext.startActivity(Intent(appContext, clz).apply {
        putExtras(bundle)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    })
}

fun Activity.toStartActivity(@NonNull clz: Class<*>, code: Int, @NonNull bundle: Bundle) {
    startActivityForResult(Intent(appContext, clz).putExtras(bundle), code)
}

fun Fragment.toStartActivity(@NonNull clz: Class<*>, code: Int, @NonNull bundle: Bundle) {
    startActivityForResult(Intent(appContext, clz).putExtras(bundle), code)
}

fun Activity.toStartActivity(@NonNull intent: Intent, code: Int) {
    startActivityForResult(intent, code)
}

fun toStartActivity(routerPath: String) {
    ARouter.getInstance().build(routerPath).navigation()
}

fun Activity.sendStartActivity(title: String?, text: String?,code: Int) {
    startActivityForResult(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
        type = "application/*"
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, text)
    }, "分享链接"), code)
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

fun Any.toJson() = GsonUtils.toJson(this)

fun Int.isEquals(value: Int) = value == this

fun Int.getDrawable(): Drawable? = ContextCompat.getDrawable(appContext, this)

fun Int.getColor() = ContextCompat.getColor(appContext, this)

fun Int.getString() = appContext.resources.getString(this)

fun Int.getStringArray(): Array<String> = appContext.resources.getStringArray(this)

fun Int.getIntArray() = appContext.resources.getIntArray(this)

fun Int.getDimension() = appContext.resources.getDimension(this)


/**
 * 设置列表动画
 * @param animation Int
 * @return BaseQuickAdapter.AnimationType
 */
fun getAnimationType(animation: Int): BaseQuickAdapter.AnimationType {
    return when (animation) {
        0 -> BaseQuickAdapter.AnimationType.AlphaIn
        1 -> BaseQuickAdapter.AnimationType.ScaleIn
        2 -> BaseQuickAdapter.AnimationType.SlideInBottom
        3 -> BaseQuickAdapter.AnimationType.SlideInLeft
        else -> BaseQuickAdapter.AnimationType.SlideInRight
    }
}

