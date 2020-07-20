package com.wanandroid.bridge.adapter

import androidx.annotation.LayoutRes

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/15
 **/
class SimpleMultipleType(val itemType: Int, @LayoutRes val layoutId: Int){
    companion object {
        const val BANNER = 0
        const val ITEM = 1
        const val HEADER = 2
        const val LINE = 3
    }
}