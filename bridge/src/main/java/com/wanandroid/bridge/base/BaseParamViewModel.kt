package com.wanandroid.bridge.base

/**
 *  @description:带默认值的ViewMode
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
class BaseParamViewModel<T>(private val defaultValue: T) : BaseViewModel() {

    init {
        dataVm.value = defaultValue
    }
}