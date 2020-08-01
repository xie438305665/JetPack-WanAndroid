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
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.ext.getVmClazz
import com.wanandroid.bridge.ext.isEquals
import com.wanandroid.bridge.ext.toast
import com.wanandroid.bridge.ext.visibleOrGone
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.NetViewModel.LoadStatus

/**
 *  @description:上拉加载 下拉刷新Fragment基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseRefreshFragment<T, VM : BaseViewModel, A : BaseQuickAdapter<T, BaseViewHolder>> :
    Fragment(), BaseRefreshObserver<T>,
    SimpleAdapterListener<T, BaseViewHolder> {

    var page = 0
    var bundle: Bundle? = null
    protected lateinit var adapter: A
    protected lateinit var baseVm: VM
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var ballPulseFooter: BallPulseFooter
    private lateinit var materialHeader: MaterialHeader
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
    protected open fun getLayoutId(): Int = R.layout.fragment_base_refresh

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
        @NetViewModel.RequestType requestType: Int
    ) {
        if (requestType.isEquals(NetViewModel.RequestType.DEFAULT)) {
            when (loadStatus) {
                LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[0]::class.java)
                LoadStatus.EMPTY -> loadService.showCallback(appContext.loadStatusCallbackList[1]::class.java)
                LoadStatus.ERROR -> loadService.showCallback(appContext.loadStatusCallbackList[2]::class.java)
                else -> loadService.showSuccess()
            }
            return
        }
        if (requestType.isEquals(NetViewModel.RequestType.REFRESH)
            && loadStatus.isEquals(NetViewModel.LoadStatus.SUCCESS)
        ) {
            refreshLayout.finishRefresh()
            return
        }
        refreshLayout.finishLoadMore()
    }

    /**
     * 网络请求重试 根据业务可以重写函数
     */
    protected open fun onNetRetry() {
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT, null)
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
            refreshLoadStatus(LoadStatus.SUCCESS, NetViewModel.RequestType.DEFAULT)
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
     * 初始化Adapter
     */
    protected open fun initAdapter() {
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
    protected open fun refreshView(data: MutableList<T>) {
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
     * BaseRefreshObserver
     */
    override fun onChanged(t: MutableList<T>?) {
        t ?: return
        refreshView(t)
    }
}