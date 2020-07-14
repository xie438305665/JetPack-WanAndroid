package com.zhixinhuixue.library.net

import com.zhixinhuixue.library.net.api.NetUrl
import com.zhixinhuixue.library.net.interception.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  @description:Retrofit
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
object NetRetrofit {
    /**
     * Service
     */
    inline fun <reified T> createService(serviceClass: Class<T>): T  {
        return retrofitBuilder.build().create(serviceClass)
    }

    /**
     * Service
     */
    inline fun <reified T> createService(serviceClass: Class<T>, baseUrl: String): T {
        retrofitBuilder.baseUrl(baseUrl)
        return retrofitBuilder.build().create(serviceClass)
    }

    /**
     * Retrofit.Builder
     */
    val retrofitBuilder by lazy {
        createRetrofitBuilder()
    }

    /**
     * OkHttpClient
     */
    val okHttpClient by lazy { okHttpClientBuilder.build() }

    /**
     * OkHttpClient.Builder
     */
    val okHttpClientBuilder by lazy { createHttpClientBuilder(OkHttpClient.Builder()) }

    /**
     * 创建RetrofitBuilder
     */
    private fun createRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().apply {
            baseUrl(NetUrl.BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }
    }

    /**
     * 创建HttpClientBuilder
     */
    private fun createHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return builder.apply {
            addInterceptor(LogInterceptor())
            //超时时间 连接、读、写
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
    }

    /**
     * 设置ConverterFactory
     */
    fun setConverterFactory(factory: GsonConverterFactory?): NetRetrofit {
        retrofitBuilder.apply {
            factory?.let {
                addConverterFactory(factory)
            }
        }
        return this
    }

    /**
     * 设置CallAdapter.Factory
     */
    fun setCallAdapterFactory(factory: CallAdapter.Factory?): NetRetrofit {
        retrofitBuilder.apply {
            factory?.let {
                addCallAdapterFactory(factory)
            }
        }
        return this
    }
}



