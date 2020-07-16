package com.wanandroid.module.wx_article.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ProjectTreeEntity

/**
 *  @description:微信公众号ViewModel
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class WxArticleViewModel : BaseViewModel(), NetResultCallback<MutableList<ProjectTreeEntity>> {

    val projectVm get() = _projectVm

    private var _projectVm: MutableLiveData<MutableList<ProjectTreeEntity>> = MutableLiveData()

    /**
     * 项目分类标题
     * @param requestType Int
     * @param params Map
     */
    override fun onNetRequest(@RequestType requestType: Int, params: Map<String, Any>?) {
        request({ getWxArticleChapter() }, this)
    }

    override fun onSuccess(data: MutableList<ProjectTreeEntity>?) {
        data ?: return
        _projectVm.postValue(data)
    }
}