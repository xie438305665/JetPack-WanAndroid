package com.wanandroid.bridge.annotation

import androidx.annotation.StringDef

/**
 * ARout路径
 */
@StringDef(
    ARouterPath.MAIN,
    ARouterPath.LOGIN,
    ARouterPath.HOT_SEARCH
)
@Retention(AnnotationRetention.SOURCE)
annotation class ARouterPath {
    companion object {
        const val MAIN = "/app/main"
        const val LOGIN = "/user/login"
        const val HOT_SEARCH = "/home/search"
    }
}