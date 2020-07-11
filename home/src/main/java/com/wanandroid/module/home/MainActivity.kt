package com.wanandroid.module.home

import android.os.Bundle
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseViewModel

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/8
 **/
class MainActivity : BaseActivity<Any, BaseViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun refreshView(data: Any) {
    }

    override fun initCreate(bundle: Bundle?) {
        
    }
}