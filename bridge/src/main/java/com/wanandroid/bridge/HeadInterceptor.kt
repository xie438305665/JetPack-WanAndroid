package com.wanandroid.bridge

import com.wanandroid.bridge.BridgeConstant.SP_KEY_PASSWORD
import com.wanandroid.bridge.BridgeConstant.SP_KEY_USER_NAME
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.ext.*
import com.wanandroid.bridge.util.SpUtils
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
            addHeader("Accept-Encoding", "")
            addHeader(
                "Cookie",
                "loginUserName=${SpUtils.getValue<String>(SP_KEY_USER_NAME, "")}"
            )
            addHeader(
                "Cookie",
                "loginUserPassword=${SpUtils.getValue<String>(SP_KEY_PASSWORD, "")}"
            )
        }
        return chain.proceed(builder.build())
    }
}