package com.wanandroid.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.adapter.SimpleMultipleAdapter
import com.wanandroid.bridge.adapter.SimpleMultipleItem
import com.wanandroid.bridge.adapter.SimpleMultipleType
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.ext.toStartActivity
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
    Observer<MutableList<SearchEntity>>, SimpleAdapterListener<SimpleMultipleItem, BaseViewHolder> {
    private lateinit var mAdapter: SimpleMultipleAdapter
    private var searchValue: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_hot_search_home
    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setMenuText("搜索")
        toolbar.setEditText {
            this.searchValue = it.toString().trim()
        }
    }

    override fun initCreate(bundle: Bundle?) {
        if (mBundle == null) {
            mBundle = Bundle()
        }
        initAdapter()
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT, null)
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.hotVm.observe(this, this)
    }

    private fun initAdapter() {
        mAdapter = SimpleMultipleAdapter(
            mutableListOf(), this, mutableListOf(
                SimpleMultipleType(SimpleMultipleType.FORMAT, R.layout.item_hot_search_format_home)
                , SimpleMultipleType(SimpleMultipleType.ITEM, R.layout.item_hot_search_tag_home)
            )
        )
        rvHotSearch.run {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: SimpleMultipleItem, position: Int) {
        when (item.itemType) {
            SimpleMultipleType.FORMAT -> {
            }
            else -> {
            }
        }
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<SimpleMultipleItem, BaseViewHolder>,
        view: View,
        item: SimpleMultipleItem,
        position: Int
    ) {
        super.onBindItemClick(adapter, view, item, position)
        if (item.itemType == SimpleMultipleType.FORMAT) return
        mBundle?.let {
            it.putString(AnnotationValue.BUNDLE_KEY_SEARCH, item.content.toString())
            toStartActivity(HomeSearchListActivity::class.java, it)
        }

    }

    //
    override fun refreshView(data: MutableList<SearchEntity>?) {
        if (data.isNullOrEmpty()) return
        mAdapter.data.clear()
//        mAdapter.data.addAll(data)
//        mAdapter.notifyDataSetChanged()
    }

    override fun onMenuClick() {
        mBundle?.let {
            it.putString(AnnotationValue.BUNDLE_KEY_SEARCH, this.searchValue)
            toStartActivity(HomeSearchListActivity::class.java, it)
        }
    }
}