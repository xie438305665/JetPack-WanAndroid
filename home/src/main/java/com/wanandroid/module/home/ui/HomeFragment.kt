package com.wanandroid.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.wanandroid.bridge.base.BaseFragment
import com.wanandroid.bridge.ext.logD
import com.wanandroid.module.home.R
import com.wanandroid.module.home.adapter.HomeBannerAdapter
import com.wanandroid.module.home.model.HomeViewModel
import com.youth.banner.indicator.CircleIndicator
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.BannerEntity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  @description:首页
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class HomeFragment : BaseFragment<List<BannerEntity>, HomeViewModel>(),
    Observer<List<BannerEntity>> {
    private var page: Int = 0
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initCreate(root: View, bundle: Bundle?) {
        homeRecyclerView.setHasFixedSize(true)
        homeBanner.addBannerLifecycleObserver(this)
            .setAdapter(HomeBannerAdapter(ArrayList()))
            .setIndicator(CircleIndicator(activity))
            .setOnBannerListener { _, position -> position.logD() }
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT)
//        baseVm.onNetArticleList(page, NetViewModel.RequestType.DEFAULT)
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.bannerVm.observe(this, this)
        baseVm.homeVm.observe(this, Observer {

        })
    }

    override fun onNetRetry() {
        super.onNetRetry()
        this.page = 0
        baseVm.onNetArticleList(this.page, NetViewModel.RequestType.DEFAULT)
    }

    override fun refreshView(data: List<BannerEntity>) {
        homeBanner.adapter.setDatas(data)
        homeBanner.start()
    }
}