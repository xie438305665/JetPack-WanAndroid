package com.wanandroid.module.user.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.refresh.RefreshFragment
import com.wanandroid.bridge.refresh.RefreshObserver
import com.wanandroid.module.user.R
import com.wanandroid.module.user.model.CollectURLViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.CollectToolEntity

/**
 *  @description: 收藏网址
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class CollectURLFragment :
    RefreshFragment<CollectToolEntity, CollectURLViewModel, SimpleAdapter<CollectToolEntity, BaseViewHolder>>(),
    RefreshObserver<CollectToolEntity> {

    override fun getBaseQuickAdapter(): SimpleAdapter<CollectToolEntity, BaseViewHolder>? {
        return SimpleAdapter(R.layout.user_item_collect_url, mutableListOf(), this)
    }

    override fun initCreate(root: View, savedInstanceState: Bundle?) {
        baseVm.onNetRequest(requestType = NetViewModel.RequestType.DEFAULT, params = null)
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.urlVm.observe(this, Observer {

        })
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: CollectToolEntity, position: Int) {

    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<CollectToolEntity, BaseViewHolder>,
        view: View,
        item: CollectToolEntity,
        position: Int
    ) {
        super.onBindItemClick(adapter, view, item, position)
    }

    /**
     * 下拉刷新
     */
    override fun onRefresh() {

    }

    override fun showBallPulseFooter(): Boolean {
        return false
    }
}