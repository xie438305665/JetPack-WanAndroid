package com.wanandroid.module.home.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.adapter.SimpleMultipleItem
import com.wanandroid.bridge.adapter.SimpleMultipleType
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

    /**
     * 组装数据
     */
    internal fun createMultipleData(
        history: MutableList<String>?,
        hotSearch: MutableList<SearchEntity>?
    ): MutableList<SimpleMultipleItem> {
        val data = mutableListOf<SimpleMultipleItem>()
        if (hotSearch.isNullOrEmpty()) {
            return data
        }
        data.add(SimpleMultipleItem(SimpleMultipleType.FORMAT, "热门标签"))
        data.add(SimpleMultipleItem(SimpleMultipleType.ITEM, hotSearch.map { it.name }))
        if (history.isNullOrEmpty()) return data
        data.add(SimpleMultipleItem(SimpleMultipleType.FORMAT, "历史搜索"))
        data.add(SimpleMultipleItem(SimpleMultipleType.ITEM, history.map { it }))
        return data
    }
}