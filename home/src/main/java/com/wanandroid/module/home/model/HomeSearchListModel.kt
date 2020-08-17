package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.ext.CollectViewModel
import com.wanandroid.bridge.ext.getUserInfo
import com.wanandroid.room.DbDatabase
import com.wanandroid.room.entity.HistoryEntity
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity

/**
 *  @description:搜索列表
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class HomeSearchListModel : CollectViewModel() {
    val searchVm get() = _searchVm

    private var _searchVm: MutableLiveData<MutableList<ArticleEntity>> =
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
                    data?.let {
                        if (page >= it.pageCount || it.datas.isNullOrEmpty()) {
                            _searchVm.postValue(mutableListOf())
                            return
                        }
                        _searchVm.postValue(data.datas)
                        val userInfoEntity = getUserInfo() ?: return
                        DbDatabase.getDatabase(appContext).historyDao().run {
                            val historyEntity = queryHistory(userInfoEntity.userName)
                            if (historyEntity == null) {
                                insertHistory(
                                    HistoryEntity(
                                        historyValue = mutableListOf(k),
                                        userName = userInfoEntity.userName
                                    )
                                )
                                return@let
                            }
                            if (!historyEntity.historyValue.contains(k)) {
                                historyEntity.historyValue.add(k)
                                updateHistory(historyEntity)
                            }
                        }
                        return
                    }
                    _searchVm.postValue(mutableListOf())
                }
            })
    }
}