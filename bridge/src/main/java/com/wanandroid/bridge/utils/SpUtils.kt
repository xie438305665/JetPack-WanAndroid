package com.wanandroid.bridge.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull
import androidx.core.content.edit
import com.wanandroid.bridge.BridgeConstant
import com.wanandroid.bridge.base.appContext

/**
 *  @description:SP工具类
 *  @author xcl qq:244672784
 *  @Date 2020/6/30
 **/
object SpUtils {
    val sharedPreferences: SharedPreferences by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        appContext.getSharedPreferences(BridgeConstant.SP_NAME, Context.MODE_PRIVATE)
    }

    /**
     * 设置Value
     */
    fun setValue(@NonNull key: String, defaultValue: Any) {
        when (defaultValue) {
            is Long -> applyValue {
                putLong(
                    key,
                    defaultValue
                )
            }
            is Int -> applyValue {
                putInt(
                    key,
                    defaultValue
                )
            }
            is Float -> applyValue {
                putFloat(
                    key,
                    defaultValue
                )
            }
            is String -> applyValue {
                putString(
                    key,
                    defaultValue
                )
            }
            is Boolean -> applyValue {
                putBoolean(
                    key,
                    defaultValue
                )
            }
        }
    }

    /**
     * 获取Value
     */
    inline fun <reified T> getValue(@NonNull key: String, defaultValue: Any): T {
        return when (defaultValue) {
            is Long -> sharedPreferences.getLong(key, defaultValue)
            is Int -> sharedPreferences.getInt(key, defaultValue)
            is Float -> sharedPreferences.getFloat(key, defaultValue)
            is String -> sharedPreferences.getString(key, defaultValue)
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue)
            else -> defaultValue
        } as T
    }

    /**
     * 扩展函数
     */
    private fun applyValue(block: SharedPreferences.Editor.() -> Unit) {
        sharedPreferences.edit {
            this.block()
            this.apply()
        }
    }

    /**
     * 根据Key 删除
     */
    fun remove(@NonNull key: String) {
        applyValue { remove(key) }
    }

    /**
     * 全部删除
     */
    fun clear() {
        applyValue { clear() }
    }
}