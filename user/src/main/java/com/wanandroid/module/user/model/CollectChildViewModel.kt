package com.wanandroid.module.user.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.entity.CollectToolEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class CollectChildViewModel : BaseViewModel() {

    val collectVm get() = _collectVm

    private var _collectVm: MutableLiveData<MutableList<CollectToolEntity>> =
        MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {

    }
}