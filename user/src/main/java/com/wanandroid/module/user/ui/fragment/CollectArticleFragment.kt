package com.wanandroid.module.user.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.ext.showDialogMessage
import com.wanandroid.bridge.ext.toJson
import com.wanandroid.bridge.refresh.RefreshFragment
import com.wanandroid.bridge.refresh.RefreshObserver
import com.wanandroid.module.user.R
import com.wanandroid.module.user.model.CollectArticleViewModel
import com.wanandroid.module.user.ui.activity.UserWebActivity
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.WebViewEntity

/**
 *  @description:收藏文章
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class CollectArticleFragment :
    RefreshFragment<ArticleEntity, CollectArticleViewModel, SimpleAdapter<ArticleEntity, BaseViewHolder>>(),
    RefreshObserver<ArticleEntity> {

    companion object {
        const val CODE = 0x106
    }

    private var collectPosition = 0

    override fun getBaseQuickAdapter(): SimpleAdapter<ArticleEntity, BaseViewHolder>? {
        return SimpleAdapter(R.layout.user_item_collect_article, mutableListOf(), this)
    }

    override fun initCreate(root: View, savedInstanceState: Bundle?) {
        baseVm.onNetRequest(
            requestType = NetViewModel.RequestType.DEFAULT,
            params = mapOf(Pair("page", 0))
        )
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.articleVm.observe(this, this)
        baseVm.collectVm.observe(this, Observer {
            notifyItemChanged(it)
        })
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ArticleEntity, position: Int) {
        val author =
            if (item.author.isEmpty()) "公众号" else "${R.string.article_author.getString()}${item.author}"
        holder.setText(R.id.tv_user_article_item_author, author)
        holder.setText(R.id.tv_user_article_item_title, item.title)
        holder.getView<AppCompatImageView>(R.id.iv_user_article_item_delete).setOnClickListener {
            showDialogMessage(
                "您确定删除这条收藏?",
                negativeButtonText = "取消",
                cancelable = true,
                positiveAction = {
                    this.collectPosition = position
                    this.baseVm.onNetCollect(true, item.originId)
                })
        }
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<ArticleEntity, BaseViewHolder>,
        view: View,
        item: ArticleEntity,
        position: Int
    ) {
        this.collectPosition = position
        item.collect = true
        UserWebActivity.start(
            WebViewEntity(item.link, "", item.title, item.toJson()),
            CODE,
            this
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
        if (!collect && collectPosition < mAdapter.data.size) {
            mAdapter.data.removeAt(collectPosition)
            mAdapter.notifyItemRemoved(collectPosition)
            if (mAdapter.data.isNullOrEmpty()) {
                refreshLoadStatus(NetViewModel.RequestType.DEFAULT, NetViewModel.LoadStatus.EMPTY)
            }
        }
    }
}