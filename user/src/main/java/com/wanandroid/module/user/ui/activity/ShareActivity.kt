package com.wanandroid.module.user.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.ext.showDialogMessage
import com.wanandroid.bridge.refresh.RefreshActivity
import com.wanandroid.bridge.refresh.RefreshObserver
import com.wanandroid.module.user.R
import com.wanandroid.module.user.model.ShareViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.WebViewEntity
import com.zhixinhuixue.library.widget.custom.CustomToolbar

/**
 *  @description:分享列表
 *  @author xcl qq:244672784
 *  @Date 2020/8/13
 **/
class ShareActivity :
    RefreshActivity<ArticleEntity, ShareViewModel, SimpleAdapter<ArticleEntity, BaseViewHolder>>()
    , RefreshObserver<ArticleEntity>, SimpleAdapterListener<ArticleEntity, BaseViewHolder> {
    private var collectPosition = 0

    companion object {
        const val CODE = 0x105
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.articleVm.observe(this, this)
        baseVm.shareVm.observe(this, Observer {
            notifyItemChanged(it)
        })
    }

    override fun initCreate(bundle: Bundle?) {
        baseVm.onNetRequest(params = mapOf(Pair("page", page)))
    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setTitleText(R.string.user_share_title)
    }

    override fun getBaseQuickAdapter(): SimpleAdapter<ArticleEntity, BaseViewHolder>? {
        return SimpleAdapter(R.layout.user_item_share_article, mutableListOf(), this)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ArticleEntity, position: Int) {
        val author =
            if (item.author.isEmpty()) "${R.string.article_wx.getString()}${item.shareUser}" else "${R.string.article_author.getString()}${item.author}"
        holder.setText(R.id.tv_user_article_item_author, author)
        holder.setText(R.id.tv_user_article_item_title, item.title)
        holder.getView<AppCompatImageView>(R.id.iv_user_article_item_delete).setOnClickListener {
            showDialogMessage("您确定删除这条收藏?",
                negativeButtonText = "取消",
                cancelable = true,
                positiveAction = {
                    this.collectPosition = position
                    this.baseVm.onDeleteArticle(item.id)
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
        UserWebActivity.start(WebViewEntity(item.link, "", item.title, ""), CODE, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != CODE || resultCode != Activity.RESULT_OK || data == null) return
    }

    private fun notifyItemChanged(delete: Boolean) {
        if (delete && collectPosition < mAdapter.data.size) {
            mAdapter.data.removeAt(collectPosition)
            mAdapter.notifyItemRemoved(collectPosition)
            if (mAdapter.data.isNullOrEmpty()) {
                refreshLoadStatus(NetViewModel.RequestType.DEFAULT, NetViewModel.LoadStatus.EMPTY)
            }
        }
    }
}