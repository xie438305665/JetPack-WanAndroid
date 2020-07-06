package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:积分
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
@Parcelize
data class IntegralEntity(
    var coinCount: Int,
    var date: Long,
    var desc: String,
    var id: Int,
    var type: Int,
    var reason: String,
    var userId: Int,
    var userName: String
) : Parcelable