package com.wanandroid.module.user.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.bridge.annotation.ARouterPath
import com.wanandroid.bridge.annotation.AnnotationValue.Companion.SP_KEY_PASSWORD
import com.wanandroid.bridge.annotation.AnnotationValue.Companion.SP_KEY_PASSWORD_CHECKED
import com.wanandroid.bridge.annotation.AnnotationValue.Companion.SP_KEY_USER_NAME
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.ext.clickNoRepeat
import com.wanandroid.bridge.ext.formHtml
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.ext.toStartActivity
import com.wanandroid.bridge.util.SpUtils
import com.wanandroid.module.user.R
import com.wanandroid.module.user.model.LoginViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.UserInfoEntity
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import com.zhixinhuixue.library.widget.custom.KeyboardStatusDetector
import kotlinx.android.synthetic.main.user_activity_login.*

/**
 *  @description:登录
 *  @author xcl qq:244672784
 *  @date 2020/7/20
 **/
@Route(path = ARouterPath.LOGIN)
class LoginActivity : BaseActivity<UserInfoEntity, LoginViewModel>(),
    CompoundButton.OnCheckedChangeListener, KeyboardStatusDetector.KeyboardListener {
    private val registerHint = "还没有账号,<font style='color:#DE2162'>立即注册</font>"
    private lateinit var keyboardStatusDetector: KeyboardStatusDetector

    companion object {
        const val CODE = 0x001
    }
    override fun getLayoutId(): Int {
        return R.layout.user_activity_login
    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setTitleText(null)
    }

    override fun initCreate(bundle: Bundle?) {
        keyboardStatusDetector = KeyboardStatusDetector(this)
        rootLogin.viewTreeObserver.addOnGlobalLayoutListener(keyboardStatusDetector)
        if (SpUtils.getValue(SP_KEY_PASSWORD_CHECKED, false)) {
            checkBoxPassword.isChecked = true
            editLoginUser.setText(SpUtils.getValue<String>(SP_KEY_USER_NAME, ""))
            editLoginPassword.setText(SpUtils.getValue<String>(SP_KEY_PASSWORD, ""))
        }
        checkBoxPassword.setOnCheckedChangeListener(this)
        tvRegisterHint.text = registerHint.formHtml()
        initClick()
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.loginVm.observe(this, this)
    }

    private fun initClick() {
        tvRegisterHint.clickNoRepeat {
            toStartActivity(
                Intent(this, RegisterActivity::class.java),
                CODE
            )
        }
        btnLogin.clickNoRepeat {
            btnLogin.text = R.string.user_login_state_load.getString()
            baseVm.onNetRequest(
                NetViewModel.RequestType.PUT,
                mapOf(
                    Pair("username", editLoginUser.text.toString().trim()),
                    Pair("password", editLoginPassword.text.toString().trim())
                )
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras ?: return
        data.extras?.let {
            if (resultCode == CODE && !it.isEmpty) {
                editLoginUser.setText(it.getString(SP_KEY_USER_NAME, ""))
                editLoginPassword.setText(it.getString(SP_KEY_PASSWORD, ""))
            }
        }
    }

    override fun refreshView(data: UserInfoEntity?) {
        if (data == null) {
            btnLogin.text = R.string.user_login_state_retry.getString()
            return
        }
        btnLogin.text = R.string.user_login_state_success.getString()
        toStartActivity(ARouterPath.MAIN)
        finish()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        SpUtils.setValue(SP_KEY_PASSWORD_CHECKED, isChecked)
    }

    override val keyboardView: View by lazy { rootLogin }

    override fun onVisibilityChanged(flag: Boolean) {
        rootLogin.smoothScrollBy(0, 800)
    }

    override fun onDestroy() {
        rootLogin.viewTreeObserver.removeOnGlobalLayoutListener(keyboardStatusDetector)
        super.onDestroy()
    }
}