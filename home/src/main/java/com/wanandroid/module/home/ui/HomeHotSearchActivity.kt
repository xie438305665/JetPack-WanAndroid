package com.wanandroid.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.util.XLog
import com.wanandroid.module.home.R
import com.wanandroid.module.home.model.HomeHotSearchModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.SearchEntity
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import kotlinx.android.synthetic.main.activity_hot_search_home.*

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/29
 **/
class HomeHotSearchActivity : BaseActivity<MutableList<SearchEntity>, HomeHotSearchModel>(),
    Observer<MutableList<SearchEntity>>, SimpleAdapterListener<SearchEntity, BaseViewHolder> {
    private lateinit var mAdapter: SimpleAdapter<SearchEntity, BaseViewHolder>
    override fun getLayoutId(): Int {
        return R.layout.activity_hot_search_home
    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setMenuText("搜索")
        toolbar.setEditText {}
    }

    override fun initCreate(bundle: Bundle?) {
        if (bundle == null) {
            refreshLoadStatus()
            return
        }
        initAdapter()
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT, null)
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.hotVm.observe(this, this)
    }

    private fun initAdapter() {
        mAdapter = SimpleAdapter(R.layout.item_hot_search_home, mutableListOf(), this)
        rvHotSearch.run {
            setHasFixedSize(true)
            layoutManager = FlexboxLayoutManager(this@HomeHotSearchActivity)
            adapter = mAdapter
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: SearchEntity, position: Int) {
        holder.setText(R.id.tv_item_hot_search_tag,item.name)
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<SearchEntity, BaseViewHolder>,
        view: View,
        item: SearchEntity,
        position: Int
    ) {
        super.onBindItemClick(adapter, view, item, position)
    }

    override fun refreshView(data: MutableList<SearchEntity>?) {
        if (data.isNullOrEmpty()) return
        mAdapter.data.clear()
        mAdapter.data.addAll(data)
    }
}