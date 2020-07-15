package com.wanandroid.module.home.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.module.home.R

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/15
 **/
class HomeMultipleAdapter(
    data: MutableList<HomeMultipleItem>,
    private val listener: SimpleAdapterListener<HomeMultipleItem, BaseViewHolder>
) : BaseMultiItemQuickAdapter<HomeMultipleItem, BaseViewHolder>(data) {
    init {
        addItemType(HomeMultipleItem.BANNER, R.layout.item_banner_home)
        addItemType(HomeMultipleItem.ITEM, R.layout.item_article_home)
    }

    override fun convert(holder: BaseViewHolder, item: HomeMultipleItem) {
        setOnItemClickListener { adapter, view, position ->
            listener.onBindItemClick(
                adapter as BaseQuickAdapter<HomeMultipleItem, BaseViewHolder>,
                view,
                position
            )
        }
        when (item.itemType) {
            HomeMultipleItem.BANNER -> {
                listener.onBindBannerViewHolder(holder, item, holder.layoutPosition)
            }
            HomeMultipleItem.ITEM -> {
                listener.onBindViewHolder(holder, item, holder.layoutPosition)
            }
        }
    }
}

