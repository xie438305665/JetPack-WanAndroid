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

    private val _dataVm: MutableLiveData<Any> = MutableLiveData<Any>()

    open fun onNetRequest() {}

    override fun onSuccess(data: T?) {
        _dataVm.value = data
    }

    override fun onError(e: Throwable?) {
        ToastUtils.show(e?.message)
    }
}


