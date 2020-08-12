package com.wanandroid.bridge.annotation

import androidx.annotation.StringDef

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/8/11
 **/

@StringDef(
    EventBusKey.CONFIG
)
@Retention(AnnotationRetention.SOURCE)
annotation class EventBusKey {
    companion object {
        const val CONFIG = "config"
    }
}