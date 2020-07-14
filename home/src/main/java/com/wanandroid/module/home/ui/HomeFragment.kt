package com.wanandroid.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.base.BaseAdapter
import com.wanandroid.bridge.base.BaseAdapterListener
import com.wanandroid.bridge.base.BaseFragment
import com.wanandroid.bridge.ext.logD
import com.wanandroid.module.home.R
import com.wanandroid.module.home.adapter.HomeBannerAdapter
import com.wanandroid.module.home.model.HomeViewModel
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
class HomeFragment : BaseFragment<MutableList<BannerEntity>, HomeViewModel>(),
    Observer<MutableList<BannerEntity>>, BaseAdapterListener<ArticleTopEntity, BaseViewHolder> {
    lateinit var adapter: BaseAdapter<ArticleTopEntity, BaseViewHolder>
    private var page: Int = 0
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initCreate(root: View, bundle: Bundle?) {
        homeRecyclerView.setHasFixedSize(true)
        adapter = BaseAdapter(R.layout.activity_base_layout, mutableListOf(), this)
        homeRecyclerView.adapter = adapter
        homeBanner.addBannerLifecycleObserver(this)
            .setAdapter(HomeBannerAdapter(ArrayList()))
            .setIndicator(CircleIndicator(activity))
            .setOnBannerListener { _, position -> position.logD() }
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT)
        baseVm.onNetArticleList(page, NetViewModel.RequestType.DEFAULT)
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.bannerVm.observe(this, this)
        baseVm.homeVm.observe(this, Observer {
            adapter.data.addAll(it.datas)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onNetRetry() {
        super.onNetRetry()
        this.page = 0
        baseVm.onNetArticleList(this.page, NetViewModel.RequestType.DEFAULT)
    }

    override fun refreshView(data: MutableList<BannerEntity>) {
        homeBanner.adapter.setDatas(data.toList())
        homeBanner.adapter.notifyDataSetChanged()
        homeBanner.start()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ArticleTopEntity, position: Int) {
    }

    override fun onBindItemClick(
        adapter: BaseAdapter<ArticleTopEntity, BaseViewHolder>,
        view: View,
        position: Int
    ) {
    }
}