package com.wanandroid.bridge.ext

import com.kingja.loadsir.callback.Callback
import com.wanandroid.developer.library.bridge.R


/**
 *  @description:LoadSir 开始请求
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
class LoadStartCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_load_start
    }
}


/**
 *  @description:LoadSir 空数据
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
class LoadEmptyCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_load_empty
    }
}


/**
 *  @description:LoadSir 请求错误
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
class LoadErrorCallback :
    Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_load_error
    }
}

/**
 *  @description:LoadSir put/delete
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
class LoadPutCallback :
    Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_load_put
        
    }
}