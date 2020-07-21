package com.wanandroid.module.user.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.toast
import com.wanandroid.module.user.R
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.error.NetException

/**
 *  @description:注册
 *  @author xcl qq:244672784
 *  @date 2020/7/20
 **/
class RegisterViewModel : BaseViewModel() {

    val registerVm get() = _registerVm

    private val _registerVm: MutableLiveData<Any?> = MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        if (params.isNullOrEmpty()) {
            "用户名或密码不能为空".toast()
            return
        }
        val username = params["username"] as String
        val password = params["password"] as String
        val rePassword = params["repassword"] as String
        if (username.isEmpty()) {
            R.string.user_login_user_hint.toast()
            _registerVm.postValue(null)
            return
        }
        if (password.isEmpty()) {
            R.string.user_login_password_hint.toast()
            _registerVm.postValue(null)
            return
        }
        if (rePassword.isEmpty()) {
            R.string.user_login_re_password_hint.toast()
            _registerVm.postValue(null)
            return
        }
        if (password != rePassword){
            R.string.user_login_password_equals_hint.toast()
            _registerVm.postValue(null)
            return
        }
        requestNoLoad(
            { register(username, password, rePassword) },
            object : NetResultCallback<Any?> {
                override fun onSuccess(data: Any?) {
                    _registerVm.postValue(data)
                }

                override fun onError(error: NetException.ErrorBean) {
                    _registerVm.postValue(null)
                }
            })
    }
}