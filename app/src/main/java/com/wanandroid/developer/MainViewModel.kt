package com.wanandroid.developer

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.adapter.SimpleMultipleItem
import com.wanandroid.bridge.adapter.SimpleMultipleType
import com.wanandroid.bridge.base.BaseViewModel
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.IntegralEntity
import com.zhixinhuixue.library.net.entity.UserInfoEntity
import com.zhixinhuixue.library.net.error.NetException

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/20
 **/
class MainViewModel : BaseViewModel() {

    val mainVm get() = _mainVm

    private var _mainVm: MutableLiveData<MutableList<SimpleMultipleItem>> = MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        requestNoLoad({ getCoinUserInfo() }, object : NetResultCallback<IntegralEntity> {
            override fun onSuccess(data: IntegralEntity?) {
                data?.let {
                    defaultMenu.apply {
                        add(0, SimpleMultipleItem(SimpleMultipleType.HEADER, ""))
                        SimpleMultipleItem(SimpleMultipleType.ITEM, "退出")
                    }
                    _mainVm.postValue(defaultMenu)
                }
            }

            override fun onError(error: NetException.ErrorBean) {
                defaultMenu.add(0, SimpleMultipleItem(SimpleMultipleType.HEADER, ""))
                _mainVm.postValue(defaultMenu)
            }
        })
    }

    internal fun initDrawer(userInfoEntity: UserInfoEntity) {
        defaultMenu.apply {
            add(0, SimpleMultipleItem(SimpleMultipleType.HEADER, userInfoEntity))
            add(SimpleMultipleItem(SimpleMultipleType.ITEM, "退出"))
        }
        _mainVm.postValue(defaultMenu)
    }

    private val defaultMenu = mutableListOf(
        SimpleMultipleItem(SimpleMultipleType.LINE, ""),
        SimpleMultipleItem(SimpleMultipleType.ITEM, "收藏"),
        SimpleMultipleItem(SimpleMultipleType.ITEM, "文章"),
        SimpleMultipleItem(SimpleMultipleType.ITEM, "关于"),
        SimpleMultipleItem(SimpleMultipleType.ITEM, "设置")
    )
}