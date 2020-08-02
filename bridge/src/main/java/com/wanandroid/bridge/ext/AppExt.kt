package com.wanandroid.bridge.ext

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import coil.util.CoilUtils
import java.io.File
import java.math.BigDecimal
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

fun Context.getDiskCacheSize(): String? {
    try {
        return getFormatSize(
            getFolderSize(
                File(
                    this.cacheDir
                        .toString() + "/" + CoilUtils.createDefaultCache(this).size()
                )
            ).toDouble()
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return "0"
}

fun getFolderSize(file: File): Long {
    var size: Long = 0
    try {
        val fileList = file.listFiles()
        for (aFileList in fileList) {
            size = if (aFileList.isDirectory) {
                size + getFolderSize(aFileList)
            } else {
                size + aFileList.length()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return size
}

fun getFormatSize(size: Double): String? {
    val kiloByte = size / 1024
    if (kiloByte < 1) {
        return 0.toString()
    }
    val megaByte = kiloByte / 1024
    if (megaByte < 1) {
        val result1 =
            BigDecimal(kiloByte.toString())
        return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB"
    }
    val gigaByte = megaByte / 1024
    if (gigaByte < 1) {
        val result2 =
            BigDecimal(megaByte.toString())
        return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB"
    }
    val teraBytes = gigaByte / 1024
    if (teraBytes < 1) {
        val result3 =
            BigDecimal(java.lang.Double.toString(gigaByte))
        return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB"
    }
    val result4 = BigDecimal(teraBytes)
    return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
}
