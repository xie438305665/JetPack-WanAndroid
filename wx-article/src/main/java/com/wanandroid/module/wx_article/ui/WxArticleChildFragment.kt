package com.wanandroid.module.wx_article.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.refresh.RefreshFragment
import com.wanandroid.bridge.ext.getString
import com.wanandroid.module.wx_article.R
import com.wanandroid.module.wx_article.model.WxArticleChildViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ProjectTreeEntity

/**
 *  @description:微信公众号Child
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class WxArticleChildFragment :
    RefreshFragment<ArticleEntity, WxArticleChildViewModel, SimpleAdapter<ArticleEntity, BaseViewHolder>>(),
    Observer<MutableList<ArticleEntity>> {
    private var position = 0
    private lateinit var currentItemEntity: ProjectTreeEntity

    companion object {
        const val CURRENT_ITEM_KEY = "currentItem"
        fun newInstance(entity: ProjectTreeEntity): WxArticleChildFragment {
            val bundle = Bundle()
            bundle.putParcelable(CURRENT_ITEM_KEY, entity)
            val fragment = WxArticleChildFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getBaseQuickAdapter(): SimpleAdapter<ArticleEntity, BaseViewHolder>? {
        return SimpleAdapter(R.layout.wx_item_article, mutableListOf(), this)
    }

    override fun initCreate(root: View, savedInstanceState: Bundle?) {
        if (bundle == null || bundle?.get(CURRENT_ITEM_KEY) == null) {
            refreshLoadStatus(NetViewModel.LoadStatus.EMPTY, NetViewModel.RequestType.DEFAULT)
            return
        }
        bundle?.getParcelable<ProjectTreeEntity>(CURRENT_ITEM_KEY)?.let {
            this.currentItemEntity = it
            baseVm.onNetRequest(
                NetViewModel.RequestType.DEFAULT,
                mapOf(Pair("page", 0), Pair("id", it.id))
            )
        }
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.projectChildVm.observe(this, this)
        baseVm.collectVm.observe(this, Observer {
            mAdapter.data[position] = it
            mAdapter.notifyItemChanged(this.position)
        })
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ArticleEntity, position: Int) {
        val ivCollect = holder.getView<AppCompatImageView>(R.id.iv_wx_article_item_collect)
        val author =
            if (item.author.isEmpty()) "${R.string.article_wx.getString()}${item.shareUser}" else "${R.string.article_author.getString()}${item.author}"
        holder.setText(
            R.id.tv_wx_article_item_date,
            "${R.string.article_date.getString()}${item.niceDate}"
        )
        holder.setText(R.id.tv_wx_article_item_author, author)
        holder.setText(R.id.tv_wx_article_item_title, item.title)
        ivCollect.isSelected = item.collect
        ivCollect.setOnClickListener {
            this.position = position
            this.baseVm.onNetCollect(!it.isSelected, item)
        }
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<ArticleEntity, BaseViewHolder>,
        view: View,
        item: ArticleEntity,
        position: Int
    ) {
        super.onBindItemClick(adapter, view, item, position)
    }

    /**
     * 下拉刷新
     */
    override fun onRefresh() {
        page = 0
        baseVm.onNetRequest(
            NetViewModel.RequestType.REFRESH,
            mapOf(Pair("page", page), Pair("id", currentItemEntity.id))
        )
    }

    /**
     * 上拉加载
     */
    override fun onLoadMore() {
        baseVm.onNetRequest(
            NetViewModel.RequestType.LOAD_MORE,
            mapOf(Pair("page", page), Pair("id", currentItemEntity.id))
        )
    }
}