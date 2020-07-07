package com.zhixinhuixue.library.net.entity

/**
 *  @description:请求数据解析基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
data class BaseNetEntity<T>(
    var data: T?,
    var msg:String?,
    var code:Int
)