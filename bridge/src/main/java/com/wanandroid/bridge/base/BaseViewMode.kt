package com.wanandroid.bridge.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  @description:ViewMode基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseViewMode<T> : ViewModel() {
    var liveData: MutableLiveData<T>? = null
    abstract fun showLoading()
    abstract fun hideLoading()
}