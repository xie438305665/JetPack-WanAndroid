package com.wanandroid.module.home.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleMultipleAdapter
import com.wanandroid.bridge.adapter.SimpleMultipleItem
import com.wanandroid.bridge.adapter.SimpleMultipleType
import com.wanandroid.bridge.annotation.AnnotationValue.Companion.BUNDLE_KEY_COLLECT
import com.wanandroid.bridge.ext.*
import com.wanandroid.bridge.refresh.RefreshFragment
import com.wanandroid.module.home.R
import com.wanandroid.module.home.adapter.HomeBannerAdapter
import com.wanandroid.module.home.model.HomeViewModel
import com.wanandroid.module.home.ui.activity.HomeWebActivity
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.BannerEntity
import com.zhixinhuixue.library.net.entity.WebViewEntity

/**
 *  @description:首页
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class HomeFragment :
    RefreshFragment<SimpleMultipleItem, HomeViewModel, SimpleMultipleAdapter>(),
    Observer<MutableList<SimpleMultipleItem>> {
    companion object {
        const val CODE = 0X100
    }

    private var collectPosition: Int = 0
    override fun getBaseQuickAdapter(): SimpleMultipleAdapter? {
        return SimpleMultipleAdapter(
            mutableListOf(),
            this,
            mutableListOf(
                SimpleMultipleType(SimpleMultipleType.BANNER, R.layout.home_item_banner)
                , SimpleMultipleType(SimpleMultipleType.ITEM, R.layout.home_item_article)
            )
        )
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.homeVm.observe(this, this)
        baseVm.collectVm.observe(this, Observer {
            val entity = mAdapter.data[collectPosition].content as ArticleEntity
            notifyItemChanged(entity, it)
        })
    }

    override fun initCreate(root: View, bundle: Bundle?) {
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT, mapOf(Pair("page", 0)))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: SimpleMultipleItem, position: Int) {
        if (item.itemType == SimpleMultipleType.BANNER) {
            onBindBanner(holder, item, position)
            return
        }
        val articleTopEntity = item.content as ArticleEntity
        holder.setText(
            R.id.tv_home_article_item_date,
            "${R.string.home_article_date.getString()}${articleTopEntity.niceDate}"
        )
        holder.setText(
            R.id.tv_home_article_item_type,
            "${articleTopEntity.superChapterName}/${articleTopEntity.chapterName}"
        )
        val name =
            if (articleTopEntity.author.isEmpty()) "${R.string.home_article_shareUser.getString()}${articleTopEntity.shareUser}" else "${R.string.home_article_author.getString()}${articleTopEntity.author}"
        holder.setText(R.id.tv_home_article_item_author, name)
        holder.setText(R.id.tv_home_article_item_title, articleTopEntity.title)
        val ivCollect = holder.getView<AppCompatImageView>(R.id.iv_home_article_item_collect)
        ivCollect.isSelected = articleTopEntity.collect
        ivCollect.clickNoRepeat {
            this.collectPosition = position
            this.baseVm.onNetCollect(articleTopEntity.collect, articleTopEntity.id)
        }
    }

    /**
     * 顶部Banner
     * @param holder BaseViewHolder
     * @param item SimpleMultipleItem
     * @param position Int
     */
    private fun onBindBanner(
        holder: BaseViewHolder,
        item: SimpleMultipleItem,
        position: Int
    ) {
        val data = item.content as MutableList<BannerEntity>
        if (data.isNullOrEmpty()) return
        val homeBanner =
            holder.getViewOrNull<Banner<BannerEntity, HomeBannerAdapter>>(R.id.item_banner_home)
        homeBanner?.let {
            it.stop()
            val layoutParams = it.layoutParams
            layoutParams.height = (getScreenWidth() / 2.6).toInt()
            it.layoutParams = layoutParams
            it.addBannerLifecycleObserver(this)
                .setAdapter(HomeBannerAdapter(data))
                .setIndicator(CircleIndicator(activity))
                .setOnBannerListener { _, position -> position.logD() }.start()
        }
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<SimpleMultipleItem, BaseViewHolder>,
        view: View,
        item: SimpleMultipleItem,
        position: Int
    ) {
        this.collectPosition = position
        val articleEntity = item.content as ArticleEntity
        HomeWebActivity.start(
            WebViewEntity(articleEntity.link, "", articleEntity.title, articleEntity.toJson()),
            CODE,
            this
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != CODE || resultCode != Activity.RESULT_OK || data == null) return
        val entity = mAdapter.data[collectPosition].content as ArticleEntity
        data.extras?.let {
            notifyItemChanged(entity, it.getBoolean(BUNDLE_KEY_COLLECT, false))
        }
    }

    private fun notifyItemChanged(entity: ArticleEntity, collect: Boolean) {
        entity.collect = collect
        mAdapter.data[collectPosition].content = entity
        mAdapter.notifyItemChanged(collectPosition)
    }
}