package com.wanandroid.module.user.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.refresh.RefreshFragment
import com.wanandroid.bridge.refresh.RefreshObserver
import com.wanandroid.module.user.R
import com.wanandroid.module.user.model.CollectArticleViewModel
import com.zhixinhuixue.library.net.entity.CollectToolEntity

/**
 *  @description:收藏文章
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class CollectArticleFragment :
    RefreshFragment<CollectToolEntity, CollectArticleViewModel, SimpleAdapter<CollectToolEntity, BaseViewHolder>>(),
    RefreshObserver<CollectToolEntity> {

    private var collectPosition = 0

    override fun getBaseQuickAdapter(): SimpleAdapter<CollectToolEntity, BaseViewHolder>? {
        return SimpleAdapter(R.layout.user_item_collect_article, mutableListOf(), this)
    }

    override fun initCreate(root: View, savedInstanceState: Bundle?) {
        baseVm.onNetRequest(params = mapOf(Pair("page", 0)))
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.articleVm.observe(this, this)
        baseVm.collectVm.observe(this, Observer {
            mAdapter.data[collectPosition].collect = it
            mAdapter.notifyItemChanged(this.collectPosition)
        })
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: CollectToolEntity, position: Int) {
        val ivCollect = holder.getView<AppCompatImageView>(R.id.iv_wx_article_item_collect)
//        val author =
//            if (item.author.isEmpty()) "${R.string.article_wx.getString()}${item.shareUser}" else "${R.string.article_author.getString()}${item.author}"
        holder.setText(
            R.id.tv_wx_article_item_date,
            "${R.string.article_date.getString()}${item.niceDate}"
        )
//        holder.setText(R.id.tv_wx_article_item_author, author)
        holder.setText(R.id.tv_wx_article_item_title, item.title)
        ivCollect.isSelected = item.collect
        ivCollect.setOnClickListener {
            this.collectPosition = position
            this.baseVm.onNetCollect(!it.isSelected, item.chapterId)
        }
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<CollectToolEntity, BaseViewHolder>,
        view: View,
        item: CollectToolEntity,
        position: Int
    ) {
        super.onBindItemClick(adapter, view, item, position)
    }
}