package com.wanandroid.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.base.BaseFragment
import com.wanandroid.bridge.ext.getScreenWidth
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.ext.logD
import com.wanandroid.module.home.R
import com.wanandroid.module.home.adapter.HomeBannerAdapter
import com.wanandroid.module.home.adapter.HomeMultipleAdapter
import com.wanandroid.module.home.adapter.HomeMultipleItem
import com.wanandroid.module.home.model.HomeViewModel
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleTopEntity
import com.zhixinhuixue.library.net.entity.BannerEntity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  @description:首页
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class HomeFragment : BaseFragment<MutableList<HomeMultipleItem>, HomeViewModel>(),
    Observer<MutableList<HomeMultipleItem>>,
    SimpleAdapterListener<HomeMultipleItem, BaseViewHolder> {
    private lateinit var adapter: HomeMultipleAdapter
    private var page: Int = 0
    private var position: Int = 0
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initCreate(root: View, bundle: Bundle?) {
        homeRecyclerView.setHasFixedSize(true)
        adapter = HomeMultipleAdapter(mutableListOf(), this)
        adapter.apply {
            setEmptyView(R.layout.layout_load_empty)
            setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        }
        homeRecyclerView.adapter = adapter
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT)
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.homeVm.observe(this, this)
        baseVm.collectVm.observe(this, Observer {
            adapter.data[position].content = it
            adapter.notifyItemChanged(this.position)
        })
    }

    override fun refreshView(data: MutableList<HomeMultipleItem>) {
        adapter.data.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: HomeMultipleItem, position: Int) {
        val articleTopEntity = item.content as ArticleTopEntity
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
        ivCollect.setOnClickListener {
            this.position = position
            this.baseVm.onNetCollect(!it.isSelected, articleTopEntity)
        }
    }

    override fun onBindBannerViewHolder(
        holder: BaseViewHolder,
        item: HomeMultipleItem,
        position: Int
    ) {
        val data = item.content as MutableList<BannerEntity>
        if (data.isNullOrEmpty()) return
        val homeBanner =
            holder.getViewOrNull<Banner<BannerEntity, HomeBannerAdapter>>(R.id.item_banner_home)
        homeBanner?.let {
            val layoutParams = it.layoutParams
            layoutParams.height = (getScreenWidth() / 2.8).toInt()
            it.layoutParams = layoutParams
            it.addBannerLifecycleObserver(this)
                .setAdapter(HomeBannerAdapter(data))
                .setIndicator(CircleIndicator(activity))
                .setOnBannerListener { _, position -> position.logD() }
            it.start()
        }
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<HomeMultipleItem, BaseViewHolder>,
        view: View,
        position: Int
    ) {
        position.logD()
    }
}