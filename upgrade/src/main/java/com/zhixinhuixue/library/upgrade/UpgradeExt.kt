package com.zhixinhuixue.library.upgrade

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import androidx.annotation.NonNull
import androidx.core.content.FileProvider
import java.io.File
import java.text.DecimalFormat

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
private const val DATA_TYPE = "application/vnd.android.package-archive"

/**
 * 安装apk
 *
 * @param activity 上下文
 * @param filePath 下载路径
 */
fun installApp(activity: Activity, packageName: String, @NonNull filePath: String) {
    if (filePath.endsWith(".apk")) {
        val file = File(filePath)
        val intent = Intent()
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val apkUri =
                    FileProvider.getUriForFile(activity, "${packageName}.provider", file)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                setDataAndType(
                    apkUri,
                    DATA_TYPE
                )
            } else {
                setDataAndType(
                    Uri.fromFile(file),
                    DATA_TYPE
                )
            }
        }
        activity.startActivity(intent)
    }
}

/**
 * 是否存在新版本
 *
 * @param data
 * @return
 */
fun checkNewVersion(data: ObjectBean?): Boolean {
    if (data?.versionNumber == null) return false
    data.localBean.let {
        val version: String = it.appVersion
        val versionNumber: String = data.versionNumber
        val newVersion = Version(version).compareTo(Version(versionNumber))
        return !TextUtils.isEmpty(version) && !TextUtils.isEmpty(versionNumber) && newVersion < 0
    }
}

fun getProgressSize(size: Int): String {
    val MB = 1024 * 1024//定义MB的计算常量
    val KB = 1024 //定义KB的计算常量
    val df = DecimalFormat("0")//格式化小数
    var resultSize = ""
    if (size / MB >= 1) {
        //如果当前Byte的值大于等于1MB
        resultSize = "${df.format(size / MB)}MB "
    } else if (size / KB >= 1) {
        //如果当前Byte的值大于等于1KB
        resultSize = "${df.format(size / KB)}KB "
    } else {
        resultSize = "${size}B "
    }
    return resultSize
}