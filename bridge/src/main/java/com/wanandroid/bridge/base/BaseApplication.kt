package com.wanandroid.bridge.base

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.view.Gravity
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.ToastUtils
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.liulishuo.filedownloader.FileDownloader
import com.wanandroid.bridge.HeadInterceptor
import com.wanandroid.bridge.event.ConfigViewModel
import com.wanandroid.bridge.ext.*
import com.wanandroid.bridge.util.XLog
import com.wanandroid.developer.library.bridge.BuildConfig
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetRetrofit


/**
 *  @description:Application
 *  @author xcl qq:244672784
 *  @Date 2020/6/30
 **/
val appContext: BaseApplication by lazy { BaseApplication.instance }

open class BaseApplication : Application(), ViewModelStoreOwner,
    Application.ActivityLifecycleCallbacks {

    companion object {
        lateinit var instance: BaseApplication
    }

    private lateinit var mAppViewModelStore: ViewModelStore

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        webViewSetPath(this)
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        //初始化Log打印
        XLog.init(BuildConfig.DEBUG)
        //初始化吐司
        ToastUtils.init(this)
        ToastUtils.setGravity(Gravity.BOTTOM, 0, R.dimen.dp_160.getDimension().px2dp())
        //下载
        FileDownloader.setup(this)
        //页面状态选择器
        initLoadSir()
        //注册全局的Activity生命周期管理
        registerActivityLifecycleCallbacks(this)
        mAppViewModelStore = ViewModelStore()
        //添加请求头拦截器
        NetRetrofit.okHttpClientBuilder.addInterceptor(HeadInterceptor())
    }

    /**
     * 全局通知
     */
    val configEvent: ConfigViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this)).get(
            ConfigViewModel::class.java
        )
    }

    /**
     * 初始化状态布局
     */
    private fun initLoadSir() {
        LoadSir.beginBuilder().run {
            loadStatusCallbackList.forEach {
                addCallback(it)
            }
            setDefaultCallback(defaultCallback()).commit()
        }
    }

    /**
     * 一定要放到初始化最上面
     * 解決WebView 在版本9.0的进程问题
     * @param context Context?
     */
    private fun webViewSetPath(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val processName = getProcessName(context)
            if (processName.equals("com.wanandroid.developer")) {
                WebView.setDataDirectorySuffix(processName)
            }
        }
    }

    /**
     * 获取进程名称
     * @param context Context?
     * @return String?
     */
    private fun getProcessName(context: Context?): String? {
        if (context == null) return null
        val manager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        if (manager.runningAppProcesses.isEmpty()) return null
        for (processInfo in manager.runningAppProcesses) {
            if (processInfo.pid == Process.myPid()) {
                return processInfo.processName
            }
        }
        return null
    }

    /**
     * 加载布局集合根据加载状态显示  根据业务可以重写函数
     */
    open val loadStatusCallbackList: MutableList<Callback> by lazy {
        mutableListOf(
            LoadStartCallback(),
            LoadEmptyCallback(),
            LoadErrorCallback(),
            LoadPutCallback()
        )
    }

    /**
     * 默认的加载布局 根据业务可以重写函数
     */
    open fun defaultCallback(): Class<out Callback> {
        return SuccessCallback::class.java
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        XLog.d(activity.javaClass.simpleName)
        addActivity(activity)
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        removeActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
    }
}