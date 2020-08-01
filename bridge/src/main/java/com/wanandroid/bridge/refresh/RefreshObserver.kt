package com.wanandroid.bridge.refresh

import androidx.lifecycle.Observer
import com.zhixinhuixue.library.net.NetViewModel

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/8/1
 **/
interface RefreshObserver<T> : Observer<MutableList<T>> {
    override fun onChanged(t: MutableList<T>?) {

    }

    /**
     * 下拉刷新
     */
     fun onRefresh() {

    }

    /**
     * 上拉加载
     */
     fun onLoadMore() {

    }
}