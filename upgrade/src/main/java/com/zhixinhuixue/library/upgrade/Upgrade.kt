package com.zhixinhuixue.library.upgrade

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.google.gson.Gson
import com.wanandroid.developer.library.upgrade.R
import java.io.File

/**
 *  @description:Apk升级相关类
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
class Upgrade : ApkDownloadCallback, DownloadProgressListener {
    //跟Activity/Fragment绑定
    private var findDownloadListener: FindDownloadListener? = null
    private lateinit var dataEntity: ObjectBean
    private lateinit var activity: Activity
    private lateinit var installFilePath: String

    companion object {
        val INSTANCE by lazy {
            Upgrade()
        }

        val DIALOG: DownloadProgressDialog by lazy {
            DownloadProgressDialog()
        }

        val GSON: Gson by lazy {
            Gson()
        }

        /***  进来检测版本,不给任何提示  */
        val TYPE_FIRST = 0

        /***  点击检测,给dialog提示  */
        val TYPE_CLICK = 1

        /***  查找是否存在新版本,适用于小红点之类的提示  */
        val TYPE_FIND = 2
    }

    /**
     * Upgrade版本升级前置工作
     */
    fun start(upgradeStr: String, activity: Activity, findDownloadListener: FindDownloadListener?) {
        DIALOG.dialogListener = this
        this.activity = activity
        this.dataEntity = GSON.fromJson(upgradeStr, ObjectBean::class.java)
        dataEntity.let {
            val bundle = Bundle()
            bundle.putString(UpgradeConst.UPGRADE_CONTENT_KEY, this.dataEntity.change_log)
            DIALOG.arguments = bundle
            this.findDownloadListener = findDownloadListener
            this.installFilePath =
                getApkFile(it.localBean.diskFileDirFile.path, it.versionNumber).path
            checkApkUpgrade(it, activity)
        }
    }

    /**
     * 检查Apk升级
     */
    private fun checkApkUpgrade(data: ObjectBean, activity: Activity) {
        if (!checkNewVersion(data) && data.localBean.type == TYPE_CLICK) {
            noVersionTips(activity)
            return
        }
        if (TextUtils.equals(data.forceUpdate, UpgradeConst.VERSION_FLAG)) {
            if (checkContextFinishing(activity)) return
            AlertDialog.Builder(activity)
                .setCancelable(false)
                .setTitle(R.string.upgrade_title)
                .setMessage(R.string.upgrade_tips_message)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    findDownloadListener?.downloadStart(
                        installFilePath,
                        data.fileUrl,
                        data.id
                    )
                }
                .show()
            return
        }
        findDownloadListener?.downloadStart(installFilePath, data.fileUrl, data.id)
    }

    /**
     * 没有检测到新版本 dialog提示
     */
    private fun noVersionTips(activity: Activity) {
        if (checkContextFinishing(activity)) return
        AlertDialog.Builder(activity)
            .setMessage(activity.getString(R.string.upgrade_no_tips_message))
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    /**
     * 安裝Apk
     */
    private fun installApk(data: ObjectBean, activity: Activity) {
        if (checkContextFinishing(activity)) return
        installApp(activity, UpgradeConst.APP_PACKAGE_NAME, installFilePath)
        if (!TextUtils.equals(data.forceUpdate, UpgradeConst.VERSION_FLAG)) {
            forceUpdateApk(data, activity)
        }
    }

    /**
     * 取消下载
     */
    override fun onCancelDownload() {
        dismissDialog(this.activity)
        findDownloadListener?.downloadCancel()
    }

    /**
     * 显示dialog
     */
    override fun showDialog(manager: FragmentManager, tag: String?) {
        if (DIALOG.isVisible) return
        DIALOG.show(manager, tag)
    }

    /**
     * 更新Progressbar进度
     */
    override fun updateProgress(soFarBytes: Int, totalBytes: Int) {
        DIALOG.updateProgress(soFarBytes, totalBytes)
    }

    /**
     * 更新完成进行安装
     */
    override fun updateCompleted(data: ObjectBean?) {
        dismissDialog(this.activity)
        val dataObject = data ?: this.dataEntity
        installApk(dataObject, this.activity)
    }

    /**
     * 更新失败
     */
    override fun updateError(any: Any?) {
        dismissDialog(this.activity)
    }

    /**
     * 获取Apk文件
     */
    private fun getApkFile(diskFileDirFilePath: String?, lastVersion: String?): File {
        return File("${diskFileDirFilePath}/${UpgradeConst.APK_NAME}_${lastVersion}.apk")
    }

    /**
     * 强制安裝Apk
     */
    private fun forceUpdateApk(data: ObjectBean, activity: Activity) {
        val alertDialog: AlertDialog = AlertDialog.Builder(activity)
            .setCancelable(false)
            .setMessage("请更新到最新版本")
            .setPositiveButton(android.R.string.ok, null)
            .show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val apkFile = getApkFile(data.localBean.diskFileDirFile.path, data.versionNumber)
            if (apkFile.exists()) {
                installApp(activity, UpgradeConst.APP_PACKAGE_NAME, apkFile.path)
                return@setOnClickListener
            }
            installApk(data, activity)
        }
    }

    /**
     * 检查Activity是否关闭
     */
    private fun checkContextFinishing(activity: Activity): Boolean {
        return activity.isDestroyed || activity.isFinishing
    }

    /**
     * 界面销毁关闭弹框
     */
    fun dismissDialog(activity: Activity) {
        if (checkContextFinishing(activity) || !DIALOG.isAdded) return
        if (DIALOG.isStateSaved) {
            DIALOG.dismiss()
            return
        }
        DIALOG.dismissAllowingStateLoss()
    }
}