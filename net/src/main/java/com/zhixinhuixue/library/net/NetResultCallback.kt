package com.zhixinhuixue.library.net

/**
 *  @description:请求结果回调
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
interface NetResultCallback<T> {
    fun onSuccess(data: T?)
    fun onError(e: Throwable?)
}