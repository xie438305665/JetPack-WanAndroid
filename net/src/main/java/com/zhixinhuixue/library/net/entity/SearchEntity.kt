package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:搜索
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
@Parcelize
data class SearchEntity(
    var id: Int,
    var link: String,
    var name: String,
    var order: Int,
    var visible: Int
) : Parcelable