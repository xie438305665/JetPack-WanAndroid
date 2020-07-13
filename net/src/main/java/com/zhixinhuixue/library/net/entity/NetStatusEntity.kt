package com.zhixinhuixue.library.net.entity

import com.zhixinhuixue.library.net.NetViewModel.LoadStatus
import com.zhixinhuixue.library.net.NetViewModel.RequestType

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/9
 **/
data class NetStatusEntity(
    @RequestType
    val requestType: Int,
    @LoadStatus
    val loadStatus: Int
)