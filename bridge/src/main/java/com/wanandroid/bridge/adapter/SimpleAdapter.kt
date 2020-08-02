package com.wanandroid.bridge.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *  @description:BaseQuickAdapter 扩展
 *  @author xcl qq:244672784
 *  @Date 2020/7/14
 **/
class SimpleAdapter<T, H : BaseViewHolder>(
    @LayoutRes layoutRes: Int,
    data: MutableList<T>,
    private val listener: SimpleAdapterListener<T, H>
) :
    BaseQuickAdapter<T, H>(layoutRes, data) {
    override fun convert(holder: H, item: T) {
        listener.onBindViewHolder(holder, item, holder.layoutPosition)
        setOnItemClickListener { adapter, view, position ->
            listener.onBindItemClick(adapter as SimpleAdapter<T, H>, view,adapter.getItem(position), position)
        }
    }
}