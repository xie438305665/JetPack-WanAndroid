package com.zhixinhuixue.library.net.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @description:文章
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
@Parcelize
data class ArticleTopEntity(
    var apkLink: String,
    var author: String,//作者
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,//是否收藏
    var courseId: Int,
    var desc: String,
    var envelopePic: String,
    var fresh: Boolean,
    var id: Int,
    var link: String,
    var niceDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var superChapterId: Int,
    var superChapterName: String,
    var shareUser: String,
    var tags: List<TagsEntity>,
    var title: String,
    var type: Int,
    var userId: Int,
    var visible: Int,
    var zan: Int
) : Parcelable
