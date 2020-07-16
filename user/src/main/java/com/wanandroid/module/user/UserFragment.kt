package com.wanandroid.module.user

import android.os.Bundle
import android.view.View
import com.wanandroid.bridge.base.BaseFragment
import com.wanandroid.bridge.base.BaseViewModel

/**
 *  @description:用户
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class UserFragment : BaseFragment<Any, BaseViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun initCreate(root: View, bundle: Bundle?) {
    }
}