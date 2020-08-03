package com.wanandroid.module.user.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.module.user.R
import com.wanandroid.module.user.ui.fragment.SettingPreferenceFragment
import com.zhixinhuixue.library.widget.custom.CustomToolbar

/**
 *  @description:設置APP
 *  @author xcl qq:244672784
 *  @Date 2020/8/2
 **/
class SettingActivity : BaseActivity<Any, BaseViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.user_activity_setting
    }

    @SuppressLint("SetTextI18n")
    override fun initCreate(bundle: Bundle?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.user_setting_fragment, SettingPreferenceFragment())
            .commitNow()
    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setTitleText(R.string.user_setting_title)
    }

    override fun refreshView(data: Any?) {
    }
}