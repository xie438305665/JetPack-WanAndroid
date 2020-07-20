package com.wanandroid.developer

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.adapter.SimpleMultipleAdapter
import com.wanandroid.bridge.adapter.SimpleMultipleItem
import com.wanandroid.bridge.adapter.SimpleMultipleType
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.getScreenWidth
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
class MainActivity : BaseActivity<Any, BaseViewModel>(),
    SimpleAdapterListener<SimpleMultipleItem, BaseViewHolder> {
    private lateinit var mAdapter: FragmentStateAdapter
    private lateinit var mDrawerAdapter: SimpleMultipleAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initCreate(bundle: Bundle?) {
        initDrawerMenu()
        initViewPageAdapter()
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

    /**
     * 侧边栏设置Adapter
     */
    private fun initDrawerMenu() {
        mDrawerMenu.setHasFixedSize(true)
        val layoutParams = mDrawerMenu.layoutParams
        layoutParams.width = (getScreenWidth() / 1.3).toInt()
        mDrawerMenu.layoutParams = layoutParams
        mDrawerAdapter = SimpleMultipleAdapter(
            mutableListOf(),
            this,
            mutableListOf(
                SimpleMultipleType(SimpleMultipleType.HEADER, R.layout.item_drawer_menu_header),
                SimpleMultipleType(SimpleMultipleType.HEADER, R.layout.item_drawer_menu_item)
            )
        )
        mDrawerMenu.adapter = mDrawerAdapter
    }

    /**
     * viewPage 设置Adapter
     */
    private fun initViewPageAdapter() {
        mAdapter = MainAdapter(this)
        mainViewPage.currentItem = 0
        mainViewPage.isUserInputEnabled = false
        mainViewPage.offscreenPageLimit = mAdapter.itemCount
        mainViewPage.adapter = mAdapter
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<SimpleMultipleItem, BaseViewHolder>,
        view: View,
        position: Int
    ) {
        super.onBindItemClick(adapter, view, position)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: SimpleMultipleItem, position: Int) {
        super.onBindViewHolder(holder, item, position)
    }

    override fun onFinishClick() {
        mDrawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onMenuClick() {
        "search".logD()
    }
}