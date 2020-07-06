package com.wanandroid.developer

import android.os.Bundle
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseViewMode
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  @description:主窗口
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
class MainActivity : BaseActivity<Any, BaseViewMode<Any>>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initCreate(bundle: Bundle?) {
        bottomNavigation.setOnNavigationItemReselectedListener {

        }
    }

    override fun initObserver() {
    }

    override fun onNetRequest() {
    }
}