package com.wanandroid.module.wx_article.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/16
 **/
class WxArticleChildViewModel : BaseViewModel(),
    NetResultCallback<ListNetEntity<MutableList<ArticleEntity>>> {
    private var page = 0

    val projectChildVm get() = _projectChildVm

    private var _projectChildVm: MutableLiveData<MutableList<ArticleEntity>> =
        MutableLiveData()

    val collectVm get() = _collectVm

    private var _collectVm: MutableLiveData<ArticleEntity> = MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        params ?: return
        val page: Int = params["page"] as Int
        val id: String = params["id"] as String
        this.page = page
        requestList(requestType, { getWxArticle(page, id) }, this)
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

    /**
     *  是否收藏
     * @param collect Boolean
     * @param entity item
     */
    internal fun onNetCollect(collect: Boolean, entity: ArticleEntity) {
        putRequest(
            { if (collect) collect(entity.chapterId) else unCollect(entity.chapterId) },
            object : NetResultCallback<Any?> {
                override fun onSuccess(data: Any?) {
                    entity.collect = collect
                    _collectVm.postValue(entity)
                }
            })
    }
}