package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
@Parcelize
data class ProjectTreeEntity(
    var children: List<String>,
    var courseId: Int = 0,
    var id: Int = 0,
    var name: String = "",
    var order: Int = 0,
    var parentChapterId: Int = 0,
    var userControlSetTop: Boolean = false,
    var visible: Int = 0
) : Parcelable