package com.wanandroid.developer

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.adapter.SimpleMultipleItem
import com.wanandroid.bridge.adapter.SimpleMultipleType
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.removeAllActivity
import com.wanandroid.bridge.ext.showDialogMessage
import com.wanandroid.bridge.ext.toStartActivity
import com.wanandroid.bridge.util.SpUtils
import com.wanandroid.module.user.ui.activity.LoginActivity
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.IntegralEntity
import com.zhixinhuixue.library.net.entity.UserInfoEntity
import com.zhixinhuixue.library.net.error.NetException
import kotlin.system.exitProcess

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

    /**
     * 侧边栏初始化
     * @param userInfoEntity UserInfoEntity?
     */
    internal fun initDrawer(userInfoEntity: UserInfoEntity?) {
        defaultMenu.apply {
            if (userInfoEntity != null) {
                set(0, SimpleMultipleItem(SimpleMultipleType.HEADER, userInfoEntity))
                if (defaultMenu.size <= 6) add(SimpleMultipleItem(SimpleMultipleType.ITEM, "退出"))
            } else {
                if (defaultMenu.size == 7) removeAt(6)
            }
        }
        _mainVm.postValue(defaultMenu)
    }

    /**
     * 侧边栏默认值
     */
    private val defaultMenu = mutableListOf(
        SimpleMultipleItem(SimpleMultipleType.HEADER, ""),
        SimpleMultipleItem(SimpleMultipleType.LINE, ""),
        SimpleMultipleItem(SimpleMultipleType.ITEM, "收藏"),
        SimpleMultipleItem(SimpleMultipleType.ITEM, "文章"),
        SimpleMultipleItem(SimpleMultipleType.ITEM, "关于"),
        SimpleMultipleItem(SimpleMultipleType.ITEM, "设置"),
        SimpleMultipleItem(SimpleMultipleType.ITEM, "退出")
    )

    /**
     *退出/切换账号
     */
    internal fun logout(activity: MainActivity) {
        activity.showDialogMessage("您确定退出?",
            cancelable = true,
            positiveAction = {
                SpUtils.clear()
                removeAllActivity()
                exitProcess(0)
            },
            negativeButtonText = "切换账号",
            negativeAction = { toStartActivity(LoginActivity::class.java) })
    }
}