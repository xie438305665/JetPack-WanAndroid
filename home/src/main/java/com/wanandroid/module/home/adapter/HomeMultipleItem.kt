package com.wanandroid.module.home.adapter

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/15
 **/
class HomeMultipleItem(override val itemType: Int) : MultiItemEntity {
    companion object {
        const val BANNER = 0
        const val ITEM = 1
    }
}