package com.wanandroid.module.user.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.ext.CollectViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ShardEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/8/13
 **/
class ShareViewModel : CollectViewModel() {
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
        request(
            true,
            requestType,
            { getUserShardArticles(page) },
            object : NetResultCallback<ShardEntity> {
                override fun onSuccess(data: ShardEntity?) {
                    data?.let {
                        if (page >= it.shareArticles.pageCount || it.shareArticles.datas.isNullOrEmpty()) {
                            requestLoadStatus(true, requestType, LoadStatus.EMPTY)
                            _articleVm.postValue(mutableListOf())
                            return
                        }
                        _articleVm.postValue(it.shareArticles.datas)
                        return
                    }
                    requestLoadStatus(true, requestType, LoadStatus.EMPTY)
                    _articleVm.postValue(mutableListOf())
                }
            })
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
                    _shareVm.postValue(false)
                }
            })
    }
}