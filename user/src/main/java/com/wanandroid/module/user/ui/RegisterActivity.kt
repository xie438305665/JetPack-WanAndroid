package com.wanandroid.module.user.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.wanandroid.bridge.annotation.AnnotationValue.Companion.SP_KEY_PASSWORD
import com.wanandroid.bridge.annotation.AnnotationValue.Companion.SP_KEY_USER_NAME
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.ext.clickNoRepeat
import com.wanandroid.module.user.R
import com.wanandroid.module.user.model.RegisterViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import com.zhixinhuixue.library.widget.custom.KeyboardStatusDetector
import kotlinx.android.synthetic.main.activity_register.*

/**
 *  @description:注冊
 *  @author xcl qq:244672784
 *  @date 2020/7/20
 **/
class RegisterActivity : BaseActivity<Any?, RegisterViewModel>(),
    KeyboardStatusDetector.KeyboardListener {
    private lateinit var keyboardStatusDetector: KeyboardStatusDetector

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun showToolbar(): Boolean {
        return true
    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setTitleText(null)
    }

    override fun initCreate(bundle: Bundle?) {
        keyboardStatusDetector = KeyboardStatusDetector(this)
        rootRegister.viewTreeObserver.addOnGlobalLayoutListener(keyboardStatusDetector)
        initClick()
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.registerVm.observe(this, this)
    }

    private fun initClick() {
        btnRegister.clickNoRepeat {
            baseVm.onNetRequest(
                NetViewModel.RequestType.PUT, mapOf(
                    Pair("username", editRegisterUser.text.toString().trim()),
                    Pair("password", editRegisterPassword.text.toString().trim()),
                    Pair("repassword", editRegisterRePassword.text.toString().trim())
                )
            )
        }
    }

    override fun refreshView(data: Any?) {
        data ?: return
        val intent = Intent().apply {
            val bundle = Bundle()
            bundle.putString(
                SP_KEY_USER_NAME,
                editRegisterUser.text.toString().trim()
            )
            bundle.putString(
                SP_KEY_PASSWORD,
                editRegisterPassword.text.toString().trim()
            )
            putExtras(bundle)
        }
        setResult(LoginActivity.CODE, intent)
        onFinishClick()
    }

    override val keyboardView: View by lazy { rootRegister }

    override fun onVisibilityChanged(flag: Boolean) {
        rootRegister.smoothScrollBy(0, 800)
    }

    override fun onDestroy() {
        rootRegister.viewTreeObserver.removeOnGlobalLayoutListener(keyboardStatusDetector)
        super.onDestroy()
    }
}