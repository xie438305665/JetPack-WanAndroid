package com.zhixinhuixue.library.net.entity

data class UserEntity(
    val phone: String = "",
    val status: Int = 0,
    val teacherName: String = "",
    val teacherId: String = "",
    val isUseHxb: Boolean = false
)