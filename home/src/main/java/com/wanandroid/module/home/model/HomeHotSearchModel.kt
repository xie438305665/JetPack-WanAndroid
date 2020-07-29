package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.SearchEntity

/**
 *  @description:热门搜索
 *  @author xcl qq:244672784
 *  @date 2020/7/29
 **/
class HomeHotSearchModel : BaseViewModel() {

    val hotVm get() = _hotVm
    private var _hotVm: MutableLiveData<MutableList<SearchEntity>> = MutableLiveData()

    /**
     * 默认第一次请求
     * @param requestType Int
     * @param params Map 参数
     */
    override fun onNetRequest(@RequestType requestType: Int, params: Map<String, Any>?) {
        request({ getHotSearch() }, object : NetResultCallback<MutableList<SearchEntity>> {
            override fun onSuccess(data: MutableList<SearchEntity>?) {
                _hotVm.postValue(data)
            }
        })
    }
}