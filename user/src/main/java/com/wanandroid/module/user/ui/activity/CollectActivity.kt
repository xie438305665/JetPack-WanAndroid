package com.wanandroid.module.user.ui.activity

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.getStringArray
import com.wanandroid.bridge.ext.textAppearance
import com.wanandroid.module.user.R
import com.wanandroid.module.user.adapter.CollectAdapter
import kotlinx.android.synthetic.main.user_activity_collect.*

/**
 *  @description:收藏
 *  @author xcl qq:244672784
 *  @Date 2020/8/2
 **/
class CollectActivity : BaseActivity<Any, BaseViewModel>(), TabLayout.OnTabSelectedListener {
    private lateinit var mAdapter: CollectAdapter
    private val tabArray = R.array.user_collect_tab_array.getStringArray()
    override fun showToolbar(): Boolean {
        return false
    }

    override fun getLayoutId(): Int {
        return R.layout.user_activity_collect
    }

    override fun initCreate(bundle: Bundle?) {
        mAdapter = CollectAdapter(this)
        collectViewPager.run {
            offscreenPageLimit = mAdapter.itemCount
            adapter = mAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                }
            })
        }

        collectTabLayout.addOnTabSelectedListener(this)
        TabLayoutMediator(collectTabLayout, collectViewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                val tabView = AppCompatTextView(tab.parent!!.context)
                tabView.gravity = Gravity.CENTER
                tabView.text = tabArray[position]
                tab.customView = tabView
            }).attach()
        collectFinish.setOnClickListener {
            finish()
        }
    }

    override fun refreshView(data: Any?) {

    }

    /**
     * 字体加粗变颜色
     *
     * @param tab
     * @param isSelected
     */
    private fun changeTabView(tab: TabLayout.Tab?, isSelected: Boolean) {
        val tabView: AppCompatTextView = tab?.customView as AppCompatTextView? ?: return
        tabView.textAppearance(if (isSelected) R.style.TabLayoutBoldTextSize else R.style.TabLayoutNormalTextSize)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        changeTabView(tab, false)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        changeTabView(tab, true)
    }
}