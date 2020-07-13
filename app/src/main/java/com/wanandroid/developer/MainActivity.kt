package com.wanandroid.developer

import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.developer.adapter.MainAdapter
import com.zhixinhuixue.library.net.entity.UserEntity
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  @description:主窗口
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
class MainActivity : BaseActivity<UserEntity, BaseViewModel>() {
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
                R.id.navTabHome -> mainViewPage.currentItem = 0
                R.id.navTabProject -> mainViewPage.currentItem = 1
                R.id.navigation_notifications -> mainViewPage.currentItem = 2
                R.id.navWxArticle -> mainViewPage.currentItem = 3
                R.id.navTabUser -> mainViewPage.currentItem = 4
            }
            true
        }
    }

    override fun refreshView(data: UserEntity) {

    }
}