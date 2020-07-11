package com.wanandroid.bridge.base

import com.kingja.loadsir.callback.Callback

/**
 *  @description:LoadSir 布局容器
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
open class BaseLoadCallback(private val layoutId: Int) : Callback() {
    override fun onCreateView(): Int {
        return layoutId
    }
}
