package com.zhixinhuixue.library.net.entity
/**
 *  @description:用户信息相关类
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
data class UserEntity(
    val phone: String = "",
    val status: Int = 0,
    val teacherName: String = "",
    val teacherId: String = "",
    val isUseHxb: Boolean = false
)