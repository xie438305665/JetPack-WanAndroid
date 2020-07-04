package com.zhixinhuixue.library.net.entity

import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import com.zhixinhuixue.library.net.NetConstant
import com.zhixinhuixue.library.net.NetResponse

data class NetBaseEntity<T>(
    var data: T,
    @SerializedName("errCode")
    var code: String = NetConstant.ERROR_CODE,
    @SerializedName("errMsg")
    var message: String = "",
    @SerializedName("success")
    var success: Int,
    var msg: String = "",
    var status: Int = 0
) : NetResponse<T>() {

    override fun isSuccess(): Boolean =
        TextUtils.equals(NetConstant.SUCCESS_CODE, code) || success == 1

    override fun getResponseData(): T = data

    override fun getResponseCode(): String = code

    override fun getResponseMsg(): String = message

}