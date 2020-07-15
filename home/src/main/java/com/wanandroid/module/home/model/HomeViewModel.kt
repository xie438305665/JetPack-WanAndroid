package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.toast
import com.wanandroid.module.home.adapter.HomeMultipleItem
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleTopEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity
import com.zhixinhuixue.library.net.error.NetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class HomeViewModel : BaseViewModel() {
    val collectVm: MutableLiveData<ArticleTopEntity> = MutableLiveData()

    val homeVm: MutableLiveData<MutableList<HomeMultipleItem>> =
        MutableLiveData()

    /**
     * banner
     * @param requestType Int
     */
    override fun onNetRequest(@RequestType requestType: Int) {
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
                    homeVm.postValue(dataList)
                    requestLoadStatus(true, requestType, if (dataList.isEmpty()) LoadStatus.EMPTY else LoadStatus.SUCCESS)
                } catch (e: Throwable) {
                    val expectation = NetException.instance.errorTransform(e)
                    requestLoadStatus(
                        true,
                        requestType,
                        LoadStatus.ERROR
                    )
                    expectation.errorMsg.toast()
                }
            }
        }
    }

    /**
     * 首页文章列表
     * @param page Int
     * @param requestType Int
     */
    internal fun onNetArticleList(page: Int, @RequestType requestType: Int) {
        requestList(requestType,
            { getArticleList(page) },
            object : NetResultCallback<ListNetEntity<MutableList<ArticleTopEntity>>> {
                override fun onSuccess(data: ListNetEntity<MutableList<ArticleTopEntity>>?) {
                    data?.let {
                        if (it.datas.isNotEmpty()) {
                            val articleList = mutableListOf<HomeMultipleItem>()
                            it.datas.forEach { item ->
                                articleList.add(HomeMultipleItem(HomeMultipleItem.ITEM, item))
                            }
                            homeVm.postValue(articleList)
                        }
                    }
                }
            })
    }

    /**
     *  是否收藏
     * @param collect Boolean
     * @param entity item
     */
    internal fun onNetCollect(collect: Boolean, entity: ArticleTopEntity) {
        putRequest(
            { if (collect) collect(entity.chapterId) else unCollect(entity.chapterId) },
            object : NetResultCallback<Any?> {
                override fun onSuccess(data: Any?) {
                    entity.collect = collect
                    collectVm.postValue(entity)
                }
            })
    }
}