package com.wanandroid.module.user.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.ext.CollectViewModel
import com.zhixinhuixue.library.net.NetResultCallback

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class CollectURLViewModel : CollectViewModel() {

    val urlVm get() = _urVm

    private var _urVm: MutableLiveData<MutableList<Any>> = MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        request({ getCollectURList() }, object : NetResultCallback<MutableList<Any>?> {
            override fun onSuccess(data: MutableList<Any>?) {
                super.onSuccess(data)
            }
        })
    }
}