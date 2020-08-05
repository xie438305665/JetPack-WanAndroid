package com.wanandroid.module.square.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity

/**
 *  @description:广场Child Model
 *  @author xcl qq:244672784
 *  @date 2020/8/5
 **/
class SquareChildViewModel : BaseViewModel(),
    NetResultCallback<ListNetEntity<MutableList<ArticleEntity>>> {
    private var page = 0

    val articleVm get() = _articleVm

    private var _articleVm: MutableLiveData<MutableList<ArticleEntity>> =
        MutableLiveData()

    val collectVm get() = _collectVm

    private var _collectVm: MutableLiveData<ArticleEntity> = MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        params ?: return
        val page: Int = params["page"] as Int
        this.page = page
        requestList(requestType, { getUserArticleList(page) }, this)
    }

    internal fun onNetQuestion(requestType: Int, params: Map<String, Any>?) {
        params ?: return
        val page: Int = params["page"] as Int
        this.page = page
        requestList(requestType, { getQuestionList(page) }, this)
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