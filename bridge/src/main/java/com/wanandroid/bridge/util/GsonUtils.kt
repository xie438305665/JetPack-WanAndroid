package com.wanandroid.bridge.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 *  @description:Gson工具类
 *  @author xcl qq:244672784
 *  @Date 2020/7/1
 **/
object GsonUtils {
    private val gsonBuilder: GsonBuilder by lazy { GsonBuilder() }
    val gson: Gson by lazy { gsonBuilder.create() }
    inline fun <reified T> toClazz(paramString: String?, paramClass: Class<T>?): T? {
        return try {
            gson.fromJson(paramString, paramClass)
        } catch (localException: Exception) {
            null
        }
    }

    inline fun <reified T> toList(
        paramString: String?,
        paramTypeToken: TypeToken<List<T>?>
    ): List<*>? {
        return try {
            gson.fromJson(
                paramString,
                paramTypeToken.type
            )
        } catch (localException: Exception) {
            null
        }
    }

    inline fun <reified K, reified V> toMap(
        paramString: String?,
        paramTypeToken: TypeToken<Map<K, V>?>
    ): Map<K, V>? {
        return gson.fromJson(
            paramString,
            paramTypeToken.type
        )
    }

    fun toJson(any: Any): String = gson.toJson(any)
}