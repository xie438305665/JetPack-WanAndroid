package com.zhixinhuixue.library.net.interception

import android.util.Log
import com.zhixinhuixue.library.net.interception.logging.DefaultFormatPrinter
import com.zhixinhuixue.library.net.interception.logging.FormatPrinter
import com.zhixinhuixue.library.net.interception.logging.util.CharacterHandler.Companion.jsonFormat
import com.zhixinhuixue.library.net.interception.logging.util.UrlEncoderUtils.Companion.hasUrlEncoded
import com.zhixinhuixue.library.net.interception.logging.util.ZipHelper.Companion.decompressForGzip
import com.zhixinhuixue.library.net.interception.logging.util.ZipHelper.Companion.decompressToStringForZlib
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *  @description:请求日志拦截器
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
class LogInterceptor : Interceptor {
    private val mPrinter: FormatPrinter = DefaultFormatPrinter()
    private val printLevel =
        Level.ALL

    constructor() {}
    constructor(printLevel: Level?) {}

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val logRequest =
            printLevel == Level.ALL || printLevel != Level.NONE && printLevel == Level.REQUEST
        if (logRequest) {
            //打印请求信息
            if (request.body() != null && isParsable(
                    request.body()!!.contentType()
                )
            ) {
                mPrinter.printJsonRequest(request, parseParams(request))
            } else {
                mPrinter.printFileRequest(request)
            }
        }
        val logResponse =
            printLevel == Level.ALL || printLevel != Level.NONE && printLevel == Level.RESPONSE
        val t1 = if (logResponse) System.nanoTime() else 0
        val originalResponse: Response
        originalResponse = try {
            chain.proceed(request)
        } catch (e: Exception) {
            Log.d("Http Error: %s", e.message ?: "")
            throw e
        }
        val t2 = if (logResponse) System.nanoTime() else 0
        val responseBody = originalResponse.body()

        //打印响应结果
        var bodyString: String? = null
        if (responseBody != null && isParsable(responseBody.contentType())) {
            bodyString = printResult(originalResponse)
        }
        if (logResponse) {
            val segmentList =
                request.url().encodedPathSegments()
            val header: String = if (originalResponse.networkResponse() == null) {
                originalResponse.headers().toString()
            } else {
                originalResponse.networkResponse()!!.request().headers().toString()
            }
            val code = originalResponse.code()
            val isSuccessful = originalResponse.isSuccessful
            val message = originalResponse.message()
            val url = originalResponse.request().url().toString()
            if (responseBody != null && isParsable(responseBody.contentType())) {
                mPrinter.printJsonResponse(
                    TimeUnit.NANOSECONDS.toMillis(t2 - t1), isSuccessful,
                    code, header, responseBody.contentType(), bodyString, segmentList, message, url
                )
            } else {
                mPrinter.printFileResponse(
                    TimeUnit.NANOSECONDS.toMillis(t2 - t1),
                    isSuccessful, code, header, segmentList, message, url
                )
            }
        }
        return originalResponse
    }

    /**
     * 打印响应结果
     *
     * @param response    [Response]
     * @return 解析后的响应结果
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun printResult(response: Response): String? {
        return try {
            //读取服务器返回的结果
            val responseBody = response.newBuilder().build().body()
            responseBody?.let {
                val source = it.source()
                source.request(Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source.buffer

                //获取content的压缩类型
                val encoding = response
                    .headers()["Content-Encoding"]
                val clone = buffer.clone()

                //解析response content
                parseContent(encoding, clone)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            "{\"error\": \"" + e.message + "\"}"
        }
    }

    /**
     * 解析服务器响应的内容
     *
     * @param encoding     编码类型
     * @param clone        克隆后的服务器响应内容
     * @return 解析后的响应结果
     */
    private fun parseContent(
        encoding: String?,
        clone: Buffer
    ): String? {
        //content 使用 gzip 压缩
        return when {
            "gzip".equals(encoding, ignoreCase = true) -> {
                //解压
                decompressForGzip(
                    clone.readByteArray(),
                    convertCharset(StandardCharsets.UTF_8)
                )
            }
            "zlib".equals(encoding, ignoreCase = true) -> {
                //content 使用 zlib 压缩
                decompressToStringForZlib(
                    clone.readByteArray(),
                    convertCharset(StandardCharsets.UTF_8)
                )
            }
            else -> {
                //content 没有被压缩, 或者使用其他未知压缩方式
                clone.readString(StandardCharsets.UTF_8)
            }
        }
    }

    enum class Level {
        /**
         * 不打印log
         */
        NONE,

        /**
         * 只打印请求信息
         */
        REQUEST,

        /**
         * 只打印响应信息
         */
        RESPONSE,

        /**
         * 所有数据全部打印
         */
        ALL
    }

    companion object {
        /**
         * 解析请求服务器的请求参数
         *
         * @param request [Request]
         * @return 解析后的请求信息
         * @throws UnsupportedEncodingException
         */
        @Throws(UnsupportedEncodingException::class)
        fun parseParams(request: Request): String {
            return try {
                val body = request.newBuilder().build().body() ?: return ""
                val requestBuffer = Buffer()
                body.writeTo(requestBuffer)
                var json = requestBuffer.readString(StandardCharsets.UTF_8) ?: ""
                if (hasUrlEncoded(json)) {
                    json = URLDecoder.decode(json, convertCharset(StandardCharsets.UTF_8))
                }
                jsonFormat(json)
            } catch (e: IOException) {
                e.printStackTrace()
                "{\"error\": \"" + e.message + "\"}"
            }
        }

        /**
         * 是否可以解析
         *
         * @param mediaType [MediaType]
         * @return `true` 为可以解析
         */
        private fun isParsable(mediaType: MediaType?): Boolean {
            return if (mediaType?.type() == null) {
                false
            } else isText(mediaType) || isPlain(
                mediaType
            )
                    || isJson(mediaType) || isForm(
                mediaType
            )
                    || isHtml(mediaType) || isXml(
                mediaType
            )
        }

        private fun isText(mediaType: MediaType?): Boolean {
            return if (mediaType?.type() == null) {
                false
            } else "text" == mediaType.type()
        }

        private fun isPlain(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype() == null) {
                false
            } else mediaType.subtype()
                .toLowerCase(Locale.ROOT).contains("plain")
        }

        @JvmStatic
        fun isJson(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype() == null) {
                false
            } else mediaType.subtype().toLowerCase(Locale.getDefault()).contains("json")
        }

        @JvmStatic
        fun isXml(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype() == null) {
                false
            } else mediaType.subtype().toLowerCase(Locale.getDefault()).contains("xml")
        }

        private fun isHtml(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype() == null) {
                false
            } else mediaType.subtype().toLowerCase(Locale.getDefault()).contains("html")
        }

        private fun isForm(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype() == null) {
                false
            } else mediaType.subtype().toLowerCase(Locale.getDefault())
                .contains("x-www-form-urlencoded")
        }

        fun convertCharset(charset: Charset?): String {
            val s = charset.toString()
            val i = s.indexOf("[")
            return if (i == -1) {
                s
            } else s.substring(i + 1, s.length - 1)
        }
    }
}