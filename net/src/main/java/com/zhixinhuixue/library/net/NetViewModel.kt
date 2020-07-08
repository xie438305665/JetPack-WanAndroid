package com.zhixinhuixue.library.net

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhixinhuixue.library.net.api.NetService
import com.zhixinhuixue.library.net.entity.BaseNetEntity
import kotlinx.coroutines.launch

/**
 *  @description:网络请求ViewModel基类
 *  @author xcl qq:244672784
 *  @date 2020/7/7
 **/
abstract class NetViewModel : ViewModel() {
    //是否正在加载
    var isLoading = false

    /**
     * Retrofit.Service
     */
    private val netService: NetService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        NetRetrofit.createService(NetService::class.java)
    }

    /**
     * 网路请求封装
     * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<NetService, BaseNetEntity<T>>
     * @param callback NetResultCallback<T> 请求结果回调
     * @param showLoading Boolean 是否显示加载框
     */
    open fun <T> request(
        block: suspend NetService.() -> BaseNetEntity<T>,
        callback: NetResultCallback<T>,
        showLoading: Boolean = true
    ) {
        if (showLoading && !isLoading) {
            isLoading = true
            loadingStatus(true, EnumStatus.START)
        }
        viewModelScope.launch {
            runCatching {
                netService.block()
            }.onSuccess {
                loadingStatus(
                    showLoading,
                    if (it.data == null) EnumStatus.SUCCESS else EnumStatus.EMPTY
                )
                callback.onSuccess(it.data)
            }.onFailure {
                loadingStatus(showLoading, EnumStatus.ERROR)
                callback.onError(it)
                it.message?.let { msg ->
                    Log.e("HttpException:", msg)
                }
            }
            if (showLoading) {
                isLoading = false
                hideLoading()
            }
        }
    }

    /**
     * 显示网络加载框
     * @param showLoading Boolean 是否显示加载框
     * @param enum EnumStatus @link[EnumStatus]}
     */
    abstract fun loadingStatus(showLoading: Boolean, enum: EnumStatus)

    /**
     * 隐藏网络加载框
     */
    abstract fun hideLoading()

    /**
     * Load加载框状态
     */
    enum class EnumStatus {
        START,//开始加载
        ERROR,//加载错误
        SUCCESS,//加载成功
        EMPTY,//暂无数据
        END//加载失败
    }
}