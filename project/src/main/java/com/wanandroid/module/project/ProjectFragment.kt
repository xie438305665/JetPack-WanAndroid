package com.wanandroid.module.project

import android.os.Bundle
import android.view.View
import com.wanandroid.bridge.base.BaseFragment
import com.wanandroid.bridge.base.BaseViewModel

/**
 *  @description:项目
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class ProjectFragment : BaseFragment<Any, BaseViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initCreate(root: View, bundle: Bundle?) {
    }

    override fun refreshView(data: Any) {
    }
}