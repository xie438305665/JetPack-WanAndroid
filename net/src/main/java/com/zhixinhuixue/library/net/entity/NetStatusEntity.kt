package com.zhixinhuixue.library.net.entity

import com.zhixinhuixue.library.net.NetViewModel

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/9
 **/
data class NetStatusEntity(
    val requestType: NetViewModel.EnumRequestType,
    val loadStatus: NetViewModel.EnumLoadStatus
)