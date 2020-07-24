package com.wanandroid.bridge.annotation

import androidx.annotation.StringDef

/**
 * ARout路径
 */
@StringDef(
    ARouterPath.MAIN,
    ARouterPath.LOGIN
)
@Retention(AnnotationRetention.SOURCE)
annotation class ARouterPath {
    companion object {
        const val MAIN = "/app/main"
        const val LOGIN = "/user/login"
    }
}