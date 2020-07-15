package com.wanandroid.module.home.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.module.home.R

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/15
 **/
class HomeMultipleAdapter<T : HomeMultipleItem, H : BaseViewHolder> constructor(
    data: MutableList<T>,
    val listener: SimpleAdapterListener<T, H>
) :
    BaseMultiItemQuickAdapter<T, H>(data) {
    init {
        addItemType(HomeMultipleItem.BANNER, R.layout.layout_banner_home)
    }

    override fun convert(holder: H, item: T) {
        setOnItemClickListener { adapter, view, position ->
            listener.onBindItemClick(adapter as HomeMultipleAdapter<T, H>, view, position)
        }
        listener.onBindViewHolder(holder, item, holder.layoutPosition)
    }
}