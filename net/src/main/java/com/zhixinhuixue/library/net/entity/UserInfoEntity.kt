package com.zhixinhuixue.library.net.entity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/23
 **/
data class UserInfoEntity(
    var coinCount: Int,
    var userName: String,
    var icon: String = "",
    var rank: Int
)