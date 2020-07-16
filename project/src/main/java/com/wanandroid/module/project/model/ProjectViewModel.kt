package com.wanandroid.module.project.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.ProjectTreeEntity

/**
 *  @description:項目ViewModel
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class ProjectViewModel : BaseViewModel(), NetResultCallback<MutableList<ProjectTreeEntity>> {

    val projectVm get() = _projectVm

    private var _projectVm: MutableLiveData<MutableList<ProjectTreeEntity>> = MutableLiveData()

    /**
     * 项目分类标题
     * @param requestType Int
     * @param params Map
     */
    override fun onNetRequest(@RequestType requestType: Int, params: Map<String, Any>?) {
        request({ getProjectTreeTitle() }, this)
    }

    override fun onSuccess(data: MutableList<ProjectTreeEntity>?) {
        data ?: return
        data.add(0, ProjectTreeEntity(listOf(), "", "", "最新项目", 0, "", false, 0))
        _projectVm.postValue(data)
    }
}