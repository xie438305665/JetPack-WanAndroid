package com.wanandroid.bridge.base

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.ext.bindViewClick
import com.wanandroid.bridge.ext.goneViews
import com.wanandroid.bridge.ext.logD
import com.wanandroid.bridge.ext.visibleViews
import com.wanandroid.bridge.util.AnimationUtils
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
abstract class BaseWebActivity<T, VM : BaseViewModel> : BaseActivity<T, VM>(),
    CustomWebView.PageFinishedListener, Animator.AnimatorListener,
    ValueAnimator.AnimatorUpdateListener {

    lateinit var floatBtn: FloatingActionButton
    lateinit var collectBtn: FloatingActionButton
    lateinit var shareBtn: FloatingActionButton
    private var entity: WebViewEntity? = null
    private var isClick: Boolean = false
    override fun getLayoutId(): Int {
        return R.layout.activity_base_webview
    }

    override fun initCreate(bundle: Bundle?) {
        if (bundle == null) {
            onChangeUi(NetViewModel.LoadStatus.EMPTY)
            return
        }
        floatBtn = baseFloatBtn
        collectBtn = collectFloatBtn
        shareBtn = shareFloatBtn
        entity = bundle.getParcelable(AnnotationValue.BUNDLE_KEY_WEB_VIEW)
        if (entity == null) {
            onChangeUi(NetViewModel.LoadStatus.EMPTY)
            throw NullPointerException("请检查 Bundle 传值是否存在误差 请仔细查看一下BaseWebActivity类 {Bundle key必须是 webView  传输得数据 必须是WebViewEntity}")
        }
        mToolbar.setTitleText(entity?.title)
        baseWebView.run {
            registerFinishedListener(this@BaseWebActivity)
            openProgress()
            webViewClient = WebClient()
            loadUrl(entity)
        }
        baseSwipeRefreshLayout.setOnRefreshListener {
            baseWebView.openProgress()
            loadUrl(entity)
        }
        canChildScrollUp(baseSwipeRefreshLayout)
        AnimationUtils.bindListener(
            values = *intArrayOf(40, 200),
            listener = this,
            updateListener = this
        )
        onBindViewClick()
        initCreate(entity)
    }

    abstract fun initCreate(entity: WebViewEntity?)

    override fun refreshView(data: T?) {

    }

    open fun loadUrl(entity: WebViewEntity?) {
        baseWebView.loadUrl(entity?.url)
    }

    open fun onBindViewClick() {
        bindViewClick(floatBtn, collectBtn, shareBtn) {
            when (it.id) {
                R.id.baseFloatBtn -> {
                    if (!isClick) {
                        visibleViews(collectBtn, shareBtn)
                    }
                    AnimationUtils.start()
                }
                R.id.collectFloatBtn -> netCollect()
                R.id.shareFloatBtn -> "shareFloatBtn".logD()
            }
        }
    }

    open fun netCollect() {}

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
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view ?: return true
            request ?: return true
            val url = request.url.toString()
            if (url.startsWith("http://") || url.startsWith("https://")) {
                view.loadUrl(url)
                return false
            }
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
            )
            return true
        }

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
        swipeRefreshLayout.setOnChildScrollUpCallback(SwipeRefreshLayout.OnChildScrollUpCallback { _, _ -> baseWebView != null && baseWebView.scrollY > 0; })
    }

    override fun onFinishClick() {
        setResult(Activity.RESULT_OK)
        super.onFinishClick()
    }

    override fun onBackPressed() {
        onFinishClick()
    }

    override fun onAnimationCancel(p0: Animator?) {
    }

    override fun onAnimationEnd(p0: Animator?) {
    }

    override fun onAnimationRepeat(p0: Animator?) {
    }

    override fun onAnimationStart(p0: Animator?) {
    }

    override fun onAnimationUpdate(animator: ValueAnimator?) {
        animator ?: return
        val value = (animator.animatedValue) as Int
        collectBtn.let {
            it.alpha = if (isClick) it.alpha - 0.1f else it.alpha + 0.1f
            val collectParams = it.layoutParams as FrameLayout.LayoutParams
            collectParams.bottomMargin = if (isClick) 240 - value else value
            it.layoutParams = collectParams
        }
        shareBtn.let {
            it.alpha = if (isClick) it.alpha - 0.1f else it.alpha + 0.1f
            val shareParams = it.layoutParams as FrameLayout.LayoutParams
            shareParams.marginEnd = if (isClick) 240 - value else value
            it.layoutParams = shareParams
        }
        //更新状态
        if (value == 200) {
            if (isClick)
                goneViews(collectBtn, shareBtn) else visibleViews(collectBtn, shareBtn)
            isClick = !isClick
        }
    }

    override fun onDestroy() {
        baseWebView.reset()
        AnimationUtils.cancel()
        super.onDestroy()
    }
}