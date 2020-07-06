package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:知识点
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
@Parcelize
data class KnowledgeTreeEntity(
    var children: ArrayList<ProjectTreeEntity>,
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int
) : Parcelable