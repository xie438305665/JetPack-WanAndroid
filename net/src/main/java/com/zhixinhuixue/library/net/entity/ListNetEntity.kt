package com.zhixinhuixue.library.net.entity

/**
 *  @description:加载更多基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
data class ListNetEntity<T>(
    var datas: T,
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)

