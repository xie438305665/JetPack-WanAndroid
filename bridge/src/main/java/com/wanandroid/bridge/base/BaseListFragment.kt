package com.wanandroid.bridge.base

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
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.ext.getVmClazz
import com.wanandroid.bridge.ext.isEquals
import com.wanandroid.bridge.ext.logD
import com.wanandroid.bridge.ext.visibleOrGone
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetViewModel.LoadStatus
import com.zhixinhuixue.library.net.NetViewModel.RequestType

/**
 *  @description:Fragment基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseListFragment<T, VM : BaseViewModel, A : BaseQuickAdapter<T, BaseViewHolder>> :
    Fragment(), Observer<MutableList<T>>,
    SimpleAdapterListener<T, BaseViewHolder> {
    var bundle: Bundle? = null
    lateinit var baseVm: VM
    lateinit var adapter: A
    lateinit var loadService: LoadService<*>
    lateinit var activity: Activity
    lateinit var refreshLayout: SmartRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var ballPulseFooter: BallPulseFooter

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
        val rootView = inflater.inflate(R.layout.fragment_base_list, container, false)
        refreshLayout = rootView.findViewById(R.id.baseSmartRefreshLayout)
        recyclerView = rootView.findViewById(R.id.baseRecyclerView)
        ballPulseFooter = rootView.findViewById(R.id.baseBallPulseFooter)
        recyclerView.setHasFixedSize(true)
        ballPulseFooter.visibleOrGone(showBallPulseFooter())
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
     * 是否显示BallPulseFooter
     */
    open fun showBallPulseFooter(): Boolean = true

    /**
     * Adapter
     */
    abstract fun getBaseQuickAdapter(): A?

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
     * 初始化
     */
    abstract fun initCreate(root: View, bundle: Bundle?)

    /**
     * liveData 跟 ViewMode 绑定   根据业务可以重写函数
     */
    open fun initObserver() {
        baseVm.loadVm.observe(viewLifecycleOwner, Observer {
            refreshLoadStatus(it.loadStatus, it.requestType)
        })
    }

    /**
     * LiveData发生改变刷新UI
     */
    open fun refreshView(data: MutableList<T>) {
        adapter.data.addAll(data)
        adapter.notifyDataSetChanged()
    }

    /**
     * LiveData发生改变刷新Load  根据业务可以重写函数
     * @param loadStatus  @link[LoadStatus] 加载状态
     * @param requestType  @link[requestType] 请求方式
     */
    open fun refreshLoadStatus(@LoadStatus loadStatus: Int, @RequestType requestType: Int) {
        if (requestType.isEquals(RequestType.DEFAULT)) {
            when (loadStatus) {
                LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[0]::class.java)
                LoadStatus.EMPTY -> loadService.showCallback(appContext.loadStatusCallbackList[1]::class.java)
                LoadStatus.ERROR -> loadService.showCallback(appContext.loadStatusCallbackList[2]::class.java)
                else -> loadService.showSuccess()
            }
            return
        }
        if (requestType.isEquals(RequestType.REFRESH)) {
            loadStatus.logD()
            return
        }
        loadStatus.logD()
    }

    /**
     * 网络请求重试 根据业务可以重写函数
     */
    open fun onNetRetry() {
        baseVm.onNetRequest(RequestType.DEFAULT)
    }

    /**
     * 获取ViewMode 根据业务可以重写函数
     */
    open fun initViewMode(): VM {
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
        }
    }

    /**
     * 创建Factory 根据业务可以重写函数
     */
    open fun createFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(activity.application)
    }

    /**
     * Observer接口实现
     */
    override fun onChanged(t: MutableList<T>) {
        refreshView(t)
    }
}