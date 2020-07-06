package com.wanandroid.bridge.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


/**
 *  @description:Activity基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseActivity<T, VM : BaseViewModel<T>> : AppCompatActivity() {
    lateinit var dataVm: VM
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = intent.extras
        dataVm = initViewMode()
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
     * liveData 跟 ViewMode 绑定
     */
    abstract fun initObserver()

    /**
     * 网络请求
     */
    abstract fun onNetRequest()

    /**
     * 获取ViewMode
     */
    private fun initViewMode(): VM {
        //JVM如果是1.6 使用
        dataVm.apply {
            ViewModelProvider(viewModelStore, createFactory()).get(this::class.java)
        }
        return dataVm
    }

    /**
     * 创建Factory
     */
    open fun createFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }
}


