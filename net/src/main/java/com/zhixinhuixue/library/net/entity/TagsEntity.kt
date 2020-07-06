package com.zhixinhuixue.library.net.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/**
 * 文章的标签
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class TagsEntity(var name:String, var url:String): Parcelable
