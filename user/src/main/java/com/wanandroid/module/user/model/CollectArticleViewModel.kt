package com.wanandroid.module.user.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.ext.CollectViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.CollectToolEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class CollectArticleViewModel : CollectViewModel(),
    NetResultCallback<ListNetEntity<MutableList<CollectToolEntity>>> {
    private var page = 0

    val articleVm get() = _articleVm

    private var _articleVm: MutableLiveData<MutableList<CollectToolEntity>> =
        MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        params ?: return
        val page: Int = params["page"] as Int
        this.page = page
        requestList(requestType, { getCollectArticleList(page) }, this)
    }

    override fun onSuccess(data: ListNetEntity<MutableList<CollectToolEntity>>?) {
        data?.let {
            if (page >= it.pageCount || it.datas.isNullOrEmpty()) {
                _articleVm.postValue(mutableListOf())
                return
            }
            _articleVm.postValue(data.datas)
            return
        }
        _articleVm.postValue(mutableListOf())
    }
}