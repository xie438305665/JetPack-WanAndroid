package com.wanandroid.bridge

import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.util.SpUtils
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
                "loginUserName=${SpUtils.getValue<String>(AnnotationValue.SP_KEY_USER_NAME, "")}"
            )
            addHeader(
                "Cookie",
                "loginUserPassword=${SpUtils.getValue<String>(AnnotationValue.SP_KEY_PASSWORD, "")}"
            )
        }
        return chain.proceed(builder.build())
    }
}