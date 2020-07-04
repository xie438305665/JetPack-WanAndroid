package com.zhixinhuixue.library.net.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author : hgj
 * @date   : 2020/6/28
 *
 */
interface HomeApi {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("basic/mte/login")
    suspend fun login1(@Field("username") username: String, @Field("password") pwd: String): Any
}