package com.wanandroid.module.wx_article.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.BaseWebActivity
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.ext.CollectViewModel
import com.wanandroid.bridge.ext.getColor
import com.wanandroid.bridge.util.GsonUtils
import com.wanandroid.module.wx_article.R
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.WebViewEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/24
 **/
class WxArticleWebActivity : BaseWebActivity<Boolean, CollectViewModel>() {
    private var articleEntity: ArticleEntity? = null
    private var isCollect: Boolean = false

    companion object {
        const val WX_WEB_CODE = 0x004
        fun start(entity: WebViewEntity?, code: Int, fragment: Fragment) {
            fragment.startActivityForResult(
                Intent(
                    appContext,
                    WxArticleWebActivity::class.java
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
        articleEntity?.let {
            isCollect = it.collect
            collectBtn.drawable.setTint(if (it.collect) R.color.colorRed.getColor() else android.R.color.white.getColor())
        }
    }

    override fun netCollect() {
        articleEntity?.let {
            baseVm.onNetCollect(it.collect, it.id)
        }
    }

    override fun shardArticle() {
        articleEntity?.let {
            val intentShareFile = Intent(Intent.ACTION_SEND)
            intentShareFile.putExtra(Intent.EXTRA_TEXT, it.projectLink)
            startActivityForResult(Intent.createChooser(intentShareFile, "分享链接"), WX_WEB_CODE)
        }
    }

    override fun refreshView(data: Boolean?) {
        articleEntity?.let {
            it.collect = data ?: false
            isCollect = it.collect
            collectBtn.drawable.setTint(if (it.collect) R.color.colorRed.getColor() else android.R.color.white.getColor())
        }
    }

    override fun finish() {
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtras(Bundle().apply {
                putBoolean(AnnotationValue.BUNDLE_KEY_COLLECT, isCollect)
            })
        })
        super.finish()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != WX_WEB_CODE) return
    }
}