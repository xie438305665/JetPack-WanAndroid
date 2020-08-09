package com.wanandroid.module.home.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.BaseWebActivity
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.ext.CollectViewModel
import com.wanandroid.bridge.util.GsonUtils
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.WebViewEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/24
 **/
class HomeWebActivity : BaseWebActivity<Boolean, CollectViewModel>() {
    private var articleEntity: ArticleEntity? = null

    companion object {
        const val HOME_WEB_CODE = 0x001
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

        fun start(entity: WebViewEntity?, code: Int, activity: Activity) {
            activity.startActivityForResult(
                Intent(
                    appContext,
                    HomeWebActivity::class.java
                ).putExtras(Bundle().apply {
                    putParcelable(AnnotationValue.BUNDLE_KEY_WEB_VIEW, entity)
                }), code
            )
        }
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.collectVm.observe(this, this)
    }

    override fun initCreate(entity: WebViewEntity?) {
        articleEntity = GsonUtils.toClazz(entity?.data, ArticleEntity::class.java)
    }

    override fun netCollect() {
        articleEntity?.let {
            baseVm.onNetCollect(it.collect, it.chapterId)
        }
    }

    override fun shardArticle() {
        articleEntity?.let {
            val intentShareFile = Intent(Intent.ACTION_SEND)
            intentShareFile.putExtra(Intent.EXTRA_TEXT, it.projectLink)
            startActivityForResult(Intent.createChooser(intentShareFile, "分享链接"), HOME_WEB_CODE)
        }
    }

    override fun refreshView(data: Boolean?) {
        articleEntity?.collect = data ?: false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != HOME_WEB_CODE) return
    }
}