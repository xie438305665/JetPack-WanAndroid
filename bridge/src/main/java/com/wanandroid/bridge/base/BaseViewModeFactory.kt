package com.wanandroid.bridge.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @description:Factory基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseViewModeFactory<P>(private val defaultValue: P) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BaseParamViewModel(defaultValue) as T
    }
}