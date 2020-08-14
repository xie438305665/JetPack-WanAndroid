package com.wanandroid.bridge.refresh

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
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
    var isRequest = false
    lateinit var mAdapter: A
    lateinit var baseVm: VM
    lateinit var mToolbar: CustomToolbar
    lateinit var refreshLayout: SmartRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var ballPulseFooter: BallPulseFooter
    lateinit var materialHeader: MaterialHeader
    lateinit var loadService: LoadService<*>
    var config: AppConfig = AppConfig()

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
        initCreate(mBundle)
    }

    /**
     * 初始化
     */
    abstract fun initCreate(bundle: Bundle?)

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
     * RequestType.PUT/RequestType.DELETE  建议都开启
     * 是否显示loading (修改/删除等操作) 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    protected open fun showLoading(): Boolean {
        return true
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
     * liveData 跟 ViewMode 绑定   根据业务可以重写函数
     */
    protected open fun initObserver() {
        baseVm.loadVm.observe(this, Observer {
            refreshLoadStatus(it.loadStatus, it.requestType)
        })
        appContext.configEvent.configVm.observe(this, Observer {
            if (it.key == EventBusKey.CONFIG) {
                changeConfigUI(it.data)
            }
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
     * 请求发生改变刷新Load  根据业务可以重写函数
     * @param loadStatus  @link[LoadStatus] 加载状态
     * @param requestType  @link[requestType] 请求方式
     */
    protected open fun refreshLoadStatus(
        @LoadStatus loadStatus: Int = LoadStatus.SUCCESS,
        @RequestType requestType: Int = RequestType.DEFAULT
    ) {
        isRequest = true
        when (requestType) {
            RequestType.DEFAULT -> {
                loading.gone()
                when (loadStatus) {
                    LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[0]::class.java)
                    LoadStatus.EMPTY -> loadService.showCallback(appContext.loadStatusCallbackList[1]::class.java)
                    LoadStatus.ERROR -> loadService.showCallback(appContext.loadStatusCallbackList[2]::class.java)
                    else -> {
                        isRequest = false
                        loadService.showSuccess()
                    }
                }
            }
            RequestType.REFRESH, RequestType.LOAD_MORE -> {
                isRequest = false
                if (requestType.isEquals(RequestType.REFRESH)) refreshLayout.finishRefresh() else refreshLayout.finishLoadMore()
            }
            RequestType.PUT, RequestType.DELETE -> {
                when (loadStatus) {
                    LoadStatus.START -> if (showLoading() && !loading.isVisible) loading.visible()
                    else -> {
                        isRequest = false
                        if (showLoading() && loading.isVisible) loading.gone() else loadService.showSuccess()
                    }
                }
            }
        }
    }

    /**
     * 数据发生改变刷新UI
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
     * RecyclerView 上下滑动 控制FloatBtn 显示/隐藏
     * @param isVisible Boolean
     */
    protected open fun changeFloatBtn(isVisible: Boolean) {
        if (refreshFloatBtn.isVisible() && !isVisible) {
            refreshFloatBtn.gone()
        }
        if (refreshFloatBtn.isGone() && isVisible) {
            refreshFloatBtn.visible()
            refreshFloatBtn.clickNoRepeat {
                recyclerView.smoothScrollToPosition(0)
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
     * 网络请求 重试
     */
    protected open fun onNetRetry() {
        baseVm.onNetRequest(RequestType.DEFAULT, null)
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

    /**
     * 列表刷新 根据业务可以重写函数
     */
    override fun onRefresh() {
        page = 0
        baseVm.onNetRequest(
            RequestType.REFRESH,
            mapOf<String, Any>(Pair("page", page))
        )
    }

    /**
     * 加载更多 根据业务可以重写函数
     */
    override fun onLoadMore() {
        baseVm.onNetRequest(
            RequestType.LOAD_MORE,
            mapOf<String, Any>(Pair("page", page))
        )
    }
}


