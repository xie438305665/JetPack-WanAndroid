package com.wanandroid.bridge.base

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.ext.toast
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.NetStatusEntity
import com.zhixinhuixue.library.net.error.NetException

/**
 *  @description:ViewMode基类
 *  @author xcl qq:244672784
 *  @Date 2020/7/5
 **/
open class BaseViewModel : NetViewModel() {
    val loadVm get() = _loadVm

    private val _loadVm: MutableLiveData<NetStatusEntity> = MutableLiveData()

    /**
     * 网络请求 根据业务可以重写函数
     * @param requestType @link[NetViewModel.RequestType]
     */
    open fun onNetRequest(
        @RequestType requestType: Int = RequestType.DEFAULT,
        params: Map<String, Any>?
    ) {
    }

    /**
     * 请求加载UI
     * @param showLoading 请求时是否显示加载UI
     * @param requestType 请求类型
     * @param loadStatus 加载状态
     */
    override fun requestLoadStatus(
        showLoading: Boolean,
        @RequestType requestType: Int,
        @LoadStatus loadStatus: Int
    ) {
        if (!showLoading) return
        _loadVm.postValue(NetStatusEntity(requestType, loadStatus))
    }

    /**
     * 错误信息Toast
     * @param error ErrorEnum
     */
    override fun toastErrorMsg(error: NetException.ErrorBean) {
        error.errorMsg.toast()
    }
}


