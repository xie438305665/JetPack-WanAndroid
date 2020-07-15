package com.zhixinhuixue.library.net.error

import android.net.ParseException
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 *  @description:错误信息处理
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
class NetException {
    companion object {
        val instance by lazy {
            NetException()
        }
    }

    fun errorTransform(e: Throwable): ErrorBean {
        return when (e) {
            is HttpException -> ErrorBean(1002, "网络连接错误，请稍后重试")
            is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> ErrorBean(
                1001,
                "解析错误，请稍后再试"
            )
            is ConnectException -> ErrorBean(1002, "网络连接错误，请稍后重试")
            is javax.net.ssl.SSLException -> ErrorBean(1004, "证书出错，请稍后再试")
            is ConnectTimeoutException -> ErrorBean(1006, "网络连接超时，请稍后重试")
            is java.net.SocketTimeoutException -> ErrorBean(1006, "网络连接超时，请稍后重试")
            is java.net.UnknownHostException -> ErrorBean(1006, "网络连接超时，请稍后重试")
            else -> ErrorBean(1000, "请求失败，请稍后再试")
        }
    }

    class ErrorBean(val code: Int, val errorMsg: String?) {

    }
}