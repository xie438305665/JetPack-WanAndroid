package com.wanandroid.module.home.ui

import android.os.Bundle
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseWebView
import com.wanandroid.bridge.ext.toStartActivity
import com.wanandroid.module.home.R
import com.wanandroid.module.home.model.HomeItemViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import kotlinx.android.synthetic.main.activity_item_webview.*

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/24
 **/
class HomeItemActivity : BaseActivity<ArticleEntity, HomeItemViewModel>(),
    BaseWebView.PageFinishedListener {

    companion object {
        fun start(entity: ArticleEntity, fragment: HomeFragment) {
            val bundle = Bundle().apply {
                putParcelable(AnnotationValue.BUNDLE_KEY_WEB_VIEW, entity)
            }
            fragment.toStartActivity(HomeItemActivity::class.java, HomeFragment.CODE, bundle)
        }
    }

    private var entity: ArticleEntity? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_item_webview
    }

    override fun initObserver() {
        super.initObserver()
    }

    override fun initCreate(bundle: Bundle?) {
        bundle ?: refreshLoadStatus()
        entity = bundle?.getParcelable(AnnotationValue.BUNDLE_KEY_WEB_VIEW)
        entity ?: refreshLoadStatus()
        mToolbar.setTitleText(entity?.title)
        itemWebView.registerFinishedListener(this)
        itemWebView.canChildScrollUp(itemSwipeRefreshLayout)
        itemWebView.openProgress()
        itemWebView.loadUrl(entity?.link)
    }

    override fun refreshView(data: ArticleEntity?) {

    }

    override fun onPageFinished() {

    }
}