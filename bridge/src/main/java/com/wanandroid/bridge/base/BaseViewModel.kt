package com.wanandroid.bridge.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhixinhuixue.library.net.NetRetrofit
import com.zhixinhuixue.library.net.api.NetService

/**
 *  @description:ViewMode基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseViewModel<T> : ViewModel() {
    var liveData: MutableLiveData<T>? = null

    private val netService: NetService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        NetRetrofit.getService(NetService::class.java)
    }

    /**
     * 网络请求
     */
    open fun request(block: NetService.() -> Unit) {
        netService.block()
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


