package com.zhixinhuixue.library.net

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhixinhuixue.library.net.api.NetService
import com.zhixinhuixue.library.net.entity.BaseNetEntity
import kotlinx.coroutines.launch

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/7
 **/
abstract class NetViewModel : ViewModel() {
    var isLoading = false
    
    private val netService: NetService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        NetRetrofit.createService(NetService::class.java)
    }

    /**
     * 网络请求
     */
    open fun <T> request(
        block: suspend NetService.() -> BaseNetEntity<T>,
        callback: NetResultCallback<T>,
        showLoading: Boolean = true
    ) {
        if (showLoading && !isLoading) {
            isLoading = true
            showLoading()
        }
        viewModelScope.launch {
            kotlin.runCatching {
                netService.block()
            }.onSuccess {
                callback.onSuccess(it.data)
            }.onFailure {
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
     */
    abstract fun showLoading()

    /**
     * 隐藏网络加载框
     */
    abstract fun hideLoading()
}