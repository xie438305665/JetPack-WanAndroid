package com.wanandroid.bridge.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.wanandroid.bridge.ext.*
import com.wanandroid.bridge.util.StatusBarUtils
import com.wanandroid.bridge.util.XLog
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.NetStatusEntity
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import com.zhixinhuixue.library.widget.custom.ToolbarClickListener


/**
 *  @description:Activity基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseActivity<T, VM : BaseViewModel> : AppCompatActivity(), Observer<T>,
    ToolbarClickListener {
    lateinit var baseVm: VM
    lateinit var mToolbar: CustomToolbar
    lateinit var loadService: LoadService<*>
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarUtils.darkStyle(this, getColorExt(R.color.colorAccent))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_layout)
        mToolbar = findViewById(R.id.toolbar)
        initToolbar(mToolbar)
        bundle = intent.extras
        baseVm = initViewMode()
        loadService = initLoadService()
        initObserver()
        initCreate(bundle)
    }

    /**
     * 布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化
     */
    abstract fun initCreate(bundle: Bundle?)

    /**
     * LiveData发生改变刷新UI
     * @param data 数据
     */
    abstract fun refreshView(data: T)

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
            refreshLoadStatus(it)
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
        toolbar.setToolbarClickListener(this)
        toolbar.background = getDrawableExt(R.color.colorAccent)
        initToolbarTitle(javaClass.simpleName)
        initToolbarMenu(null, false)
    }

    /**
     * 初始化Toolbar左边Finish
     * @param icon 图标
     */
    protected fun initToolbarFinish(icon: Any?) {
        mToolbar.setLeftIcon(icon)
    }

    /**
     * 初始化LoadService 根据业务可以重写函数
     */
    protected open fun initLoadService(): LoadService<*> {
        return LoadSir.getDefault().register(this) {
            refreshLoadStatus(
                NetStatusEntity(
                    NetViewModel.RequestType.DEFAULT,
                    NetViewModel.LoadStatus.START
                )
            )
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
     * 初始化Toolbar中间Title
     * @param title 标题
     */
    protected fun initToolbarTitle(title: Any?) {
        mToolbar.setTitleText(title)
    }

    /**
     * Toolbar中间Title点击事件
     */
    override fun onTitleClick() {
        XLog.d("toolbarTitle")
    }

    /**
     * 初始化Toolbar右边Menu
     * @param menu Any? 菜单内容
     * @param isTvMenu Boolean  true只显示tvMenu  false只显示btnMenu
     */
    protected fun initToolbarMenu(menu: Any?, isTvMenu: Boolean) {
        if (menu == null) {
            goneViews(mToolbar.tvMenu, mToolbar.btnMenu)
            return
        }
        if (isTvMenu) {
            mToolbar.setMenuText(menu)
        } else {
            mToolbar.setMenuIcon(menu)
        }
    }

    /**
     * Toolbar右边Menu点击事件
     */
    override fun onMenuClick() {
        XLog.d("toolbarMenu")
    }

    /**
     * LiveData发生改变刷新Load  根据业务可以重写函数
     * @param statusEntity  @link[NetStatusEntity]
     */
    open fun refreshLoadStatus(statusEntity: NetStatusEntity) {
        if (isEqualIntExt(statusEntity.requestType, NetViewModel.RequestType.DEFAULT)) {
            when (statusEntity.loadStatus) {
                NetViewModel.LoadStatus.START -> loadService.showCallback(appContext.loadStatusCallbackList[0]::class.java)
                NetViewModel.LoadStatus.EMPTY -> loadService.showCallback(appContext.loadStatusCallbackList[1]::class.java)
                NetViewModel.LoadStatus.ERROR -> loadService.showCallback(appContext.loadStatusCallbackList[2]::class.java)
                NetViewModel.LoadStatus.SUCCESS -> loadService.showSuccess()
                else -> loadService.showSuccess()
            }
            return
        }
        if (isEqualIntExt(statusEntity.requestType, NetViewModel.RequestType.REFRESH)) {
            statusEntity.loadStatus.logD()
            return
        }
        statusEntity.loadStatus.logD()
    }

    /**
     * 网络请求 重试
     */
    protected open fun onNetRetry() {
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT)
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
    override fun onChanged(t: T) {
        refreshView(t)
    }

    override fun onDestroy() {
        loadService.showSuccess()
        super.onDestroy()
    }
}


