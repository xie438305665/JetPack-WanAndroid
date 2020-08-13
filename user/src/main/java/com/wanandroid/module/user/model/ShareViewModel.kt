package com.wanandroid.module.user.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.ext.CollectViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/8/13
 **/
class ShareViewModel : CollectViewModel(),
    NetResultCallback<ListNetEntity<MutableList<ArticleEntity>>> {
    private var page = 0

    val articleVm get() = _articleVm

    private var _articleVm: MutableLiveData<MutableList<ArticleEntity>> =
        MutableLiveData()

    val shareVm get() = _shareVm

    private var _shareVm: MutableLiveData<Boolean> =
        MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        params ?: return
        val page: Int = params["page"] as Int
        this.page = page
        requestList(requestType, { getUserShardArticles(page) }, this)
    }

    override fun onSuccess(data: ListNetEntity<MutableList<ArticleEntity>>?) {
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

    /**
     * 删除自己分享的文章
     * @param articleId Int
     */
    fun onDeleteArticle(articleId: Int) {
        putRequest(
            { deleteUserShareArticle(articleId) },
            object : NetResultCallback<Any?> {
                override fun onSuccess(data: Any?) {
                    _shareVm.postValue(true)
                }
            })
    }
}