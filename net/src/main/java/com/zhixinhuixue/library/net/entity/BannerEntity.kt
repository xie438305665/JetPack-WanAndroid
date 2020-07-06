package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:Banner
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
@Parcelize
data class BannerEntity(
    var desc: String = "",
    var id: Int = 0,
    var imagePath: String = "",
    var isVisible: Int = 0,
    var order: Int = 0,
    var title: String = "",
    var type: Int = 0,
    var url: String = ""
) : Parcelable
