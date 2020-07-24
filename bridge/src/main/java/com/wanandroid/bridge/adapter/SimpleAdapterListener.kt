package com.wanandroid.bridge.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *  @description:Adapter Bind
 *  @author xcl qq:244672784
 *  @Date 2020/7/14
 **/
interface SimpleAdapterListener<T, H : BaseViewHolder> {
    /**
     * 设置Item点击事件
     */
    fun onBindItemClick(
        adapter: BaseQuickAdapter<T, H>,
        view: View,
        item: T,
        position: Int
    ) {
    }

    /**
     * Item绑定
     */
    fun onBindViewHolder(holder: H, item: T, position: Int) {}
}