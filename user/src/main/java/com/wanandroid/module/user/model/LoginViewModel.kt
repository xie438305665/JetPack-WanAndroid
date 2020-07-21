package com.wanandroid.module.user.model

import androidx.lifecycle.MutableLiveData
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.toast
import com.wanandroid.module.user.R
import com.zhixinhuixue.library.net.NetResultCallback
import com.zhixinhuixue.library.net.entity.LoginEntity
import com.zhixinhuixue.library.net.error.NetException

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/20
 **/
class LoginViewModel : BaseViewModel() {

    val loginVm get() = _loginVm

    private val _loginVm: MutableLiveData<LoginEntity> = MutableLiveData()

    override fun onNetRequest(requestType: Int, params: Map<String, Any>?) {
        if (params.isNullOrEmpty()) {
            "用户名或密码不能为空".toast()
            return
        }
        val username = params["username"] as String
        val password = params["password"] as String
        if (username.isEmpty()) {
            R.string.user_login_user_hint.toast()
            _loginVm.postValue(null)
            return
        }
        if (password.isEmpty()) {
            R.string.user_login_password_hint.toast()
            _loginVm.postValue(null)
            return
        }
        requestNoLoad({ login(username, password) }, object : NetResultCallback<LoginEntity> {
            override fun onSuccess(data: LoginEntity?) {
                _loginVm.postValue(data)
            }

            override fun onError(error: NetException.ErrorBean) {
                _loginVm.postValue(null)
            }
        })
    }
}