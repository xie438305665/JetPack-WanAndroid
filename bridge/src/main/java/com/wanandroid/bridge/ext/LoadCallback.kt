package com.wanandroid.bridge.ext

import com.kingja.loadsir.callback.Callback
import com.wanandroid.developer.library.bridge.R


/**
 *  @description:LoadSir 布局容器
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
class LoadStartCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_load_start
    }
}


/**
 *  @description:LoadSir 布局容器
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
class LoadEmptyCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_load_empty
    }
}


/**
 *  @description:LoadSir 布局容器
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
class LoadErrorCallback :
    Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_load_error
    }
}