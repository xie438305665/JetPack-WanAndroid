package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ArticleEntity
import com.zhixinhuixue.library.net.entity.WebViewEntity

/**
 *  @description:首页Item ViewModel
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class HomeItemViewModel : BaseViewModel() {

    val collectVm get() = _collectVm

    private var _collectVm: MutableLiveData<ArticleEntity> = MutableLiveData()

    val itemVm get() = _itemVmVm

    private var _itemVmVm: MutableLiveData<WebViewEntity> = MutableLiveData()

    /**
     * 默认第一次请求
     * @param requestType Int
     * @param params Map 参数
     */
    override fun onNetRequest(@RequestType requestType: Int, params: Map<String, Any>?) {

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