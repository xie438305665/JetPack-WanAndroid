package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/24
 **/
@Parcelize
data class WebViewEntity(
    var url: String,
    var routerPath: String,
    var title: String,
    var data: String,
    var code: Int = 0x001
) : Parcelable