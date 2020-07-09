package com.zhixinhuixue.library.net

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
     * @param requestType 请求类型 [EnumRequestType]
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @param showLoading 是否显示加载UI
     * @return Job
     */
    protected fun <T> request(
        showLoading: Boolean = true,
        requestType: EnumRequestType = EnumRequestType.DEFAULT,
        block: suspend NetService.() -> BaseNetEntity<T>,
        callback: NetResultCallback<T>
    ): Job {
        if (isRequest) return viewModelScope.launch { }
        if (showLoading) {
            isRequest = true
            requestLoadStatus(true, requestType, EnumLoadStatus.START)
        }
        return viewModelScope.launch {
            runCatching {
                netService.block()
            }.onSuccess {
                if (it.data != null) {
                    requestLoadStatus(showLoading, requestType, EnumLoadStatus.SUCCESS)
                    callback.onSuccess(it.data)
                    return@onSuccess
                }
                requestLoadStatus(showLoading, requestType, EnumLoadStatus.EMPTY)
            }.onFailure {
                val expectation = NetException.errorTransform(it)
                requestLoadStatus(showLoading, requestType, EnumLoadStatus.ERROR)
                callback.onError(expectation)
                if (requestType != EnumRequestType.DEFAULT) {
                    toastErrorMsg(expectation)
                }
            }
            isRequest = false
            requestLoadStatus(showLoading, requestType, EnumLoadStatus.END)
        }
    }

    /**
     * 网络请求 列表
     * @param requestType 请求类型 [EnumRequestType]
     * @param block  Retrofit 请求体[NetService]
     * @param callback 请求结果回调
     * @param showLoading 是否显示加载UI
     * @return Job
     */
    protected fun <T> requestList(
        showLoading: Boolean = true,
        requestType: EnumRequestType = EnumRequestType.DEFAULT,
        block: suspend NetService.() -> BaseNetEntity<ListNetEntity<ArrayList<T>>>,
        callback: NetResultCallback<ListNetEntity<ArrayList<T>>>
    ): Job {
        if (isRequest) return viewModelScope.launch { }
        if (showLoading) {
            isRequest = true
            requestLoadStatus(true, requestType, EnumLoadStatus.START)
        }
        return viewModelScope.launch {
            runCatching {
                netService.block()
            }.onSuccess {
                if (it.data != null) {
                    requestLoadStatus(showLoading, requestType, EnumLoadStatus.SUCCESS)
                    callback.onSuccess(it.data)
                    return@onSuccess
                }
                requestLoadStatus(showLoading, requestType, EnumLoadStatus.EMPTY)
            }.onFailure {
                val expectation = NetException.errorTransform(it)
                requestLoadStatus(showLoading, requestType, EnumLoadStatus.ERROR)
                callback.onError(expectation)
                if (requestType != EnumRequestType.DEFAULT) {
                    toastErrorMsg(expectation)
                }
            }
            isRequest = false
            requestLoadStatus(showLoading, requestType, EnumLoadStatus.END)
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
        request(true, EnumRequestType.DEFAULT, block, callback)
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
        request(true, EnumRequestType.REFRESH, block, callback)
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
        request(true, EnumRequestType.PUT, block, callback)
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
        request(true, EnumRequestType.DELETE, block, callback)
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
        requestList(true, EnumRequestType.DEFAULT, block, callback)
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
        requestList(true, EnumRequestType.REFRESH, block, callback)
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
        requestList(true, EnumRequestType.LOAD_MORE, block, callback)
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
        request(false, EnumRequestType.DEFAULT, block, callback)
    }

    /**
     * 请求加载UI
     * @param showLoading 请求时是否显示加载UI
     * @param requestType 请求类型
     * @param enumLoad 加载状态
     */
    abstract fun requestLoadStatus(
        showLoading: Boolean,
        requestType: EnumRequestType,
        enumLoad: EnumLoadStatus
    )

    /**
     * 错误信息Toast
     * @param error ErrorEnum
     */
    open fun toastErrorMsg(error: NetException.ErrorEnum) {}

    /**
     * 加载状态
     */
    enum class EnumLoadStatus {
        START,//开始加载
        ERROR,//加载错误
        SUCCESS,//加载成功
        EMPTY,//暂无数据
        END//加载结束
    }

    /**
     * 请求类型
     */
    enum class EnumRequestType {
        DEFAULT,//默认
        REFRESH,//刷新
        LOAD_MORE,//加载更多
        PUT,//修改
        DELETE,//删除
    }
}