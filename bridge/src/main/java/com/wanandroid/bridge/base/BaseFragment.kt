package com.wanandroid.bridge.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 *  @description:Fragment基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseFragment<T, VM : BaseViewModel<T>> : Fragment() {
    lateinit var dataVm: VM
    var bundle: Bundle? = null
    lateinit var activity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
        dataVm = initViewMode()
        initObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutId(), container)
        initCreate(view, bundle)
        onNetRequest()
        return view
    }

    /**
     * 布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化
     */
    abstract fun initCreate(view: View, bundle: Bundle?)

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
        return ViewModelProvider.AndroidViewModelFactory.getInstance(activity.application)
    }
}