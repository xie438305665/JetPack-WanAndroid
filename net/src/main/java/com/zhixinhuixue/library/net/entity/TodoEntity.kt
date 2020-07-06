package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:Banner
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
@Parcelize
data class TodoEntity(
    var completeDate: Long,
    var completeDateStr: String,
    var content: String,
    var date: Long,
    var dateStr: String,
    var id: Int,
    var priority: Int,
    var status: Int,
    var title: String,
    var type: Int,
    var userId: Int
) : Parcelable
