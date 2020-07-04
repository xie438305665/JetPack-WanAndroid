package com.wanandroid.bridge

import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.ext.*
import com.zhixinhuixue.library.net.entity.ConfigEntity
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 *  @description:Head拦截器
 *  @author xcl qq:244672784
 *  @Date 2020/7/1
 **/
class HeadInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.apply {
            addHeader("Content-Type", "application/json;charset=utf-8")
            addHeader("Authorization", "")
            addHeader("Accept-Encoding", "")
            addHeader(
                "User-Agent", ConfigEntity(
                    getPhoneModel(),
                    getPhoneVersion(),
                    getPhoneBrand(),
                    getAppVersion(appContext),
                    getPackageNameName(appContext)
                ).toString()
            )
        }
        return chain.proceed(builder.build())
    }
}