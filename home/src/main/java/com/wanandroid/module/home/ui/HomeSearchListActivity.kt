package com.wanandroid.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.module.home.R
import com.wanandroid.module.home.model.HomeSearchListModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity
import com.zhixinhuixue.library.net.entity.SearchEntity
import kotlinx.android.synthetic.main.activity_hot_search_home.*

/**
 *  @description:搜索数据列表
 *  @author xcl qq:244672784
 *  @date 2020/7/29
 **/
class HomeSearchListActivity :
    BaseActivity<ListNetEntity<MutableList<ArticleEntity>>, HomeSearchListModel>(),
    Observer<ListNetEntity<MutableList<ArticleEntity>>>,
    SimpleAdapterListener<ArticleEntity, BaseViewHolder> {
    private lateinit var mAdapter: SimpleAdapter<SearchEntity, BaseViewHolder>

    override fun getLayoutId(): Int {
        return R.layout.activity_hot_search_home
    }

    override fun initCreate(bundle: Bundle?) {
        if (bundle == null) {
            refreshLoadStatus()
            return
        }
        mToolbar.setTitleText("")
        initAdapter()
        baseVm.onNetRequest(
            NetViewModel.RequestType.DEFAULT, mapOf(
                Pair("page", 0),
                Pair("k", bundle.getString(AnnotationValue.BUNDLE_KEY_SEARCH, ""))
            )
        )
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.searchVm.observe(this, this)
    }

    private fun initAdapter() {
//        mAdapter = SimpleAdapter(R.layout.item_hot_search_tag_home, mutableListOf(), this)
        rvHotSearch.run {
            setHasFixedSize(true)
            layoutManager = FlexboxLayoutManager(this@HomeSearchListActivity)
            adapter = mAdapter
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ArticleEntity, position: Int) {
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<ArticleEntity, BaseViewHolder>,
        view: View,
        item: ArticleEntity,
        position: Int
    ) {
        super.onBindItemClick(adapter, view, item, position)
    }

    override fun refreshView(data: ListNetEntity<MutableList<ArticleEntity>>?) {

    }
}