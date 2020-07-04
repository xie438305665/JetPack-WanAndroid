package com.zhixinhuixue.library.upgrade

import com.google.gson.annotations.SerializedName
import java.io.File

/**
 * @Author: cock
 * @Date: 2020/06/30
 * @Description:
 */
data class UpgradeEntity(
    val message: String,
    val success: Int,
    @SerializedName("object") val data: ObjectBean?
)

data class ObjectBean(
    val change_log: String,
    val description: String,
    @SerializedName("file_size")
    val fileSize: String,
    @SerializedName("file_url")
    val fileUrl: String,
    @SerializedName("force_update")
    val forceUpdate: String,
    val id: String,
    @SerializedName("release_date_string")
    val releaseDateString: String,
    @SerializedName("release_time")
    val releaseTime: String,
    @SerializedName("software_id")
    val softwareId: String,
    @SerializedName("version_number")
    val versionNumber: String?,
    var localBean: LocalInfoBean
)

/**
 * 升级APK 需要的本地信息
 */
data class LocalInfoBean(
    // 0 进来检测版本,不给任何提示,1 点击检测,给dialog提示,2 查找是否存在新版本,适用于小红点之类的提示
    val type: Int = 0,
    //Apk版本
    val appVersion: String,
    //Apk安装路径
    val diskFileDirFile: File
)