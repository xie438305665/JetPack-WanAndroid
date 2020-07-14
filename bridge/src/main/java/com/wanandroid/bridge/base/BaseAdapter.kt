package com.wanandroid.bridge.base

import android.view.View
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/14
 **/
class BaseAdapter<T, H : BaseViewHolder>(
    @LayoutRes layoutRes: Int,
    data: MutableList<T>,
    private val listener: BaseAdapterListener<T, H>
) :
    BaseQuickAdapter<T, H>(layoutRes, data) {
    override fun convert(holder: H, item: T) {
        setOnItemChildClickListener { adapter, view, position ->
            listener.onBindItemChildClick(adapter as BaseAdapter<T, H>, view, position)
        }
        setOnItemClickListener { adapter, view, position ->
            listener.onBindItemClick(adapter as BaseAdapter<T, H>, view, position)
        }
        listener.onBindViewHolder(holder, item, holder.layoutPosition)
    }
}