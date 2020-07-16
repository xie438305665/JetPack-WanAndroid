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
    var courseId: String = "",
    var id: String = "",
    var name: String = "",
    var order: Int = 0,
    var parentChapterId: String = "",
    var userControlSetTop: Boolean = false,
    var visible: Int = 0
) : Parcelable