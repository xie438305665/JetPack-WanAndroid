package com.zhixinhuixue.library.net.api

import com.zhixinhuixue.library.net.entity.NetBaseEntity
import com.zhixinhuixue.library.net.entity.UpgradeEntity
import com.zhixinhuixue.library.net.entity.UpgradeInfoEntity
import com.zhixinhuixue.library.net.entity.UserEntity
import okhttp3.FormBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author : hgj
 * @date   : 2020/6/28
 *
 */
interface UserApi {
    /**
     * 获取token
     */
    @POST("business/teacher/login")
    suspend fun getToken(@Body map: Map<String, String>): NetBaseEntity<String>

    /**
     * 获取用户信息
     */
    @GET("base/teacher/info")
    suspend fun getUserInfo(): NetBaseEntity<UserEntity>

    /**
     * Apk版本检查
     */
    @POST(NetUrl.APK_UPGRADE)
    suspend fun checkApkUpgrade(@Body body: FormBody): UpgradeEntity<UpgradeInfoEntity>
}