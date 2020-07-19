package com.wanandroid.developer

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.gone
import com.wanandroid.bridge.ext.logD
import com.wanandroid.bridge.ext.visible
import com.wanandroid.developer.adapter.MainAdapter
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  @description:主窗口
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
class MainActivity : BaseActivity<Any, BaseViewModel>() {
    private lateinit var mAdapter: FragmentStateAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initCreate(bundle: Bundle?) {
        mAdapter = MainAdapter(this)
        mainViewPage.currentItem = 0
        mainViewPage.isUserInputEnabled = false
        mainViewPage.offscreenPageLimit = mAdapter.itemCount
        mainViewPage.adapter = mAdapter
        bottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navTabHome -> {
                    mToolbar.visible()
                    mainViewPage.setCurrentItem(0, false)
                }
                R.id.navTabProject -> {
                    mToolbar.gone()
                    mainViewPage.setCurrentItem(1, false)
                }
                R.id.navigation_notifications -> {
                    mToolbar.gone()
                    mainViewPage.setCurrentItem(2, false)
                }
                R.id.navWxArticle -> {
                    mToolbar.gone()
                    mainViewPage.setCurrentItem(3, false)
                }
            }
            true
        }
    }

    override fun refreshView(data: Any) {

    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setLeftIcon(R.drawable.ic_login)
        toolbar.setTitleText("首页")
        toolbar.setMenuIcon(R.drawable.ic_search)
    }

    override fun onFinishClick() {
        mDrawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onMenuClick() {
        "search".logD()
    }
}