package com.wanandroid.bridge.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.wanandroid.bridge.AppConfig
import com.wanandroid.bridge.annotation.EventBusKey
import com.wanandroid.bridge.ext.*
import com.wanandroid.bridge.util.StatusBarUtils
import com.wanandroid.bridge.util.XLog
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetViewModel.LoadStatus
import com.zhixinhuixue.library.net.NetViewModel.RequestType
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import com.zhixinhuixue.library.widget.custom.ToolbarClickListener
import kotlinx.android.synthetic.main.activity_base_layout.*


/**
 *  @description:Activity基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseActivity<T, VM : BaseViewModel> : AppCompatActivity(), Observer<T>,
    ToolbarClickListener {
    lateinit var baseVm: VM
    lateinit var mToolbar: CustomToolbar
    lateinit var mDrawerLayout: DrawerLayout
    lateinit var mDrawerMenu: RecyclerView
    lateinit var loadService: LoadService<*>
    var mBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarUtils.darkStyle(this, R.color.colorGreen.getColor())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_layout)
        mDrawerLayout = findViewById(R.id.baseDrawerLayout)
        mDrawerMenu = findViewById(R.id.baseDrawerMenu)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        mDrawerMenu.visibleOrGone(showDrawerMenu() && showToolbar())
        val contentView = View.inflate(this, getLayoutId(), null)
        baseRootLayout.addView(contentView)
        mToolbar = findViewById(R.id.toolbar)
        initToolbar(mToolbar)
        mBundle = intent.extras
        baseVm = initViewMode()
        loadService = initLoadService(contentView)
        initObserver()
        initCreate(mBundle)
    }

    /**
     * 布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化Toolbar 根据业务可以重写函数
     */
    protected open fun initToolbar(toolbar: CustomToolbar) {
        if (!showToolbar()) {
            toolbar.visibility = View.GONE
            return
        }
        toolbar.run {
            visibility = View.VISIBLE
            addToolbarClickListener(this@BaseActivity)
            background = R.color.colorAccent.getDrawable()
            setToolbarTitle(javaClass.simpleName)
            setToolbarMenu(null, false)
        }
    }

    /**
     * 视图 跟 ViewMode 绑定   根据业务可以重写函数
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
     * 初始化 根据业务可以重写函数
     */
    abstract fun initCreate(bundle: Bundle?)

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
     * 请求发生改变刷新Load  根据业务可以重写函数
     * @param loadStatus  @link[LoadStatus] 加载状态
     * @param requestType  @link[requestType] 请求方式
     */
    open fun refreshLoadStatus(
        @LoadStatus loadStatus: Int = LoadStatus.SUCCESS,
        @RequestType requestType: Int = RequestType.DEFAULT
    ) {
        when (requestType) {
            RequestType.DEFAULT -> {
                when (loadStatus) {
                    LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[0]::class.java)
                    LoadStatus.EMPTY -> loadService.showCallback(appContext.loadStatusCallbackList[1]::class.java)
                    LoadStatus.ERROR -> loadService.showCallback(appContext.loadStatusCallbackList[2]::class.java)
                    else -> loadService.showSuccess()
                }
            }
            RequestType.DELETE, RequestType.PUT -> {
                when (loadStatus) {
                    LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[3]::class.java)
                    else -> loadService.showSuccess()
                }
            }
        }
    }

    /**
     * 数据发生改变刷新UI 根据业务可以重写函数
     * @param data 数据
     */
    abstract fun refreshView(data: T?)

    /**
     * 修改APP相关配置 根据业务可以重写函数
     * @param config AppConfig
     */
    protected open fun changeConfigUI(config: AppConfig) {
    }

    /**
     * 是否显示Toolbar 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    protected open fun showToolbar(): Boolean {
        return true
    }

    /**
     * 是否显示DrawerMenu 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    protected open fun showDrawerMenu(): Boolean {
        return true
    }

    /**
     * Toolbar左边Finish点击事件 根据业务可以重写函数
     */
    override fun onFinishClick() {
        XLog.d("toolbarFinish")
        finish()
    }

    /**
     * Toolbar中间Title点击事件 根据业务可以重写函数
     */
    override fun onTitleClick() {
        XLog.d("toolbarTitle")
    }

    /**
     * Toolbar右边Menu点击事件 根据业务可以重写函数
     */
    override fun onMenuClick() {
        XLog.d("toolbarMenu")
    }

    /**
     * 网络请求重试 根据业务可以重写函数
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
     * Observer接口实现 根据业务可以重写函数
     */
    override fun onChanged(t: T) {
        refreshView(t)
    }
}


