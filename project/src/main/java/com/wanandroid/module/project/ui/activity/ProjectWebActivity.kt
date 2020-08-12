package com.wanandroid.module.project.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.BaseWebActivity
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.ext.CollectViewModel
import com.wanandroid.bridge.ext.formHtml
import com.wanandroid.bridge.ext.getColor
import com.wanandroid.bridge.ext.sendStartActivity
import com.wanandroid.bridge.util.GsonUtils
import com.wanandroid.module.project.R
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.WebViewEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/8/5
 **/
class ProjectWebActivity : BaseWebActivity<Boolean, CollectViewModel>() {
    private var articleEntity: ArticleEntity? = null
    private var isCollect: Boolean = false

    companion object {
        const val PROJECT_WEB_CODE = 0x002
        fun start(entity: WebViewEntity?, code: Int, fragment: Fragment) {
            fragment.startActivityForResult(
                Intent(
                    appContext,
                    ProjectWebActivity::class.java
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
            sendStartActivity(it.title.formHtml().toString(), it.projectLink, PROJECT_WEB_CODE)
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
        if (requestCode != PROJECT_WEB_CODE) return
    }
}