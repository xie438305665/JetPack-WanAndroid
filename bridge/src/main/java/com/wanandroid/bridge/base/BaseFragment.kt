package com.wanandroid.bridge.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wanandroid.bridge.ext.getVmClazz
import com.zhixinhuixue.library.net.NetViewModel.LoadStatus
import com.zhixinhuixue.library.net.NetViewModel.RequestType

/**
 *  @description:Fragment基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
abstract class BaseFragment<T, VM : BaseViewModel> : Fragment(),Observer<T> {
    lateinit var baseVm: VM
    var bundle: Bundle? = null
    lateinit var activity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
        baseVm = initViewMode()
        initObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutId(), container)
        initCreate(view, bundle)
        return view
    }

    /**
     * 布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化
     */
    abstract fun initCreate(root:View,bundle: Bundle?)

    /**
     * LiveData发生改变刷新UI
     */
    abstract fun refreshView(data: T)

    /**
     * liveData 跟 ViewMode 绑定   根据业务可以重写函数
     */
    open fun initObserver() {
        baseVm.loadVm.observe(this, Observer {
            refreshLoadStatus(it.loadStatus, it.requestType)
        })
    }

    /**
     * LiveData发生改变刷新Load  根据业务可以重写函数
     * @param loadStatus  @link[LoadStatus] 加载状态
     * @param requestType  @link[requestType] 请求方式
     */
    open fun refreshLoadStatus(
        @LoadStatus loadStatus: Int,
        @RequestType requestType: Int
    ) {
        (activity as BaseActivity<*, *>).refreshLoadStatus(loadStatus, requestType)
    }

    /**
     * 网络请求重试 根据业务可以重写函数
     */
    open fun onNetRetry() {
        baseVm.onNetRequest(RequestType.DEFAULT)
    }

    /**
     * 获取ViewMode 根据业务可以重写函数
     */
    open fun initViewMode(): VM {
        //JVM如果是1.6 使用
        baseVm = ViewModelProvider(viewModelStore, createFactory()).get(getVmClazz(activity))
        return baseVm
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
    override fun onChanged(t: T) {
        refreshView(t)
    }
}