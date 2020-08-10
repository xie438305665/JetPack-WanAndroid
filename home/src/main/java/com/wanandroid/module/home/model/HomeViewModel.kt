package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wanandroid.bridge.adapter.SimpleMultipleItem
import com.wanandroid.bridge.adapter.SimpleMultipleType
import com.wanandroid.bridge.ext.CollectViewModel
import com.wanandroid.bridge.ext.isEquals
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
class HomeViewModel : CollectViewModel() {


    val homeVm get() = _homeVm

    private var _homeVm: MutableLiveData<MutableList<SimpleMultipleItem>> = MutableLiveData()

    /**
     * 默认第一次请求
     * @param requestType Int
     * @param params Map 参数
     */
    override fun onNetRequest(@RequestType requestType: Int, params: Map<String, Any>?) {
        var page = 0
        var isTop = false
        if (!params.isNullOrEmpty()) {
            page = params["page"] as Int
            isTop = params["isTop"] as Boolean
        }
        if (requestType.isEquals(RequestType.LOAD_MORE) && page != 0) {
            onNetArticleList(page)
            return
        }
        requestLoadStatus(true, requestType, LoadStatus.START)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val dataList: MutableList<SimpleMultipleItem> = mutableListOf()
                    withContext(Dispatchers.IO) { async { netService.getBanner() } }.await().data?.let {
                        dataList.add(SimpleMultipleItem(SimpleMultipleType.BANNER, it))
                    }
                    if (isTop) {
                        withContext(Dispatchers.IO) { netService.getArticleTopList() }.data?.let { article ->
                            if (article.isNotEmpty())
                                article.forEach {
                                    dataList.add(
                                        SimpleMultipleItem(
                                            SimpleMultipleType.ITEM,
                                            it
                                        )
                                    )
                                }
                        }
                    }
                    withContext(Dispatchers.IO) { async { netService.getArticleList(0) } }.await().data?.let { article ->
                        if (article.datas.isNotEmpty())
                            article.datas.forEach {
                                dataList.add(
                                    SimpleMultipleItem(
                                        SimpleMultipleType.ITEM,
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
                            val articleList = mutableListOf<SimpleMultipleItem>()
                            it.datas.forEach { item ->
                                articleList.add(SimpleMultipleItem(SimpleMultipleType.ITEM, item))
                            }
                            _homeVm.postValue(articleList)
                        }
                        return
                    }
                    _homeVm.postValue(mutableListOf())
                }
            })
    }
}