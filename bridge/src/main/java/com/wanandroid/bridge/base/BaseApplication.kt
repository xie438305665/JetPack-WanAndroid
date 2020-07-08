package com.wanandroid.bridge.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.hjq.toast.ToastUtils
import com.liulishuo.filedownloader.FileDownloader
import com.wanandroid.bridge.HeadInterceptor
import com.wanandroid.bridge.ext.addActivity
import com.wanandroid.bridge.ext.getDimensionExt
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

val appContext: Context by lazy { BaseApplication.instance }

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
        ToastUtils.setGravity(Gravity.BOTTOM, 0, px2dp(getDimensionExt(R.dimen.dp_100)))
        mAppViewModelStore = ViewModelStore()
        //添加请求头拦截器
        NetRetrofit.okHttpClientBuilder.addInterceptor(HeadInterceptor())
        //注册全局的Activity生命周期管理
        registerActivityLifecycleCallbacks(this)
        //下载
        FileDownloader.setup(this)
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