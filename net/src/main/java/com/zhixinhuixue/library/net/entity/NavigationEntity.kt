package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:导航
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
@Parcelize
data class NavigationEntity(
    var articles: ArrayList<ArticleEntity>,
    var cid: Int,
    var name: String
) : Parcelable