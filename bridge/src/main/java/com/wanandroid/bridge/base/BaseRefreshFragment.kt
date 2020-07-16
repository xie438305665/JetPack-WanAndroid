package com.wanandroid.bridge.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.ext.isEquals
import com.wanandroid.bridge.ext.toast
import com.wanandroid.bridge.ext.visibleOrGone
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetViewModel

/**
 *  @description:上拉加载 下拉刷新Fragment基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseRefreshFragment<T, VM : BaseViewModel, A : BaseQuickAdapter<T, BaseViewHolder>> :
    BaseFragment<T, VM>(), Observer<MutableList<T>>,
    SimpleAdapterListener<T, BaseViewHolder> {


     var page = 0
    lateinit var adapter: A
    lateinit var refreshLayout: SmartRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var ballPulseFooter: BallPulseFooter
    lateinit var materialHeader: MaterialHeader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
        refreshLayout = rootView.findViewById(R.id.baseSmartRefreshLayout)
        recyclerView = rootView.findViewById(R.id.baseRecyclerView)
        ballPulseFooter = rootView.findViewById(R.id.baseBallPulseFooter)
        materialHeader = rootView.findViewById(R.id.baseMaterialHeader)
        refreshLayout.setOnRefreshListener {
            if (showMaterialHeader())
                onRefresh()
        }
        refreshLayout.setOnLoadMoreListener {
            if (showBallPulseFooter())
                onLoadMore()
        }
        refreshLayout.setEnableAutoLoadMore(true)
        refreshLayout.setEnableRefresh(showMaterialHeader())
        refreshLayout.setEnableLoadMore(showBallPulseFooter())
        materialHeader.visibleOrGone(showMaterialHeader())
        ballPulseFooter.visibleOrGone(showBallPulseFooter())
        recyclerView.setHasFixedSize(true)
        loadService = initLoadService(rootView)
        initAdapter()
        initObserver()
        return loadService.loadLayout
    }

    /**
     * 布局Id
     */
    override fun getLayoutId() = R.layout.fragment_base_list

    /**
     * Adapter
     */
    abstract fun getBaseQuickAdapter(): A?

    /**
     * 是否显示BallPulseFooter
     */
    open fun showBallPulseFooter(): Boolean = true

    /**
     * 是否显示MaterialHeader
     */
    open fun showMaterialHeader(): Boolean = true

    /**
     * 下拉刷新
     */
    open fun onRefresh() {
        page = 0
        baseVm.onNetRequest(
            NetViewModel.RequestType.REFRESH,
            mapOf<String, Any>(Pair("page", page))
        )
    }

    /**
     * 上拉加载
     */
    open fun onLoadMore() {
        baseVm.onNetRequest(
            NetViewModel.RequestType.LOAD_MORE,
            mapOf<String, Any>(Pair("page", page))
        )
    }

    /**
     * 初始化Adapter
     */
    open fun initAdapter() {
        getBaseQuickAdapter()?.let {
            it.setEmptyView(R.layout.layout_load_empty)
            it.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
            adapter = it
            recyclerView.adapter = adapter
        }
    }

    /**
     * 刷新Adapter
     * @param data MutableList<T>
     */
    open fun refreshView(data: MutableList<T>) {
        if (page == 0) {
            adapter.data.clear()
        }
        if (page > 0 && data.isNullOrEmpty()) {
            "没有更多数据了!".toast()
            return
        }
        page++
        adapter.data.addAll(data)
        adapter.notifyDataSetChanged()
    }

    /**
     *
     * @param loadStatus Int
     * @param requestType Int
     */
    override fun refreshLoadStatus(loadStatus: Int, requestType: Int) {
        super.refreshLoadStatus(loadStatus, requestType)
        if (requestType.isEquals(NetViewModel.RequestType.REFRESH)
            && loadStatus.isEquals(NetViewModel.LoadStatus.SUCCESS)
        ) {
            refreshLayout.finishRefresh()
            return
        }
        refreshLayout.finishLoadMore()
    }

    /**
     * Observer接口实现
     */
    override fun onChanged(t: MutableList<T>) {
        refreshView(t)
    }
}