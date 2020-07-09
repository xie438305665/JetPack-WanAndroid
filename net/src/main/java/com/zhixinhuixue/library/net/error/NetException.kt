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
object NetException {
    fun errorTransform(e: Throwable): ErrorEnum {
        return when (e) {
            is HttpException -> ErrorEnum.NETWORK_ERROR
            is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> ErrorEnum.PARSE_ERROR
            is ConnectException -> ErrorEnum.NETWORK_ERROR
            is javax.net.ssl.SSLException -> ErrorEnum.SSL_ERROR
            is ConnectTimeoutException -> ErrorEnum.TIMEOUT_ERROR
            is java.net.SocketTimeoutException -> ErrorEnum.TIMEOUT_ERROR
            is java.net.UnknownHostException -> ErrorEnum.TIMEOUT_ERROR
            else -> ErrorEnum.UNKNOWN
        }
    }

    enum class ErrorEnum(private val code: Int, private val err: String) {
        /**
         * 未知错误
         */
        UNKNOWN(1000, "请求失败，请稍后再试"),

        /**
         * 解析错误
         */
        PARSE_ERROR(1001, "解析错误，请稍后再试"),

        /**
         * 网络错误
         */
        NETWORK_ERROR(1002, "网络连接错误，请稍后重试"),

        /**
         * 证书出错
         */
        SSL_ERROR(1004, "证书出错，请稍后再试"),

        /**
         * 连接超时
         */
        TIMEOUT_ERROR(1006, "网络连接超时，请稍后重试");

        fun getValue(): String {
            return err
        }

        fun getKey(): Int {
            return code
        }
    }
}