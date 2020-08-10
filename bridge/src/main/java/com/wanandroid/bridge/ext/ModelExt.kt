package com.wanandroid.bridge.ext

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback

/**
 *  @description: 收藏
 *  @author xcl qq:244672784
 *  @date 2020/8/5
 **/
open class CollectViewModel : BaseViewModel() {

    val collectVm get() = _collectVm

    private var _collectVm: MutableLiveData<Boolean> = MutableLiveData()

    /**
     *  是否收藏
     * @param collect Boolean
     * @param collectId item
     */
    fun onNetCollect(collect: Boolean, collectId: Int) {
        putRequest(
            { if (!collect) collect(collectId) else unCollect(collectId) },
            object : NetResultCallback<Any?> {
                override fun onSuccess(data: Any?) {
                    _collectVm.postValue(!collect)
                }
            })
    }
}