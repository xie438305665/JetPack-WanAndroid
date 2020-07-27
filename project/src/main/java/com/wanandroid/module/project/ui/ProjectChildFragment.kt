package com.wanandroid.module.project.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import coil.api.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.base.BaseRefreshFragment
import com.wanandroid.bridge.ext.getString
import com.wanandroid.module.project.R
import com.wanandroid.module.project.model.ProjectChildViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ProjectTreeEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class ProjectChildFragment :
    BaseRefreshFragment<ArticleEntity, ProjectChildViewModel, SimpleAdapter<ArticleEntity, BaseViewHolder>>(),
    Observer<MutableList<ArticleEntity>> {
    private var position = 0
    private lateinit var currentItemEntity: ProjectTreeEntity

    companion object {
        const val CURRENT_ITEM_KEY = "currentItem"
        fun newInstance(entity: ProjectTreeEntity): ProjectChildFragment {
            val bundle = Bundle()
            bundle.putParcelable(CURRENT_ITEM_KEY, entity)
            val fragment = ProjectChildFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getBaseQuickAdapter(): SimpleAdapter<ArticleEntity, BaseViewHolder>? {
        return SimpleAdapter(R.layout.item_article_project, mutableListOf(), this)
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
                mapOf(Pair("page", 0), Pair("cid", it.id))
            )
        }
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.projectChildVm.observe(this, this)
        baseVm.collectVm.observe(this, Observer {
            adapter.data[position] = it
            adapter.notifyItemChanged(this.position)
        })
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ArticleEntity, position: Int) {
        val ivCollect = holder.getView<AppCompatImageView>(R.id.iv_project_article_item_collect)
        val tvLink = holder.getView<AppCompatTextView>(R.id.tv_project_article_item_link)
        val author =
            if (item.author.isEmpty()) "${R.string.article_shareUser.getString()}${item.shareUser}" else "${R.string.article_author.getString()}${item.author}"
        val linkName =
            if (TextUtils.isEmpty(currentItemEntity.id)) R.string.article_link.getString() else currentItemEntity.name
        holder.setText(
            R.id.tv_project_article_item_date,
            "${R.string.article_date.getString()}${item.niceDate}"
        )
        holder.setText(
            R.id.tv_project_article_item_type,
            "${item.superChapterName}/${item.chapterName}"
        )
        holder.setText(R.id.tv_project_article_item_author, author)
        holder.setText(R.id.tv_project_article_item_title, item.title)
        holder.setText(R.id.tv_project_article_item_content, item.desc)
        holder.getView<AppCompatImageView>(R.id.tv_project_article_item_icon).load(item.envelopePic)
        tvLink.text = linkName
        ivCollect.isSelected = item.collect
        ivCollect.setOnClickListener {
            this.position = position
            this.baseVm.onNetCollect(!it.isSelected, item)
        }
        tvLink.setOnClickListener {
            if (!TextUtils.isEmpty(currentItemEntity.id)) return@setOnClickListener
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
            mapOf(Pair("page", page), Pair("cid", currentItemEntity.id))
        )
    }

    /**
     * 上拉加载
     */
    override fun onLoadMore() {
        baseVm.onNetRequest(
            NetViewModel.RequestType.LOAD_MORE,
            mapOf(Pair("page", page), Pair("cid", currentItemEntity.id))
        )
    }
}