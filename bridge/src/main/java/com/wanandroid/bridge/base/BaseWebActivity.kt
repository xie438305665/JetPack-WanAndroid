package com.wanandroid.bridge.base

import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.ext.clickNoRepeat
import com.wanandroid.bridge.ext.getVmClazz
import com.wanandroid.developer.library.bridge.R
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.WebViewEntity
import com.zhixinhuixue.library.widget.custom.CustomWebView
import kotlinx.android.synthetic.main.activity_base_webview.*

/**
 *  @description:WebView
 *  @author xcl qq:244672784
 *  @date 2020/7/24
 **/
abstract class BaseWebActivity< VM : BaseViewModel> : BaseActivity<WebViewEntity, VM>(),
    CustomWebView.PageFinishedListener {

    private var entity: WebViewEntity? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_base_webview
    }

    override fun initCreate(bundle: Bundle?) {
        if (bundle == null) {
            onChangeUi(NetViewModel.LoadStatus.EMPTY)
            return
        }
        entity = bundle.getParcelable(AnnotationValue.BUNDLE_KEY_WEB_VIEW)
        if (entity == null) {
            onChangeUi(NetViewModel.LoadStatus.EMPTY)
            throw NullPointerException("请检查 Bundle 传值是否存在误差 请仔细查看一下BaseWebActivity类 {Bundle key必须是 webView  传输得数据 必须是WebViewEntity}")
        }
        mToolbar.setTitleText(entity?.title)
        baseWebView.run {
            baseWebView.registerFinishedListener(this@BaseWebActivity)
            baseWebView.openProgress()
            loadUrl(entity)
            baseWebView.webViewClient = WebClient()
        }
        baseSwipeRefreshLayout.setOnRefreshListener {
            baseWebView.openProgress()
            loadUrl(entity)
        }
        canChildScrollUp(baseSwipeRefreshLayout)
        baseFloatBtn.clickNoRepeat {
            onViewClick(it)
        }
        initCreate(entity)
    }

    abstract fun initCreate(entity: WebViewEntity?)

    override fun refreshView(data: WebViewEntity?) {

    }

    open fun loadUrl(entity: WebViewEntity?) {
        baseWebView.loadUrl(entity?.url)
    }

    open fun onViewClick(view: View) {

    }

    override fun onPageFinished() {
        onChangeUi(NetViewModel.LoadStatus.SUCCESS)
    }

    private fun onChangeUi(@NetViewModel.LoadStatus status: Int) {
        if (baseSwipeRefreshLayout.isRefreshing) {
            baseSwipeRefreshLayout.isRefreshing = false
        } else {
            refreshLoadStatus(status)
        }
    }

    inner class WebClient : WebViewClient() {
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

    /**
     * 解决SwipeRefreshLayout 滑动冲突
     * @param swipeRefreshLayout SwipeRefreshLayout?
     */
    private fun canChildScrollUp(swipeRefreshLayout: SwipeRefreshLayout?) {
        swipeRefreshLayout ?: return
        swipeRefreshLayout.setOnChildScrollUpCallback(SwipeRefreshLayout.OnChildScrollUpCallback { _, _ -> baseWebView.scaleY > 0 })
    }

    override fun initViewMode(): VM {
        //JVM如果是1.6 使用
        baseVm = ViewModelProvider(viewModelStore, createFactory()).get(getVmClazz(this,0))
        return baseVm
    }

    override fun onDestroy() {
        baseWebView.reset()
        super.onDestroy()
    }
}