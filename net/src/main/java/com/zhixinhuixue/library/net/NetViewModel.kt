package com.zhixinhuixue.library.net

import androidx.annotation.IntDef
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhixinhuixue.library.net.api.NetService
import com.zhixinhuixue.library.net.entity.BaseNetEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity
import com.zhixinhuixue.library.net.error.NetException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *  @description:网络请求ViewModel基类
 *  @author xcl qq:244672784
 *  @date 2020/7/7
 **/
abstract class NetViewModel : ViewModel() {
    //是否正在网络请求
    protected var isRequest = false

    /**
     * Retrofit.Service
     */
    private val netService: NetService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        NetRetrofit.createService(NetService::class.java)
    }

    /**
     * 网络请求
     * @param requestType 请求类型 [RequestType]
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @param showLoading 是否显示加载UI
     * @return Job
     */
    protected fun <T> request(
        showLoading: Boolean = true,
        @RequestType requestType: Int = RequestType.DEFAULT,
        block: suspend NetService.() -> BaseNetEntity<T>,
        callback: NetResultCallback<T>
    ): Job {
        if (isRequest) return viewModelScope.launch { }
        if (showLoading) {
            isRequest = true
            requestLoadStatus(true, requestType, LoadStatus.START)
        }
        return viewModelScope.launch {
            runCatching {
                netService.block()
            }.onSuccess {
                if (it.data != null) {
                    requestLoadStatus(showLoading, requestType, LoadStatus.SUCCESS)
                    callback.onSuccess(it.data)
                    return@onSuccess
                }
                requestLoadStatus(showLoading, requestType, LoadStatus.EMPTY)
            }.onFailure {
                val expectation = NetException.errorTransform(it)
                requestLoadStatus(showLoading, requestType, LoadStatus.ERROR)
                callback.onError(expectation)
                if (requestType != RequestType.DEFAULT) {
                    toastErrorMsg(expectation)
                }
            }
            isRequest = false
            requestLoadStatus(showLoading, requestType, LoadStatus.END)
        }
    }

    /**
     * 网络请求 列表
     * @param requestType 请求类型 [RequestType]
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @return Job
     */
    protected fun <T> requestList(
        @RequestType requestType: Int = RequestType.DEFAULT,
        block: suspend NetService.() -> BaseNetEntity<ListNetEntity<ArrayList<T>>>,
        callback: NetResultCallback<ListNetEntity<ArrayList<T>>>
    ): Job {
        if (isRequest) return viewModelScope.launch { }
        isRequest = true
        requestLoadStatus(true, requestType, LoadStatus.START)
        return viewModelScope.launch {
            runCatching {
                netService.block()
            }.onSuccess {
                if (it.data != null) {
                    requestLoadStatus(true, requestType, LoadStatus.SUCCESS)
                    callback.onSuccess(it.data)
                    return@onSuccess
                }
                requestLoadStatus(true, requestType, LoadStatus.EMPTY)
            }.onFailure {
                val expectation = NetException.errorTransform(it)
                requestLoadStatus(true, requestType, LoadStatus.ERROR)
                callback.onError(expectation)
                if (requestType != RequestType.DEFAULT) {
                    toastErrorMsg(expectation)
                }
            }
            isRequest = false
            requestLoadStatus(true, requestType, LoadStatus.END)
        }
    }

    /**
     * 网络请求 默认第一次进来
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @return Job
     */
    protected fun <T> request(
        block: suspend NetService.() -> BaseNetEntity<T>,
        callback: NetResultCallback<T>
    ) {
        request(true, RequestType.DEFAULT, block, callback)
    }

    /**
     * 网络请求 刷新
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @return Job
     */
    protected fun <T> refreshRequest(
        block: suspend NetService.() -> BaseNetEntity<T>,
        callback: NetResultCallback<T>
    ) {
        request(true, RequestType.REFRESH, block, callback)
    }

    /**
     * 网络请求 修改
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @return Job
     */
    protected fun <T> putRequest(
        block: suspend NetService.() -> BaseNetEntity<T>,
        callback: NetResultCallback<T>
    ) {
        request(true, RequestType.PUT, block, callback)
    }

    /**
     * 网络请求 刪除
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @return Job
     */
    protected fun <T> deleteRequest(
        block: suspend NetService.() -> BaseNetEntity<T>,
        callback: NetResultCallback<T>
    ) {
        request(true, RequestType.DELETE, block, callback)
    }

    /**
     * 网络请求 默认第一次进来
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @return Job
     */
    protected fun <T> requestList(
        block: suspend NetService.() -> BaseNetEntity<ListNetEntity<ArrayList<T>>>,
        callback: NetResultCallback<ListNetEntity<ArrayList<T>>>
    ) {
        requestList(RequestType.DEFAULT, block, callback)
    }

    /**
     * 网络请求刷新
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @return Job
     */
    protected fun <T> refreshListRequest(
        block: suspend NetService.() -> BaseNetEntity<ListNetEntity<ArrayList<T>>>,
        callback: NetResultCallback<ListNetEntity<ArrayList<T>>>
    ) {
        requestList(RequestType.REFRESH, block, callback)
    }

    /**
     * 网络请求 加载更多
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @return Job
     */
    protected fun <T> loadListRequest(
        block: suspend NetService.() -> BaseNetEntity<ListNetEntity<ArrayList<T>>>,
        callback: NetResultCallback<ListNetEntity<ArrayList<T>>>
    ) {
        requestList(RequestType.LOAD_MORE, block, callback)
    }

    /**
     * 网络请求 不使用加载框 （适合埋点）
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @return Job
     */
    protected fun <T> requestNoLoad(
        block: suspend NetService.() -> BaseNetEntity<T>,
        callback: NetResultCallback<T>
    ) {
        request(false, RequestType.DEFAULT, block, callback)
    }

    /**
     * 请求加载UI
     * @param showLoading 请求时是否显示加载UI
     * @param requestType 请求类型
     * @param loadStatus 加载状态
     */
    abstract fun requestLoadStatus(
        showLoading: Boolean,
        @RequestType requestType: Int,
        @LoadStatus loadStatus: Int
    )

    /**
     * 错误信息Toast
     * @param error ErrorEnum
     */
    open fun toastErrorMsg(error: NetException.ErrorEnum) {}

    /**
     * 加载状态
     */
    @IntDef(
        LoadStatus.START,
        LoadStatus.ERROR,
        LoadStatus.SUCCESS,
        LoadStatus.EMPTY,
        LoadStatus.END
    )
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class LoadStatus {
        companion object {
            const val START: Int = 0//开始加载
            const val ERROR: Int = 1//加载错误
            const val SUCCESS: Int = 2//加载成功
            const val EMPTY: Int = 3//暂无数据
            const val END: Int = 4//加载结束
        }
    }

    /**
     * 请求类型
     */
    @IntDef(
        RequestType.DEFAULT,
        RequestType.REFRESH,
        RequestType.LOAD_MORE,
        RequestType.PUT,
        RequestType.DELETE
    )
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class RequestType {
        companion object {
            const val DEFAULT: Int = 0//开始加载
            const val REFRESH: Int = 1//加载错误
            const val LOAD_MORE: Int = 2//加载成功
            const val PUT: Int = 3//暂无数据
            const val DELETE: Int = 4//加载结束
        }
    }
}