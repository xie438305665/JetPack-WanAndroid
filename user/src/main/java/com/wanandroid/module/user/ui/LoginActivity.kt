package com.wanandroid.module.user.ui

import android.os.Bundle
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.module.user.R
import com.wanandroid.module.user.model.LoginViewModel
import com.zhixinhuixue.library.net.entity.UserEntity

/**
 *  @description:登录
 *  @author xcl qq:244672784
 *  @date 2020/7/20
 **/
class LoginActivity : BaseActivity<UserEntity, LoginViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun showToolbar(): Boolean {
        return false
    }

    override fun initCreate(bundle: Bundle?) {
    }

    override fun refreshView(data: UserEntity) {
    }
}