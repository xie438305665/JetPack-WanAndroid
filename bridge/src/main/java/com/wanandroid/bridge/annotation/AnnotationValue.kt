package com.wanandroid.bridge.annotation

import androidx.annotation.StringDef

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/24
 **/
@StringDef(
    AnnotationValue.SP_NAME,
    AnnotationValue.SP_KEY_USER_NAME,
    AnnotationValue.SP_KEY_PASSWORD,
    AnnotationValue.SP_KEY_PASSWORD_CHECKED,
    AnnotationValue.SP_KEY_USER_INFO,
    AnnotationValue.BUNDLE_KEY_WEB_VIEW,
    AnnotationValue.BUNDLE_KEY_ITEM
)
@Retention(AnnotationRetention.SOURCE)
annotation class AnnotationValue {
    companion object {
        const val SP_NAME = "JetPack-WanAndroid"

        const val SP_KEY_USER_NAME = "UserName"

        const val SP_KEY_PASSWORD = "Password"

        const val SP_KEY_PASSWORD_CHECKED = "PasswordIsChecked"

        const val SP_KEY_USER_INFO = "UserInfo"

        const val BUNDLE_KEY_WEB_VIEW = "webView"

        const val BUNDLE_KEY_SEARCH = "search"

        const val BUNDLE_KEY_ITEM = "item"
    }
}