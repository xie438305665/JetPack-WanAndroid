package com.zhixinhuixue.library.net

import androidx.lifecycle.MutableLiveData
import com.zhixinhuixue.library.net.error.ExceptionHandle
import com.zhixinhuixue.library.net.error.NetException

/**
 * 作者　: hegaojian
 * 时间　: 2020/4/9
 * 描述　: 自定义结果集封装类
 */
sealed class ResultState<out T> {
    companion object {
        fun <T> onNetSuccess(data: T): ResultState<T> = Success(data)
        fun <T> onNetLoading(loadingMessage:String): ResultState<T> = Loading(loadingMessage)
        fun <T> onNetError(error: NetException): ResultState<T> = Error(error)
    }
    data class Loading(val loadingMessage:String) : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: NetException) : ResultState<Nothing>()
}

/**
 * 处理返回值
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: NetResponse<T>) {
    value = if (result.isSuccess()) ResultState.onNetSuccess(result.getResponseData()) else
        ResultState.onNetError(
            NetException(
                result.getResponseCode(),
                result.getResponseMsg()
            )
        )
}

/**
 * 不处理返回值 直接返回请求结果
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: T) {
    value = ResultState.onNetSuccess(result)
}

/**
 * 异常转换异常处理
 */
fun <T> MutableLiveData<ResultState<T>>.paresException(e: Throwable) {
    this.value = ResultState.onNetError(ExceptionHandle.handleException(e))
}

