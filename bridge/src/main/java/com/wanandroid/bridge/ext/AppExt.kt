package com.wanandroid.bridge.ext

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import java.util.*

/**
 *  @description:APP相关
 *  @author xcl qq:244672784
 *  @Date 2020/6/30
 **/

/**
 * 获取packageName
 */
fun getPackageNameName(context: Context): String {
    try {
        val pi = context.packageManager.getPackageInfo(context.packageName, 0)
        return pi.packageName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return ""
}

/**
 * 获取versionName
 */
fun getAppVersion(context: Context): String {
    try {
        val pi = context.packageManager.getPackageInfo(context.packageName, 0)
        return pi.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return ""
}

private val activityList = LinkedList<Activity>()

//栈顶
val currentActivity: Activity? get() = if (activityList.isNullOrEmpty()) null else activityList.last

/**
 * 入栈
 */
fun addActivity(activity: Activity) {
    activityList.add(activity)
}

/**
 * 出栈
 */
fun removeActivity(activity: Activity) {
    if (!activity.isFinishing) {
        activity.finish()
    }
    activityList.remove(activity)
}

/**
 * 出栈
 */
fun removeActivity(cls: Class<*>) {
    if (activityList.isNullOrEmpty()) return
    val index = activityList.indexOfFirst { it.javaClass == cls }
    if (index == -1) return
    if (!activityList[index].isFinishing) {
        activityList[index].finish()
    }
    activityList.removeAt(index)
}

/**
 * 全部出栈
 */
fun removeAllActivity() {
    activityList.forEach {
        if (!it.isFinishing) {
            it.finish()
        }
    }
    activityList.clear()
}
