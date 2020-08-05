package com.wanandroid.module.user.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.clickNoRepeat
import com.wanandroid.bridge.ext.getDiskCacheSize
import com.wanandroid.module.user.BuildConfig
import com.wanandroid.module.user.R
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import kotlinx.android.synthetic.main.user_activity_about.*

/**
 *  @description:关于APP
 *  @author xcl qq:244672784
 *  @Date 2020/8/2
 **/
class AboutActivity : BaseActivity<Any, BaseViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.user_activity_about
    }

    @SuppressLint("SetTextI18n")
    override fun initCreate(bundle: Bundle?) {
        tvVersion.text = "V${BuildConfig.VERSION_NAME}"
        tvRemoveCache.text = "缓存大小:${this.getDiskCacheSize()}(点击清除)"
        tvRemoveCache.clickNoRepeat {

        }
    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setTitleText(R.string.user_about_title)
    }

    override fun refreshView(data: Any?) {
    }
}