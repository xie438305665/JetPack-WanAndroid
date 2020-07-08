package com.wanandroid.bridge.base

import androidx.lifecycle.MutableLiveData
import com.hjq.toast.ToastUtils
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.NetViewModel

/**
 *  @description:ViewMode基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseViewModel<T> : NetViewModel(), NetResultCallback<T> {

    val dataVm get() = _dataVm

    private val _dataVm: MutableLiveData<T> = MutableLiveData()

    val loadVm get() = _loadVm

    private val _loadVm: MutableLiveData<EnumStatus> = MutableLiveData()

    /**
     * 网络请求 根据业务可以重写函数
     */
    open fun onNetRequest() {}

    override fun loadingStatus(showLoading: Boolean, enum: EnumStatus) {
        if (showLoading) {
            loadVm.value = enum
        }
    }

    override fun hideLoading() {
        loadVm.value = EnumStatus.END
    }

    override fun onSuccess(data: T?) {
        _dataVm.value = data
    }

    override fun onError(e: Throwable?) {
        ToastUtils.show(e?.message)
    }
}


