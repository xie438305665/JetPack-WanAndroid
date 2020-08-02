package com.wanandroid.module.user.ui

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.refresh.RefreshFragment
import com.wanandroid.bridge.refresh.RefreshObserver
import com.wanandroid.module.user.R
import com.wanandroid.module.user.model.CollectChildViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.CollectToolEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class CollectChildFragment :
    RefreshFragment<CollectToolEntity, CollectChildViewModel, SimpleAdapter<CollectToolEntity, BaseViewHolder>>(),
    RefreshObserver<CollectToolEntity> {
    private var position = 0

    companion object {
        fun newInstance(currentItem: Int): CollectChildFragment {
            return CollectChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(AnnotationValue.BUNDLE_KEY_ITEM, currentItem)
                }
            }
        }
    }

    override fun getBaseQuickAdapter(): SimpleAdapter<CollectToolEntity, BaseViewHolder>? {
        return SimpleAdapter(R.layout.user_item_collect, mutableListOf(), this)
    }

    override fun initCreate(root: View, savedInstanceState: Bundle?) {
        if (bundle == null) {
            refreshLoadStatus(NetViewModel.LoadStatus.EMPTY, NetViewModel.RequestType.DEFAULT)
            return
        }
        baseVm.onNetRequest(
            NetViewModel.RequestType.DEFAULT,
            mapOf(Pair("page", 0))
        )
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.collectVm.observe(this, this)

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