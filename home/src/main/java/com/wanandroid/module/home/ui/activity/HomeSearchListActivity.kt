package com.wanandroid.module.home.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.ext.*
import com.wanandroid.bridge.refresh.RefreshActivity
import com.wanandroid.bridge.refresh.RefreshObserver
import com.wanandroid.module.home.R
import com.wanandroid.module.home.model.HomeSearchListModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.WebViewEntity

/**
 *  @description:搜索数据列表
 *  @author xcl qq:244672784
 *  @date 2020/7/29
 **/
class HomeSearchListActivity :
    RefreshActivity<ArticleEntity, HomeSearchListModel, SimpleAdapter<ArticleEntity, BaseViewHolder>>(),
    RefreshObserver<ArticleEntity>,
    SimpleAdapterListener<ArticleEntity, BaseViewHolder> {
    private var collectPosition = 0
    private lateinit var search: String

    companion object {
        const val CODE = 0x104
        fun start(search: String?, code: Int, activity: HomeHotSearchActivity) {
            activity.toStartActivity(HomeSearchListActivity::class.java, code, Bundle().apply {
                putString(AnnotationValue.BUNDLE_KEY_SEARCH, search)
            })
        }
    }

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
            notifyItemChanged(it)
        })
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ArticleEntity, position: Int) {
        val ivCollect = holder.getView<AppCompatImageView>(R.id.ivSearchArticleItemCollect)
        val tvLink = holder.getView<AppCompatTextView>(R.id.tvSearchArticleItemLink)
        val author =
            if (item.author.isEmpty()) "${R.string.article_shareUser.getString()}${item.shareUser}" else "${R.string.article_author.getString()}${item.author}"
        holder.setText(
            R.id.tvSearchArticleItemDate,
            "${R.string.article_date.getString()}${item.niceDate}"
        )
        holder.setText(
            R.id.tvSearchArticleItemType,
            "${item.superChapterName}/${item.chapterName}"
        )
        holder.setText(R.id.tvSearchArticleItemAuthor, author)
        holder.setText(R.id.tvSearchArticleItemTitle, item.title.formHtml())
        tvLink.text = search
        ivCollect.isSelected = item.collect
        ivCollect.clickNoRepeat {
            this.collectPosition = position
            this.baseVm.onNetCollect(item.collect, item.id)
        }
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<ArticleEntity, BaseViewHolder>,
        view: View,
        item: ArticleEntity,
        position: Int
    ) {
        this.collectPosition = position
        HomeWebActivity.start(WebViewEntity(item.link, "", item.title, item.toJson()), CODE, this)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != CODE || resultCode != Activity.RESULT_OK || data == null) return
        data.extras?.let {
            notifyItemChanged(it.getBoolean(AnnotationValue.BUNDLE_KEY_COLLECT, false))
        }
    }

    private fun notifyItemChanged(collect: Boolean) {
        mAdapter.data[collectPosition].collect = collect
        mAdapter.notifyItemChanged(collectPosition)
    }

    override fun finish() {
        setResult(
            Activity.RESULT_OK,
            Intent().apply {
                putExtra(
                    AnnotationValue.BUNDLE_KEY_SEARCH,
                    if (mAdapter.data.isNotEmpty()) search else null
                )
            })
        super.finish()
    }

    override fun onBackPressed() {
        finish()
    }
}