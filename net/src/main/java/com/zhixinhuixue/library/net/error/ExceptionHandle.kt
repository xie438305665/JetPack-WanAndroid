package com.zhixinhuixue.library.net.error

import android.net.ParseException
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/17
 * 描述　: 根据异常返回相关的错误信息工具类
 */
object ExceptionHandle {

    fun handleException(e: Throwable?): NetException {
        val ex: NetException
        e?.let {
            when (it) {
                is HttpException -> {
                    ex = NetException(
                        ErrorType.NETWORK_ERROR,
                        e
                    )
                    return ex
                }
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    ex = NetException(
                        ErrorType.PARSE_ERROR,
                        e
                    )
                    return ex
                }
                is ConnectException -> {
                    ex = NetException(
                        ErrorType.NETWORK_ERROR,
                        e
                    )
                    return ex
                }
                is javax.net.ssl.SSLException -> {
                    ex = NetException(
                        ErrorType.SSL_ERROR,
                        e
                    )
                    return ex
                }
                is ConnectTimeoutException -> {
                    ex = NetException(
                        ErrorType.TIMEOUT_ERROR,
                        e
                    )
                    return ex
                }
                is java.net.SocketTimeoutException -> {
                    ex = NetException(
                        ErrorType.TIMEOUT_ERROR,
                        e
                    )
                    return ex
                }
                is java.net.UnknownHostException -> {
                    ex = NetException(
                        ErrorType.TIMEOUT_ERROR,
                        e
                    )
                    return ex
                }
                is NetException -> return it
                else -> {
                    ex = NetException(
                        ErrorType.UNKNOWN,
                        e
                    )
                    return ex
                }
            }
        }
        ex = NetException(ErrorType.UNKNOWN, e)
        return ex
    }
}