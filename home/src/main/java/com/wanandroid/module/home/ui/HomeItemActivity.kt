package com.wanandroid.module.home.ui

import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseWebView
import com.wanandroid.bridge.ext.clickNoRepeat
import com.wanandroid.bridge.ext.toStartActivity
import com.wanandroid.module.home.R
import com.wanandroid.module.home.model.HomeItemViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleEntity
import kotlinx.android.synthetic.main.home_activity_item_webview.*

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
        return R.layout.home_activity_item_webview
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
        itemWebView.webViewClient = HomeItemWebClient()
        itemSwipeRefreshLayout.setOnRefreshListener {
            itemWebView.openProgress()
            itemWebView.loadUrl(entity?.link)
        }
        onViewClick()
    }

    override fun refreshView(data: ArticleEntity?) {

    }

    private fun onViewClick(){
        itemFloatBtn.clickNoRepeat {

        }
    }

    override fun onPageFinished() {
        onChangeUi(NetViewModel.LoadStatus.SUCCESS)
    }

    private fun onChangeUi(@NetViewModel.LoadStatus status: Int) {
        if (itemSwipeRefreshLayout.isRefreshing) {
            itemSwipeRefreshLayout.isRefreshing = false
        } else {
            refreshLoadStatus(status)
        }
    }

    inner class HomeItemWebClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            onChangeUi(NetViewModel.LoadStatus.SUCCESS)
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            onChangeUi(NetViewModel.LoadStatus.ERROR)
        }

        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            onChangeUi(NetViewModel.LoadStatus.ERROR)
        }
    }
}