package com.wanandroid.bridge.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.ext.logD

/**
 *  @description:BaseMultiItemQuickAdapter 扩展
 *  @author xcl qq:244672784
 *  @date 2020/7/15
 **/
class SimpleMultipleAdapter(
    datas: MutableList<SimpleMultipleItem>,
    private val listener: SimpleAdapterListener<SimpleMultipleItem, BaseViewHolder>,
    types: MutableList<SimpleMultipleType>
) : BaseMultiItemQuickAdapter<SimpleMultipleItem, BaseViewHolder>(datas) {
    init {
        types.forEach {
            addItemType(it.itemType, it.layoutId)
        }
    }

    override fun convert(holder: BaseViewHolder, item: SimpleMultipleItem) {
        item.toString().logD("tag")
        listener.onBindViewHolder(holder, item, holder.layoutPosition)
    }
}

