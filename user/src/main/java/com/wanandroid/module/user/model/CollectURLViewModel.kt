package com.wanandroid.module.user.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.ext.CollectViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class CollectURLViewModel : CollectViewModel() {

    val urlVm get() = _urVm

    private var _urVm: MutableLiveData<MutableList<ArticleEntity>> = MutableLiveData()

    val deleteVm get() = _deleteVm

    private var _deleteVm: MutableLiveData<Boolean> =
        MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        request(
            true,
            requestType,
            { getCollectURList() },
            object : NetResultCallback<MutableList<ArticleEntity>?> {
                override fun onSuccess(data: MutableList<ArticleEntity>?) {
                    _urVm.postValue(data)
                }
            })
    }

    /**
     * 删除这条网址
     * @param id Int
     */
    fun onDeleteURL(id: Int) {
        putRequest({ deleteURList(id) },
            object : NetResultCallback<Any?> {
                override fun onSuccess(data: Any?) {
                    _deleteVm.postValue(false)
                }
            })
    }
}