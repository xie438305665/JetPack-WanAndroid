package com.zhixinhuixue.library.net

import android.util.Log
import com.zhixinhuixue.library.net.error.NetException

/**
 *  @description:请求结果回调
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
interface NetResultCallback<T> {
    fun onSuccess(data: T?) {}
    fun onError(error: NetException.ErrorBean) {
        Log.e("NetException: ", error.errorMsg ?: "")
    }
}