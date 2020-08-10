package com.wanandroid.module.square.ui.fragment

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
import com.wanandroid.bridge.ext.getColor
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.refresh.RefreshFragment
import com.wanandroid.module.square.R
import com.wanandroid.module.square.model.SquareChildViewModel
import com.wanandroid.module.square.ui.activity.SquareWebActivity
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.WebViewEntity

/**
 *  @description:广场
 *  @author xcl qq:244672784
 *  @date 2020/8/5
 **/
class SquareChildFragment :
    RefreshFragment<ArticleEntity, SquareChildViewModel, SimpleAdapter<ArticleEntity, BaseViewHolder>>(),
    Observer<MutableList<ArticleEntity>> {
    private var collectPosition = 0
    private var currentItem = 0
    companion object {
        const val CODE = 0x102
        const val CURRENT_ITEM_KEY = "currentItem"
        fun newInstance(currentItem: Int): SquareChildFragment {
            return SquareChildFragment()
                .apply {
                    arguments = Bundle().apply {
                        putInt(CURRENT_ITEM_KEY, currentItem)
                    }
                }
        }
    }

    override fun getBaseQuickAdapter(): SimpleAdapter<ArticleEntity, BaseViewHolder>? {
        return SimpleAdapter(R.layout.square_item_article, mutableListOf(), this)
    }

    override fun initCreate(root: View, savedInstanceState: Bundle?) {
        if (bundle == null) {
            refreshLoadStatus(NetViewModel.LoadStatus.EMPTY, NetViewModel.RequestType.DEFAULT)
            return
        }
        currentItem = bundle?.getInt(CURRENT_ITEM_KEY, 0) ?: 0
        when (currentItem) {
            0 -> baseVm.onNetQuestion(
                NetViewModel.RequestType.DEFAULT,
                mapOf(Pair("page", 0))
            )
            1 -> baseVm.onNetRequest(
                NetViewModel.RequestType.DEFAULT,
                mapOf(Pair("page", 0))
            )
        }
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.articleVm.observe(this, this)
        baseVm.collectVm.observe(this, Observer {
            notifyItemChanged(it)
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
        ivCollect.drawable.setTint(if (item.collect) R.color.colorTag.getColor() else R.color.colorTitle.getColor())
        ivCollect.isSelected = item.collect
        ivCollect.setOnClickListener {
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
        SquareWebActivity.start(
            WebViewEntity(item.link, "", item.title, ""),
            CODE,
            this
        )
    }

    /**
     * 下拉刷新
     */
    override fun onRefresh() {
        page = 0
        when (currentItem) {
            0 -> baseVm.onNetQuestion(
                NetViewModel.RequestType.REFRESH,
                mapOf(Pair("page", 0))
            )
            1 -> baseVm.onNetRequest(
                NetViewModel.RequestType.REFRESH,
                mapOf(Pair("page", 0))
            )
        }
    }

    /**
     * 上拉加载
     */
    override fun onLoadMore() {
        when (currentItem) {
            0 -> baseVm.onNetQuestion(
                NetViewModel.RequestType.LOAD_MORE,
                mapOf(Pair("page", 0))
            )
            1 -> baseVm.onNetRequest(
                NetViewModel.RequestType.LOAD_MORE,
                mapOf(Pair("page", 0))
            )
        }
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
}