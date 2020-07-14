package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleTopEntity
import com.zhixinhuixue.library.net.entity.BannerEntity
import com.zhixinhuixue.library.net.entity.ListNetEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class HomeViewModel : BaseViewModel() {
    val bannerVm: MutableLiveData<MutableList<BannerEntity>> = MutableLiveData()

    val homeVm: MutableLiveData<ListNetEntity<MutableList<ArticleTopEntity>>> =
        MutableLiveData()

    override fun onNetRequest(@RequestType requestType: Int) {
        //banner
        requestNoLoad(
            { netService.getBanner() },
            object : NetResultCallback<MutableList<BannerEntity>> {
                override fun onSuccess(data: MutableList<BannerEntity>?) {
                    bannerVm.postValue(data)
                }
            })
    }

    internal fun onNetArticleList(page: Int, @RequestType requestType: Int) {
        //首页文章列表
        requestList(requestType,
            { getArticleList(page) },
            object : NetResultCallback<ListNetEntity<MutableList<ArticleTopEntity>>> {
                override fun onSuccess(data: ListNetEntity<MutableList<ArticleTopEntity>>?) {
                    homeVm.postValue(data)
                }
            })
    }
}