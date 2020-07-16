package com.wanandroid.module.article

import android.os.Bundle
import android.view.View
import com.wanandroid.bridge.base.BaseFragment
import com.wanandroid.bridge.base.BaseViewModel

/**
 *  @description:公众号
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class ArticleFragment : BaseFragment<Any, BaseViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_article
    }

    override fun initCreate(root: View, bundle: Bundle?) {
    }
}