package com.wanandroid.module.project.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.ext.CollectViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class ProjectChildViewModel : CollectViewModel(),
    NetResultCallback<ListNetEntity<MutableList<ArticleEntity>>> {
    private var page = 0

    val projectChildVm get() = _projectChildVm

    private var _projectChildVm: MutableLiveData<MutableList<ArticleEntity>> =
        MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        params ?: return
        val page: Int = params["page"] as Int
        val cid: String = params["cid"] as String
        this.page = page
        if (cid.isEmpty()) {
            requestList(requestType, { getNewProject(page) }, this)
            return
        }
        requestList(requestType, { getProjectByType(page, cid) }, this)
    }

    override fun onSuccess(data: ListNetEntity<MutableList<ArticleEntity>>?) {
        data?.let {
            if (page >= it.pageCount || it.datas.isNullOrEmpty()) {
                _projectChildVm.postValue(mutableListOf())
                return
            }
            _projectChildVm.postValue(data.datas)
            return
        }
        _projectChildVm.postValue(mutableListOf())
    }
}