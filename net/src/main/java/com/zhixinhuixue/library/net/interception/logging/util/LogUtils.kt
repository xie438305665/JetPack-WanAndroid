package com.zhixinhuixue.library.net.interception.logging.util

import android.util.Log
import com.wanandroid.developer.library.net.BuildConfig

/**
 *  @description:Log日志打印
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
class LogUtils private constructor() {
    companion object {
        private const val DEFAULT_TAG = "JetPack-WanAndroid"
        private var isShowLog = BuildConfig.DEBUG
        fun setLog(isLog: Boolean) {
            Companion.isShowLog = isLog
        }

        fun debugInfo(tag: String?, msg: String?) {
            if (!isShowLog || msg.isNullOrEmpty()) {
                return
            }
            Log.d(tag, msg)
        }

        fun debugInfo(msg: String?) {
            debugInfo(DEFAULT_TAG, msg)
        }

        private fun warnInfo(tag: String?, msg: String?) {
            if (!isShowLog || msg.isNullOrEmpty()) {
                return
            }
            Log.w(tag, msg)
        }

        fun warnInfo(msg: String?) {
            warnInfo(DEFAULT_TAG, msg)
        }

        /**
         * 这里使用自己分节的方式来输出足够长度的 message
         *
         * @param tag 标签
         * @param msg 日志内容
         */
       private fun debugLongInfo(tag: String?, msg: String?) {
            if (!isShowLog || msg.isNullOrEmpty()) {
                return
            }
            val msgValue = msg.trim { it <= ' ' }
            var index = 0
            val maxLength = 3500
            var sub: String
            while (index < msgValue.length) {
                sub = if (msgValue.length <= index + maxLength) {
                    msgValue.substring(index)
                } else {
                    msgValue.substring(index, index + maxLength)
                }
                index += maxLength
                Log.d(tag, sub.trim { it <= ' ' })
            }
        }

        fun debugLongInfo(msg: String) {
            debugLongInfo(DEFAULT_TAG, msg)
        }
    }

    init {
        throw IllegalStateException("you can't instantiate me!")
    }
}