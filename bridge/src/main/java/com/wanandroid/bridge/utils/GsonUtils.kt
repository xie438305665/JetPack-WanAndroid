package com.wanandroid.bridge.utils

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

    inline fun <reified T> arrayToValue(paramArrayOfT: Array<T>?): String =
        toJson(paramArrayOfT)

    inline fun <reified T> listToValue(paramList: List<T>?): String =
        toJson(paramList)

    inline fun <reified K, reified V> mapToValue(paramMap: Map<K, V>?): String =
        toJson(paramMap)

    fun toJson(paramObject: Any?): String = gson.toJson(paramObject)
}