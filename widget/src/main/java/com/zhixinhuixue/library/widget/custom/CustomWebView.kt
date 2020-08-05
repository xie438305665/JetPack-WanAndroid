package com.zhixinhuixue.library.widget.custom

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.AttributeSet
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ProgressBar

/**
 *  @description:WebView
 *  @author xcl qq:244672784
 *  @date 2020/7/23
 **/
class CustomWebView : WebView {
    private var progressbar: ProgressBar? = null
    private var listener: PageFinishedListener? = null

    constructor(context: Context) : super(getFixedContext(context)) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(
        getFixedContext(context),
        attributeSet
    ) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        getFixedContext(context),
        attributeSet,
        defStyleAttr
    ) {
        init()
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        getFixedContext(context),
        attributeSet,
        defStyleAttr,
        defStyleRes
    ) {
        init()
    }

    companion object {
        /**
         * 解决WebView 升级AndroidX 出现白屏问题
         * @param context Context
         * @return Context
         */
        fun getFixedContext(context: Context): Context {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                context.createConfigurationContext(Configuration())
            } else context
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        settings.run {
            settings.javaScriptEnabled = true
            settings.builtInZoomControls = false
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
            settings.savePassword = false
            settings.domStorageEnabled = true
            settings.setAppCacheEnabled(false)
            settings.defaultFontSize = (14.0f / context.resources.displayMetrics.scaledDensity).toInt()
            setOnLongClickListener { true }
        }
    }

    fun openProgress() {
        progressbar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        progressbar?.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            0,
            0
        )
        addView(progressbar)
        webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                newProgressBar(newProgress)
            }
        }
    }

    private fun newProgressBar(newProgress: Int) {
        progressbar?.let {
            if (newProgress == 100) {
                it.visibility = GONE
                listener?.onPageFinished()
                listener = null
            } else {
                if (it.visibility == GONE) {
                    it.visibility = VISIBLE
                }
                it.progress = newProgress
            }
        }
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        progressbar?.let {
            val lp = it.layoutParams as LayoutParams
            lp.x = l
            lp.y = t
            it.layoutParams = lp
        }
        super.onScrollChanged(l, t, oldl, oldt)
    }

    /**
     * 加载html
     * @param url
     */
    fun loadDataUrl(url: String?) {
        loadDataWithBaseURL(null, url, "text/html", "utf-8", null)
    }

    /**
     * 与JS交互
     * @param params Any 传参
     * @param funName String  交互定义的方法名
     */
    fun javaScriptData(params: Any, funName: String) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            loadUrl("javascript:$funName($params)")
        } else {
            post {
                evaluateJavascript(
                    "javascript:$funName($params)"
                ) { }
            }
        }
    }

//    /**
//     * 解决SwipeRefreshLayout 滑动冲突
//     * @param swipeRefreshLayout SwipeRefreshLayout?
//     */
//    fun canChildScrollUp(swipeRefreshLayout: SwipeRefreshLayout?) {
//        swipeRefreshLayout ?: return
//        swipeRefreshLayout.setOnChildScrollUpCallback(SwipeRefreshLayout.OnChildScrollUpCallback { _, _ -> scrollY > 0 })
//    }

    /**
     * WebView加载完成回调监听
     * @param listener PageFinishedListener
     */
    fun registerFinishedListener(listener: PageFinishedListener) {
        this.listener = listener
    }

    interface PageFinishedListener {
        fun onPageFinished()
    }

    /**
     * 销毁WebView
     */
    fun reset() {
        loadDataUrl("")
        clearHistory()
        removeAllViews()
        (parent as ViewGroup).removeView(this)
        destroy()
    }
}