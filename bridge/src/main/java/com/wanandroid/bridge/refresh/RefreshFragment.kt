package com.wanandroid.bridge.refresh

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.wanandroid.bridge.AppConfig
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.annotation.EventBusKey
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.event.TestViewModel
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
    var config: AppConfig = AppConfig()

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
        val rootView = View.inflate(activity, getLayoutId(), null).apply {
            refreshLayout = findViewById(R.id.smartRefreshLayout)
            recyclerView = findViewById(R.id.refreshRecyclerView)
            ballPulseFooter = findViewById(R.id.refreshBallPulseFooter)
            materialHeader = findViewById(R.id.refreshMaterialHeader)
            refreshFloatBtn = findViewById(R.id.refreshFloatBtn)
        }
        loadService = initLoadService(rootView)
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
                        if ((recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0) {
                            refreshFloatBtn.gone()
                        } else {
                            if (dy < -1) {
                                changeFloatBtn(true)
                            } else if (dy > 20) {
                                changeFloatBtn(false)
                            }
                        }
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
     * 布局Id 根据业务可以重写函数
     */
    protected open fun getLayoutId(): Int = R.layout.fragment_refresh_layout

    /**
     * 视图 跟 ViewModel 绑定   根据业务可以重写函数
     */
    protected open fun initObserver() {
        baseVm.loadVm.observe(viewLifecycleOwner, Observer {
            refreshLoadStatus(it.loadStatus, it.requestType)
        })
        appContext.configEvent.configVm.observe(viewLifecycleOwner, Observer {
            if (it.key == EventBusKey.CONFIG) {
                "test".logD()
                changeConfigUI(it.data)
            }
        })
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
        }.setCallBack(appContext.loadStatusCallbackList[1]::class.java) { _, emptyView ->
            emptyView.findViewById<AppCompatTextView>(R.id.loadEmpty)
                .clickNoRepeat { onNetRetry() }
        }.setCallBack(appContext.loadStatusCallbackList[2]::class.java) { _, errorView ->
            errorView.findViewById<AppCompatTextView>(
                R.id.loadError
            ).clickNoRepeat { onNetRetry() }
        }
    }

    /**
     * 初始化 根据业务可以重写函数
     */
    abstract fun initCreate(root: View, bundle: Bundle?)

    /**
     * 初始化Adapter 根据业务可以重写函数
     */
    protected open fun initAdapter() {
        getBaseQuickAdapter()?.let {
            it.setEmptyView(R.layout.layout_load_empty)
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
     * Adapter 根据业务可以重写函数
     */
    abstract fun getBaseQuickAdapter(): A?

    /**
     * 请求发生改变刷新Load  根据业务可以重写函数
     * @param loadStatus  @link[LoadStatus] 加载状态
     * @param requestType  @link[requestType] 请求方式
     */
    protected open fun refreshLoadStatus(
        @LoadStatus loadStatus: Int,
        @RequestType requestType: Int
    ) {
        isLoading = true
        when (requestType) {
            RequestType.DEFAULT -> {
                when (loadStatus) {
                    LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[0]::class.java)
                    LoadStatus.EMPTY -> loadService.showCallback(appContext.loadStatusCallbackList[1]::class.java)
                    LoadStatus.ERROR -> loadService.showCallback(appContext.loadStatusCallbackList[2]::class.java)
                    else -> {
                        isLoading = false
                        loadService.showSuccess()
                    }
                }
            }
            RequestType.REFRESH, RequestType.LOAD_MORE -> {
                isLoading = false
                if (requestType.isEquals(RequestType.REFRESH)) refreshLayout.finishRefresh() else refreshLayout.finishLoadMore()
            }
            RequestType.PUT, RequestType.DELETE -> {
                when (loadStatus) {
                    LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[3]::class.java)
                    else -> {
                        isLoading = false
                        loadService.showSuccess()
                    }
                }
            }
        }
    }

    /**
     * 刷新Adapter 根据业务可以重写函数
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
     * 修改APP相关配置 根据业务可以重写函数
     * @param config AppConfig
     */
    protected open fun changeConfigUI(config: AppConfig) {
        if (config == this.config) return
        if (this.config.animation != config.animation) {
            mAdapter.setAnimationWithDefault(getAnimationType(config.animation))
        }
        this.config = config
    }

    /**
     * 网络请求重试 根据业务可以重写函数
     */
    protected open fun onNetRetry() {
        baseVm.onNetRequest(RequestType.DEFAULT, mapOf(Pair("page", page)))
    }

    /**
     * 创建Factory 根据业务可以重写函数
     */
    protected open fun createFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(activity.application)
    }

    /**
     * 是否显示BallPulseFooter 根据业务可以重写函数
     */
    protected open fun showBallPulseFooter(): Boolean = true

    /**
     * 是否显示MaterialHeader 根据业务可以重写函数
     */
    protected open fun showMaterialHeader(): Boolean = true

    /**
     * 上滑是否显示FloatBtn 根据业务可以重写函数
     * @return Boolean
     */
    protected open fun showFloatBtn(): Boolean = true

    /**
     * RecyclerView 上下滑动 控制FloatBtn显示/隐藏 根据业务可以重写函数
     * @param isVisible
     */
    protected open fun changeFloatBtn(isVisible: Boolean) {
        refreshFloatBtn.let { btn ->
            if (btn.isVisible() && !isVisible) {
                btn.gone()
            }
            if (btn.isGone() && isVisible) {
                btn.visible()
                btn.clickNoRepeat {
                    recyclerView.smoothScrollToPosition(0)
                }
            }
        }
    }

    /**
     * BaseRefreshObserver 根据业务可以重写函数
     */
    override fun onChanged(t: MutableList<T>?) {
        refreshView(t)
    }

    /**
     * 刷新 根据业务可以重写函数
     */
    override fun onRefresh() {
        page = 0
        baseVm.onNetRequest(
            RequestType.REFRESH,
            mapOf(Pair("page", page))
        )
    }

    /**
     * 加载更多 根据业务可以重写函数
     */
    override fun onLoadMore() {
        baseVm.onNetRequest(
            RequestType.LOAD_MORE,
            mapOf(Pair("page", page))
        )
    }
}