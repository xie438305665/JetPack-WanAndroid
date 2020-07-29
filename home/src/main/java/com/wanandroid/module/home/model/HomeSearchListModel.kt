package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity

/**
 *  @description:搜索列表
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class HomeSearchListModel : BaseViewModel() {

    val searchVm get() = _searchVm

    private var _searchVm: MutableLiveData<ListNetEntity<MutableList<ArticleEntity>>> =
        MutableLiveData()

    /**
     * 默认第一次请求
     * @param requestType Int
     * @param params Map 参数
     */
    override fun onNetRequest(@RequestType requestType: Int, params: Map<String, Any>?) {
        params ?: return
        val page = params["page"] as Int
        val k = params["k"] as String
        requestList(
            requestType,
            { getArticleQueryByKey(page, k) },
            object : NetResultCallback<ListNetEntity<MutableList<ArticleEntity>>> {
                override fun onSuccess(data: ListNetEntity<MutableList<ArticleEntity>>?) {
                    _searchVm.postValue(data)
                }
            })
    }
}