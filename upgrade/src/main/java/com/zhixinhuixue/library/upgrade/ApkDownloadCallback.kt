package com.zhixinhuixue.library.upgrade

import androidx.fragment.app.FragmentManager

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
interface ApkDownloadCallback {
    /**
     * 显示下载框
     */
    fun showDialog(manager: FragmentManager, tag: String?)

    /**
     * 更新进度
     */
    fun updateProgress(soFarBytes: Int, totalBytes: Int)

    /**
     * 完成
     */
    fun updateCompleted(data: ObjectBean?)

    /**
     * 失败
     */
    fun updateError(any: Any?)
}