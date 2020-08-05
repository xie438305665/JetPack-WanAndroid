package com.wanandroid.bridge.refresh

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.ext.*
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetViewModel.LoadStatus
import com.zhixinhuixue.library.net.NetViewModel.RequestType


/**
 *  @description:上拉加载 下拉刷新Fragment基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class RefreshFragment<T, VM : BaseViewModel, A : BaseQuickAdapter<T, BaseViewHolder>> :
    Fragment(), RefreshObserver<T>,
    SimpleAdapterListener<T, BaseViewHolder> {

    var page = 0
    var bundle: Bundle? = null
    var isLoading = false
    protected lateinit var mAdapter: A
    protected lateinit var baseVm: VM
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var ballPulseFooter: BallPulseFooter
    private lateinit var materialHeader: MaterialHeader
    private lateinit var refreshFloatBtn: FloatingActionButton
    private lateinit var loadService: LoadService<*>
    private lateinit var activity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
        baseVm = initViewMode()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
        rootView.run {
            refreshLayout = findViewById(R.id.smartRefreshLayout)
            recyclerView = findViewById(R.id.refreshRecyclerView)
            ballPulseFooter = findViewById(R.id.refreshBallPulseFooter)
            materialHeader = findViewById(R.id.refreshMaterialHeader)
            refreshFloatBtn = findViewById(R.id.refreshFloatBtn)
            loadService = initLoadService(this)
        }
        refreshLayout.run {
            setEnableAutoLoadMore(true)
            setEnableRefresh(showMaterialHeader() && !isLoading)
            setEnableLoadMore(showBallPulseFooter() && !isLoading)
            setOnRefreshListener { onRefresh() }
            setOnLoadMoreListener { onLoadMore() }
        }
        materialHeader.visibleOrGone(showMaterialHeader())
        ballPulseFooter.visibleOrGone(showBallPulseFooter())
        recyclerView.run {
            setHasFixedSize(true)
            if (showFloatBtn())
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (dy < -1) upMove()
                        super.onScrolled(recyclerView, dx, dy)
                    }
                })
        }
        initAdapter()
        initObserver()
        return loadService.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCreate(view, bundle)
    }

    /**
     * 初始化
     */
    abstract fun initCreate(root: View, bundle: Bundle?)

    /**
     * Adapter
     */
    abstract fun getBaseQuickAdapter(): A?

    /**
     * 布局Id 根据业务可以重写函数
     */
    protected open fun getLayoutId(): Int = R.layout.fragment_refresh_layout

    /**
     * liveData 跟 ViewMode 绑定   根据业务可以重写函数
     */
    protected open fun initObserver() {
        baseVm.loadVm.observe(viewLifecycleOwner, Observer {
            refreshLoadStatus(it.loadStatus, it.requestType)
        })
    }

    /**
     * LiveData发生改变刷新Load  根据业务可以重写函数
     * @param loadStatus  @link[LoadStatus] 加载状态
     * @param requestType  @link[requestType] 请求方式
     */
    protected open fun refreshLoadStatus(
        @LoadStatus loadStatus: Int,
        @RequestType requestType: Int
    ) {
        isLoading = true
        if (requestType.isEquals(RequestType.DEFAULT)) {
            when (loadStatus) {
                LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[0]::class.java)
                LoadStatus.EMPTY -> loadService.showCallback(appContext.loadStatusCallbackList[1]::class.java)
                LoadStatus.ERROR -> loadService.showCallback(appContext.loadStatusCallbackList[2]::class.java)
                else -> {
                    isLoading = false
                    loadService.showSuccess()
                }
            }
            return
        }
        if (!loadStatus.isEquals(LoadStatus.START) && !requestType.isEquals(RequestType.DEFAULT)) {
            isLoading = false
            if (requestType.isEquals(RequestType.REFRESH)) refreshLayout.finishRefresh() else refreshLayout.finishLoadMore()
        }
    }

    /**
     * 网络请求重试 根据业务可以重写函数
     */
    protected open fun onNetRetry() {
        baseVm.onNetRequest(RequestType.DEFAULT, null)
    }

    /**
     * 获取ViewMode 根据业务可以重写函数
     */
    protected open fun initViewMode(): VM {
        //JVM如果是1.6 使用
        baseVm = ViewModelProvider(viewModelStore, createFactory()).get(getVmClazz(this))
        return baseVm
    }

    /**
     * 初始化LoadService 根据业务可以重写函数
     */
    protected open fun initLoadService(view: View): LoadService<*> {
        return LoadSir.getDefault().register(view) {
            refreshLoadStatus(LoadStatus.SUCCESS, RequestType.DEFAULT)
            it.setOnClickListener {
                onNetRetry()
            }
        }
    }

    /**
     * 创建Factory 根据业务可以重写函数
     */
    protected open fun createFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(activity.application)
    }


    /**
     * 是否显示BallPulseFooter
     */
    protected open fun showBallPulseFooter(): Boolean = true

    /**
     * 是否显示MaterialHeader
     */
    protected open fun showMaterialHeader(): Boolean = true

    /**
     * 上滑是否显示FloatBtn
     * @return Boolean
     */
    protected open fun showFloatBtn(): Boolean = true

    /**
     * 初始化Adapter
     */
    protected open fun initAdapter() {
        getBaseQuickAdapter()?.let {
            it.setEmptyView(R.layout.layout_load_empty)
            it.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
            it.setOnItemClickListener { adapter, view, position ->
                onBindItemClick(
                    adapter as BaseQuickAdapter<T, BaseViewHolder>,
                    view,
                    it.getItem(position),
                    position
                )
            }
            mAdapter = it
            recyclerView.adapter = mAdapter
        }
    }

    /**
     * RecyclerView 向上移动
     */
    protected open fun upMove() {
        if (refreshFloatBtn.isGone()) {
            refreshFloatBtn.visible()
        }
    }

    /**
     * 刷新Adapter
     * @param data MutableList<T>
     */
    protected open fun refreshView(data: MutableList<T>?) {
        data ?: return
        if (page == 0) {
            mAdapter.data.clear()
        }
        if (page > 0 && data.isNullOrEmpty()) {
            "没有更多数据了!".toast()
            return
        }
        page++
        mAdapter.data.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

    /**
     * BaseRefreshObserver
     */
    override fun onChanged(t: MutableList<T>?) {
        refreshView(t)
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        page = 0
        baseVm.onNetRequest(
            RequestType.REFRESH,
            mapOf<String, Any>(Pair("page", page))
        )
    }

    /**
     * 加载更多
     */
    override fun onLoadMore() {
        baseVm.onNetRequest(
            RequestType.LOAD_MORE,
            mapOf<String, Any>(Pair("page", page))
        )
    }
}