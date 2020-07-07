package com.wanandroid.bridge.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zhixinhuixue.library.net.api.NetService


/**
 *  @description:Activity基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseActivity<T, VM : BaseViewModel<T>> : AppCompatActivity() {
    lateinit var baseVm: VM
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = intent.extras
        baseVm = initViewMode()
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
     * 网络请求重试
     */
    open fun onNetRetry() {
        baseVm.onNetRequest()
    }

    /**
     * 获取ViewMode
     */
    private fun initViewMode(): VM {
        //JVM如果是1.6 使用
        baseVm.apply {
            ViewModelProvider(viewModelStore, createFactory()).get(this::class.java)
        }
        return baseVm
    }

    /**
     * 创建Factory
     */
    open fun createFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }
}


