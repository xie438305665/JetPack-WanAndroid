package com.wanandroid.developer

import android.os.Bundle
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.entity.UserEntity
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  @description:主窗口
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
class MainActivity : BaseActivity<UserEntity, BaseViewModel<UserEntity>>(){

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initCreate(bundle: Bundle?) {
        bottomNavigation.setOnNavigationItemReselectedListener {

        }
    }

    override fun initObserver() {
    }

//    override fun onNetRequest() {
//        dataVm.request({ login("", "") }, object : NetResultCallback<UserEntity> {
//            override fun onError(e: Throwable?) {
//            }
//
//            override fun onSuccess(data: UserEntity?) {
//            }
//        }, true)
//    }

    override fun onNetRequest() {
        baseVm.request({ login("", "") },this, true)
    }

    override fun onSuccess(data: UserEntity?) {
        super.onSuccess(data)
    }
}