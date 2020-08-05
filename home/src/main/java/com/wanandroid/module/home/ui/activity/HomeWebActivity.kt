package com.wanandroid.module.home.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.BaseWebActivity
import com.wanandroid.bridge.base.appContext
import com.wanandroid.module.home.model.HomeItemViewModel
import com.zhixinhuixue.library.net.entity.WebViewEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/24
 **/
class HomeWebActivity : BaseWebActivity<HomeItemViewModel>() {
    companion object {
        fun start(entity: WebViewEntity?, code: Int, fragment: Fragment) {
            fragment.startActivityForResult(
                Intent(
                    appContext,
                    HomeWebActivity::class.java
                ).putExtras(Bundle().apply {
                    putParcelable(AnnotationValue.BUNDLE_KEY_WEB_VIEW, entity)
                }), code
            )
        }
    }

    override fun initCreate(entity: WebViewEntity?) {

    }
}