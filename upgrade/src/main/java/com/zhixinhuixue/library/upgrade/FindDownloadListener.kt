package com.zhixinhuixue.library.upgrade

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
interface FindDownloadListener {
    /**
     * 开始下载
     */
    fun downloadStart(apkFilePath: String, apkUrl: String, downloadTag: Any)

    /**
     * 取消下载
     */
    fun downloadCancel()

    /**
     * 暂停下载
     */
    fun downloadPause()
}