package com.wanandroid.developer

import android.content.Context
import com.tencent.bugly.crashreport.CrashReport
import com.wanandroid.bridge.base.BaseApplication

class App : BaseApplication() {
    override fun onCreate() {
        CrashReport.initCrashReport(this, "aa0684480f", BuildConfig.DEBUG)
        super.onCreate()
    }
}