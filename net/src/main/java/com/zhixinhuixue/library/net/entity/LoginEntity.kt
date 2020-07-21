package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:用户信息相关类
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
@Parcelize
data class LoginEntity(
    var admin: Boolean = false,
    var chapterTops: List<String>,
    var collectIds: MutableList<String>,
    var email: String = "",
    var icon: String = "",
    var id: String = "",
    var nickname: String = "",
    var password: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = ""
) : Parcelable