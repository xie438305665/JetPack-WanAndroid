package com.wanandroid.bridge.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wanandroid.bridge.utils.XLog
import com.zhixinhuixue.library.net.NetViewModel


/**
 *  @description:Activity基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseActivity<T, VM : BaseViewModel<T>> : AppCompatActivity(), Observer<T> {
    lateinit var baseVm: VM
    lateinit var toolbar: Toolbar
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = intent.extras
        baseVm = initViewMode()
        initToolbar()
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
    open fun initObserver() {
        baseVm.dataVm.observe(this, this)
        baseVm.loadVm.observe(this, Observer {
            refreshLoadStatus(it)
        })
    }

    /**
     * 是否显示Toolbar 根据业务可以重写函数
     * @return Boolean  true显示 false隐藏
     */
    open fun showToolbar(): Boolean {
        return true
    }

    /**
     * 初始化Toolbar 根据业务可以重写函数
     */
    open fun initToolbar() {
        if (showToolbar()) return
        toolbar.title = javaClass.simpleName
    }

    /**
     * 刷新加载状态
     * @param enum EnumStatus @link[com.zhixinhuixue.library.net.NetViewModel.EnumStatus]
     */
    open fun refreshLoadStatus(enum: NetViewModel.EnumStatus) {
        when (enum) {
            NetViewModel.EnumStatus.START -> XLog.d(enum)
            NetViewModel.EnumStatus.EMPTY -> XLog.d(enum)
            NetViewModel.EnumStatus.ERROR -> XLog.d(enum)
            NetViewModel.EnumStatus.SUCCESS -> XLog.d(enum)
            else -> XLog.d(enum)
        }
    }

    /**
     * 网络请求重试 根据业务可以重写函数
     */
    open fun onNetRetry() {
        baseVm.onNetRequest()
    }

    /**
     * 获取ViewMode 根据业务可以重写函数
     */
    open fun initViewMode(): VM {
        //JVM如果是1.6 使用
        baseVm.apply {
            ViewModelProvider(viewModelStore, createFactory()).get(this::class.java)
        }
        return baseVm
    }

    /**
     * 创建Factory 根据业务可以重写函数
     */
    open fun createFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    /**
     * Observer接口实现
     */
    override fun onChanged(t: T) {
        refreshView(t)
    }
}


