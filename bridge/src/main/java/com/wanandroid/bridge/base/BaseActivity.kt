package com.wanandroid.bridge.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

/**
 *  @description:Activity基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseActivity<T, VM : BaseViewMode<T>> : AppCompatActivity() {
    var dataVm: VM? = null
    var bundle: Bundle? = null
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = intent.extras
        initCreate(bundle)
        initViewMode()
        setContentView(layoutId)
        initObserver()
        onNetRequest()
    }

    /**
     * 初始化
     */
    abstract fun initCreate(bundle: Bundle?)

    /**
     * 网络请求
     */
    abstract fun onNetRequest()

    /**
     * liveData 跟 ViewMode 绑定
     */
    abstract fun initObserver()

    /**
     * 获取ViewMode
     */
    private fun initViewMode() {
        dataVm?.apply {
            ViewModelProvider(viewModelStore, createFactory()).get(this::class.java)
        }
    }

    /**
     * 创建Factory
     */
    open fun createFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }
}