package com.wanandroid.bridge.base

import android.view.View
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *  @description:Adapter Bind
 *  @author xcl qq:244672784
 *  @Date 2020/7/14
 **/
interface BaseAdapterListener<T, H : BaseViewHolder> {
    /**
     * 设置Item 子控件点击事件
     */
    fun onBindItemChildClick(
        adapter: BaseAdapter<T, H>,
        view: View,
        position: Int
    ) {
    }

    /**
     * 设置Item点击事件
     */
    fun onBindItemClick(
        adapter: BaseAdapter<T, H>,
        view: View,
        position: Int
    ) {
    }

    /**
     * Item绑定
     */
    fun onBindViewHolder(holder: H, item: T, position: Int) {}
}