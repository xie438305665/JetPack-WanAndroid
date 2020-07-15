package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.toast
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleTopEntity
import com.zhixinhuixue.library.net.entity.BannerEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity
import com.zhixinhuixue.library.net.error.NetException

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class HomeViewModel : BaseViewModel() {

    val bannerVm: MutableLiveData<MutableList<BannerEntity>> = MutableLiveData()

    val collectVm: MutableLiveData<ArticleTopEntity> = MutableLiveData()

    val homeVm: MutableLiveData<ListNetEntity<MutableList<ArticleTopEntity>>> =
        MutableLiveData()

    /**
     * banner
     * @param requestType Int
     */
    override fun onNetRequest(@RequestType requestType: Int) {
        requestNoLoad(
            { netService.getBanner() },
            object : NetResultCallback<MutableList<BannerEntity>> {
                override fun onSuccess(data: MutableList<BannerEntity>?) {
                    bannerVm.postValue(data)
                }
            })
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
                    homeVm.postValue(data)
                }
            })
    }

    /**
     *  是否收藏
     * @param collect Boolean
     * @param entity item
     */
    internal fun onNetCollect(collect: Boolean,entity: ArticleTopEntity) {
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