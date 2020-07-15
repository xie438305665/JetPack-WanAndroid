package com.wanandroid.bridge.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.hjq.toast.ToastUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.liulishuo.filedownloader.FileDownloader
import com.wanandroid.bridge.HeadInterceptor
import com.wanandroid.bridge.ext.addActivity
import com.wanandroid.bridge.ext.getDimension
import com.wanandroid.bridge.ext.px2dp
import com.wanandroid.bridge.ext.removeActivity
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
        //初始化Log打印
        XLog.init(BuildConfig.DEBUG)
        //初始化吐司
        ToastUtils.init(this)
        ToastUtils.setGravity(Gravity.BOTTOM, 0, R.dimen.dp_160.getDimension().px2dp())
        //下载
        FileDownloader.setup(this)
        //LiveEventBus
        liveEventBusConfig()
        //页面状态选择器
        initLoadSir()
        //注册全局的Activity生命周期管理
        registerActivityLifecycleCallbacks(this)
        mAppViewModelStore = ViewModelStore()
        //添加请求头拦截器
        NetRetrofit.okHttpClientBuilder.addInterceptor(HeadInterceptor())
    }

    /**
     * 初始化状态布局
     */
    private fun initLoadSir() {
        val builder = LoadSir.beginBuilder()
        loadStatusCallbackList.forEach {
            builder.addCallback(it)
        }
        builder.setDefaultCallback(defaultCallback()).commit()
    }

    /**
     * 加载布局集合根据加载状态显示  根据业务可以重写函数
     */
    open val loadStatusCallbackList: List<BaseLoadCallback> by lazy {
        listOf(
            BaseLoadCallback(R.layout.layout_load_start),
            BaseLoadCallback(R.layout.layout_load_empty),
            BaseLoadCallback(R.layout.layout_load_error)
        )
    }

    /**
     * 默认的加载布局 根据业务可以重写函数
     */
    open fun defaultCallback(): Class<out Callback> {
        return SuccessCallback::class.java
    }

    /**
     * LiveEventBus配置  根据业务可以重写函数
     */
    open fun liveEventBusConfig() {
        LiveEventBus.config()
            .autoClear(true) //在没有Observer关联的时候自动清除LiveEvent以释放内存
            .lifecycleObserverAlwaysActive(false)//激活状态（Started）可以实时收到消息，非激活状态（Stoped）无法实时收到消息
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