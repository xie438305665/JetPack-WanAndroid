package com.wanandroid.bridge.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.wanandroid.bridge.AppConfig
import com.wanandroid.bridge.annotation.EventBusKey
import com.wanandroid.bridge.ext.clickNoRepeat
import com.wanandroid.bridge.ext.getVmClazz
import com.wanandroid.bridge.ext.gone
import com.wanandroid.bridge.ext.visible
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetViewModel.LoadStatus
import com.zhixinhuixue.library.net.NetViewModel.RequestType
import kotlinx.android.synthetic.main.activity_base_layout.*

/**
 *  @description:Fragment基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseFragment<T, VM : BaseViewModel> : Fragment(), Observer<T> {
    var bundle: Bundle? = null
    lateinit var baseVm: VM
    lateinit var activity: Activity
    private lateinit var loadService: LoadService<*>
    lateinit var loading: View

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
        val view = View.inflate(activity, R.layout.fragment_base_layout, null)
        val rootLayout = view.findViewById<FrameLayout>(R.id.baseRootLayout)
        rootLayout.addView(View.inflate(activity, getLayoutId(), null))
        if (showLoading()) {
            loading = View.inflate(activity, R.layout.layout_load_put, null)
            baseRootLayout.addView(loading)
        }
        loadService = initLoadService(view)
        initObserver()
        return loadService.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCreate(view, bundle)
    }

    /**
     * 布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 视图 跟 ViewMode 绑定   根据业务可以重写函数
     */
    open fun initObserver() {
        baseVm.loadVm.observe(viewLifecycleOwner, Observer {
            refreshLoadStatus(it.loadStatus, it.requestType)
        })
        appContext.configEvent.configVm.observe(this, Observer {
            if (it.key == EventBusKey.CONFIG) {
                changeConfigUi(it.data)
            }
        })
    }

    /**
     * 初始化 根据业务可以重写函数
     * @param root View
     * @param bundle Bundle?
     */
    abstract fun initCreate(root: View, bundle: Bundle?)

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
     * RequestType.PUT/RequestType.DELETE  建议都开启
     * 是否显示loading (修改/删除等操作) 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    protected open fun showLoading(): Boolean {
        return false
    }

    /**
     * 请求发生改变刷新Load  根据业务可以重写函数
     * @param loadStatus  @link[LoadStatus] 加载状态
     * @param requestType  @link[requestType] 请求方式
     */
    open fun refreshLoadStatus(@LoadStatus loadStatus: Int, @RequestType requestType: Int) {
        when (requestType) {
            RequestType.DEFAULT -> {
                if (showLoading()) {
                    loading.gone()
                }
                when (loadStatus) {
                    LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[0]::class.java)
                    LoadStatus.EMPTY -> loadService.showCallback(appContext.loadStatusCallbackList[1]::class.java)
                    LoadStatus.ERROR -> loadService.showCallback(appContext.loadStatusCallbackList[2]::class.java)
                    else -> loadService.showSuccess()
                }
            }
            RequestType.DELETE, RequestType.PUT -> {
                when (loadStatus) {
                    LoadStatus.START -> if (showLoading() && !loading.isVisible) loading.visible()
                    else -> if (showLoading() && loading.isVisible) loading.gone() else loadService.showSuccess()
                }
            }
        }
    }

    /**
     * LiveData发生改变刷新UI
     * @param data 数据
     */
    abstract fun refreshView(data: T?)

    /**
     * 修改APP相关配置 根据业务可以重写函数
     * @param config AppConfig
     */
    protected open fun changeConfigUi(config: AppConfig) {

    }

    /**
     * 网络请求重试 根据业务可以重写函数
     */
    open fun onNetRetry() {
        baseVm.onNetRequest(RequestType.DEFAULT, null)
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
    override fun onChanged(t: T?) {
        refreshView(t)
    }
}