package com.wanandroid.bridge.base

/**
 *  @description:带默认值的ViewMode
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
class BaseParamViewMode<T>(private val defaultValue: T) : BaseViewMode<T>() {

    init {
        liveData?.value = defaultValue
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }
}