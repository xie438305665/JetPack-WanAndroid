package com.wanandroid.bridge.base

import androidx.lifecycle.MutableLiveData
import com.zhixinhuixue.library.net.NetViewModel

/**
 *  @description:ViewMode基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
open class BaseViewModel : NetViewModel() {

    val dataVm get() = createDataVm()

    open fun createDataVm(): MutableLiveData<Any> {
        return MutableLiveData()
    }

    val loadVm get() = _loadVm

    private val _loadVm: MutableLiveData<EnumStatus> = MutableLiveData()

    /**
     * 网络请求 根据业务可以重写函数
     */
    open fun onNetRequest(){}

    override fun loadingStatus(showLoading: Boolean, enum: EnumStatus) {
        if (showLoading) {
            loadVm.value = enum
        }
    }

    override fun hideLoading() {
        loadVm.value = EnumStatus.END
    }
}


