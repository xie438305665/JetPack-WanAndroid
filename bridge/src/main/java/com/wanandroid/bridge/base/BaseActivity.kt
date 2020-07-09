package com.wanandroid.bridge.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wanandroid.bridge.ext.getColorExt
import com.wanandroid.bridge.ext.getDrawableExt
import com.wanandroid.bridge.ext.getVmClazz
import com.wanandroid.bridge.ext.goneViews
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
    lateinit var toolbar: CustomToolbar
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.darkStyle(this, getColorExt(R.color.colorAccent))
        bundle = intent.extras
        baseVm = initViewMode()
        initToolbar(toolbar)
        initCreate(bundle)
        setContentView(getLayoutId())
        initObserver()
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
     * liveData 跟 ViewMode 绑定   根据业务可以重写函数
     */
    protected open fun initObserver() {
        baseVm.loadVm.observe(this, Observer {
            refreshLoadStatus(it)
        })
    }

    /**
     * 是否显示Toolbar 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    protected open fun showToolbar(): Boolean {
        return true
    }

    /**
     * 初始化Toolbar 根据业务可以重写函数
     */
    protected open fun initToolbar(toolbar: CustomToolbar) {
        if (!showToolbar()) {
            toolbar.visibility = View.GONE
            return
        }
        toolbar.background = getDrawableExt(R.color.colorAccent)
        toolbar.visibility = View.VISIBLE
        initToolbarTitle(javaClass.simpleName)
        initToolbarMenu(null, false)
        toolbar.setToolbarClickListener(this)
    }

    /**
     * 初始化Toolbar左边Finish
     * @param icon 图标
     */
    protected fun initToolbarFinish(icon: Any?) {
        toolbar.setLeftIcon(icon)
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
        toolbar.setTitleText(title)
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
            goneViews(toolbar.tvMenu, toolbar.btnMenu)
            return
        }
        if (isTvMenu) {
            toolbar.setMenuText(menu)
        } else {
            toolbar.setMenuIcon(menu)
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
        when (statusEntity.loadStatus) {
            NetViewModel.LoadStatus.START -> XLog.d(statusEntity.loadStatus)
            NetViewModel.LoadStatus.EMPTY -> XLog.d(statusEntity.loadStatus)
            NetViewModel.LoadStatus.ERROR -> XLog.d(statusEntity.loadStatus)
            NetViewModel.LoadStatus.SUCCESS -> XLog.d(statusEntity.loadStatus)
            else -> XLog.d(statusEntity.loadStatus)
        }
    }

    /**
     * 网络请求重试 根据业务可以重写函数
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
}


