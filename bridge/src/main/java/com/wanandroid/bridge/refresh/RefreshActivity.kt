package com.wanandroid.bridge.refresh

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
import com.wanandroid.bridge.ext.*
import com.wanandroid.bridge.util.StatusBarUtils
import com.wanandroid.bridge.util.XLog
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetViewModel.LoadStatus
import com.zhixinhuixue.library.net.NetViewModel.RequestType
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import com.zhixinhuixue.library.widget.custom.ToolbarClickListener
import kotlinx.android.synthetic.main.activity_refresh_layout.*


/**
 *  @description:Activity基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class RefreshActivity<T, VM : BaseViewModel, A : BaseQuickAdapter<T, BaseViewHolder>> :
    AppCompatActivity(), RefreshObserver<T>, ToolbarClickListener,
    SimpleAdapterListener<T, BaseViewHolder> {
    var page = 0
    var mBundle: Bundle? = null
    var isLoading = false
    lateinit var mAdapter: A
    lateinit var baseVm: VM
    lateinit var mToolbar: CustomToolbar
    lateinit var refreshLayout: SmartRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var ballPulseFooter: BallPulseFooter
    lateinit var materialHeader: MaterialHeader
    lateinit var loadService: LoadService<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarUtils.darkStyle(this, R.color.colorAccent.getColor())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_layout)
        mBundle = intent.extras
        baseVm = initViewMode()
        mToolbar = findViewById(R.id.toolbar)
        initToolbar(mToolbar)
        refreshLayout = findViewById(R.id.smartRefreshLayout)
        recyclerView = findViewById(R.id.refreshRecyclerView)
        ballPulseFooter = findViewById(R.id.refreshBallPulseFooter)
        materialHeader = findViewById(R.id.refreshMaterialHeader)
        recyclerView.run {
            setHasFixedSize(true)
            if (showFloatBtn())
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (dy < -1) {
                            changeFloatBtn(true)
                        } else if (dy > 20) {
                            changeFloatBtn(false)
                        }
                        super.onScrolled(recyclerView, dx, dy)
                    }
                })
        }
        refreshLayout.run {
            setEnableAutoLoadMore(true)
            setEnableRefresh(showMaterialHeader() && !isLoading)
            setEnableLoadMore(showBallPulseFooter() && !isLoading)
            setOnRefreshListener { onRefresh() }
            setOnLoadMoreListener { onLoadMore() }
            loadService = initLoadService(this)
        }
        materialHeader.visibleOrGone(showMaterialHeader())
        ballPulseFooter.visibleOrGone(showBallPulseFooter())
        initAdapter()
        initObserver()
        initLiveEventBus()
        initCreate(mBundle)
    }

    /**
     * 初始化
     */
    abstract fun initCreate(bundle: Bundle?)

    /**
     * LiveData发生改变刷新UI
     * @param data 数据
     */
    protected open fun refreshView(data: MutableList<T>?) {
        data ?: return
        if (page == 0 && data.isNotEmpty()) {
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
     * Adapter
     */
    abstract fun getBaseQuickAdapter(): A?

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
     * 是否显示Toolbar 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    protected open fun showToolbar(): Boolean {
        return true
    }

    /**
     * liveData 跟 ViewMode 绑定   根据业务可以重写函数
     */
    protected open fun initObserver() {
        baseVm.loadVm.observe(this, Observer {
            refreshLoadStatus(it.loadStatus, it.requestType)
        })
    }

    /**
     * 初始化Toolbar 根据业务可以重写函数
     */
    protected open fun initToolbar(toolbar: CustomToolbar) {
        if (!showToolbar()) {
            toolbar.visibility = View.GONE
            return
        }
        toolbar.visibility = View.VISIBLE
        toolbar.addToolbarClickListener(this)
        toolbar.background = R.color.colorAccent.getDrawable()
        toolbar.setToolbarTitle(javaClass.simpleName)
        toolbar.setToolbarMenu(null, false)
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
     * RecyclerView 上下滑动 控制FloatBtn
     */
    protected open fun changeFloatBtn(isVisible: Boolean) {
        if (refreshFloatBtn.isVisible() && !isVisible) {
            refreshFloatBtn.gone()
        }
        if (refreshFloatBtn.isGone() && isVisible) {
            refreshFloatBtn.visible()
            refreshFloatBtn.clickNoRepeat {
                recyclerView.smoothScrollToPosition(0)
                refreshFloatBtn.gone()
            }
        }
    }

    /**
     * Toolbar左边Finish点击事件
     */
    override fun onFinishClick() {
        XLog.d("toolbarFinish")
        finish()
    }

    /**
     * Toolbar中间Title点击事件
     */
    override fun onTitleClick() {
        XLog.d("toolbarTitle")
    }

    /**
     * Toolbar右边Menu点击事件
     */
    override fun onMenuClick() {
        XLog.d("toolbarMenu")
    }

    /**
     * LiveData发生改变刷新Load  根据业务可以重写函数
     * @param loadStatus  @link[LoadStatus] 加载状态
     * @param requestType  @link[requestType] 请求方式
     */
    protected open fun refreshLoadStatus(
        @LoadStatus loadStatus: Int = LoadStatus.SUCCESS,
        @RequestType requestType: Int = RequestType.DEFAULT
    ) {
        if (requestType.isEquals(RequestType.DEFAULT)) {
            when (loadStatus) {
                LoadStatus.START -> {
                    isLoading = true
                    loadService.showCallback(appContext.loadStatusCallbackList[0]::class.java)
                    return
                }
                LoadStatus.EMPTY -> loadService.showCallback(appContext.loadStatusCallbackList[1]::class.java)
                LoadStatus.ERROR -> loadService.showCallback(appContext.loadStatusCallbackList[2]::class.java)
                else -> loadService.showSuccess()
            }
            isLoading = false
            return
        }
        if (!loadStatus.isEquals(LoadStatus.START) && !requestType.isEquals(RequestType.DEFAULT)) {
            isLoading = false
            if (requestType.isEquals(RequestType.REFRESH)) refreshLayout.finishRefresh() else refreshLayout.finishLoadMore()
        }
    }

    /**
     * LiveEventBus消息监听 根据业务可以重写函数
     */
    protected open fun initLiveEventBus() {}

    /**
     * 网络请求 重试
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
     * 创建Factory 根据业务可以重写函数
     */
    protected open fun createFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    /**
     * Observer接口实现
     */
    override fun onChanged(t: MutableList<T>?) {
        refreshView(t)
    }

    override fun onRefresh() {
        page = 0
        baseVm.onNetRequest(
            RequestType.REFRESH,
            mapOf<String, Any>(Pair("page", page))
        )
    }

    override fun onLoadMore() {
        baseVm.onNetRequest(
            RequestType.LOAD_MORE,
            mapOf<String, Any>(Pair("page", page))
        )
    }
}


