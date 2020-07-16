package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.isEquals
import com.wanandroid.module.home.adapter.HomeMultipleItem
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity
import com.zhixinhuixue.library.net.error.NetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 *  @description:首页ViewModel
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class HomeViewModel : BaseViewModel() {

    val collectVm get() = _collectVm

    private var _collectVm: MutableLiveData<ArticleEntity> = MutableLiveData()

    val homeVm get() = _homeVm

    private var _homeVm: MutableLiveData<MutableList<HomeMultipleItem>> = MutableLiveData()

    /**
     * 默认第一次请求
     * @param requestType Int
     * @param params Map 参数
     */
    override fun onNetRequest(@RequestType requestType: Int, params: Map<String, Any>?) {
        val page: Int = if (params.isNullOrEmpty()) 0 else params["page"] as Int
        if (requestType.isEquals(RequestType.LOAD_MORE) && page != 0) {
            onNetArticleList(page)
            return
        }
        requestLoadStatus(true, requestType, LoadStatus.START)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val banner = async { netService.getBanner() }
                    val articleTopList = async { netService.getArticleTopList() }
                    val articleList = async { netService.getArticleList(0) }
                    val dataList: MutableList<HomeMultipleItem> = mutableListOf()
                    banner.await().data?.let {
                        dataList.add(HomeMultipleItem(HomeMultipleItem.BANNER, it))
                    }
                    articleTopList.await().data?.let { article ->
                        if (article.isNotEmpty())
                            article.forEach {
                                dataList.add(
                                    HomeMultipleItem(
                                        HomeMultipleItem.ITEM,
                                        it
                                    )
                                )
                            }
                    }
                    articleList.await().data?.let { article ->
                        if (article.datas.isNotEmpty())
                            article.datas.forEach {
                                dataList.add(
                                    HomeMultipleItem(
                                        HomeMultipleItem.ITEM,
                                        it
                                    )
                                )
                            }
                    }
                    _homeVm.postValue(dataList)
                    requestLoadStatus(
                        true,
                        requestType,
                        if (dataList.isEmpty()) LoadStatus.EMPTY else LoadStatus.SUCCESS
                    )
                } catch (e: IOException) {
                    requestLoadStatus(
                        true,
                        requestType,
                        LoadStatus.ERROR
                    )
                    toastErrorMsg(NetException.ErrorBean(-1, e.message))
                }
            }
        }
    }

    /**
     * 首页文章列表
     * @param page Int
     */
    private fun onNetArticleList(page: Int) {
        loadListRequest(
            { getArticleList(page) },
            object : NetResultCallback<ListNetEntity<MutableList<ArticleEntity>>> {
                override fun onSuccess(data: ListNetEntity<MutableList<ArticleEntity>>?) {
                    data?.let {
                        if (page > it.pageCount || it.datas.isNullOrEmpty()) {
                            _homeVm.postValue(mutableListOf())
                            return
                        }
                        if (it.datas.isNotEmpty()) {
                            val articleList = mutableListOf<HomeMultipleItem>()
                            it.datas.forEach { item ->
                                articleList.add(HomeMultipleItem(HomeMultipleItem.ITEM, item))
                            }
                            _homeVm.postValue(articleList)
                        }
                        return
                    }
                    _homeVm.postValue(mutableListOf())
                }
            })
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