package com.wanandroid.module.home.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import coil.api.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.ext.clickNoRepeat
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.ext.logD
import com.wanandroid.bridge.refresh.RefreshActivity
import com.wanandroid.bridge.refresh.RefreshObserver
import com.wanandroid.module.home.R
import com.wanandroid.module.home.model.HomeSearchListModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity

/**
 *  @description:搜索数据列表
 *  @author xcl qq:244672784
 *  @date 2020/7/29
 **/
class HomeSearchListActivity :
    RefreshActivity<ArticleEntity, HomeSearchListModel, SimpleAdapter<ArticleEntity, BaseViewHolder>>(),
    RefreshObserver<ArticleEntity>,
    SimpleAdapterListener<ArticleEntity, BaseViewHolder> {
    private var position = 0
    private lateinit var search: String
    override fun initCreate(bundle: Bundle?) {
        if (bundle == null) {
            refreshLoadStatus()
            return
        }
        search = bundle.getString(AnnotationValue.BUNDLE_KEY_SEARCH, "")
        mToolbar.setTitleText(search)
        baseVm.onNetRequest(
            NetViewModel.RequestType.DEFAULT, mapOf(Pair("page", 0), Pair("k", search))
        )
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.searchVm.observe(this, this)
        baseVm.collectVm.observe(this, Observer {
            mAdapter.data[position] = it
            mAdapter.notifyItemChanged(this.position)
        })
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ArticleEntity, position: Int) {
        position.logD()
        val ivCollect = holder.getView<AppCompatImageView>(R.id.ivSearchArticleItemCollect)
        val tvLink = holder.getView<AppCompatTextView>(R.id.tvSearchArticleItemLink)
        val author =
            if (item.author.isEmpty()) "${R.string.article_shareUser.getString()}${item.shareUser}" else "${R.string.article_author.getString()}${item.author}"
//        val linkName =
//            if (TextUtils.isEmpty(currentItemEntity.id)) R.string.article_link.getString() else currentItemEntity.name
        holder.setText(
            R.id.tvSearchArticleItemDate,
            "${R.string.article_date.getString()}${item.niceDate}"
        )
        holder.setText(
            R.id.tvSearchArticleItemType,
            "${item.superChapterName}/${item.chapterName}"
        )
        holder.setText(R.id.tvSearchArticleItemAuthor, author)
        holder.setText(R.id.tvSearchArticleItemTitle, item.title)
        holder.setText(R.id.tvSearchArticleItemContent, item.desc)
        holder.getView<AppCompatImageView>(R.id.tvSearchArticleItemIcon).load(item.envelopePic)
        tvLink.text = "测试"
        ivCollect.isSelected = item.collect
        ivCollect.clickNoRepeat {
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
        super<RefreshActivity>.onBindItemClick(adapter, view, item, position)
    }

    override fun getBaseQuickAdapter(): SimpleAdapter<ArticleEntity, BaseViewHolder>? {
        return SimpleAdapter(R.layout.home_item_search_article, mutableListOf(), this)
    }

    override fun onRefresh() {
        page = 0
        baseVm.onNetRequest(
            NetViewModel.RequestType.REFRESH,
            mapOf(Pair("page", page), Pair("k", search))
        )
    }

    override fun onLoadMore() {
        baseVm.onNetRequest(
            NetViewModel.RequestType.LOAD_MORE,
            mapOf(Pair("page", page), Pair("k", search))
        )
    }
}