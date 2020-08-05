package com.wanandroid.module.square.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wanandroid.bridge.base.BaseFragment
import com.wanandroid.bridge.base.BaseViewModel
import com.wanandroid.bridge.ext.getStringArray
import com.wanandroid.bridge.ext.textAppearance
import com.wanandroid.module.square.R
import com.wanandroid.module.square.adapter.SquareAdapter
import kotlinx.android.synthetic.main.square_fragment_article.*


/**
 *  @description:广场
 *  @author xcl qq:244672784
 *  @date 2020/8/5
 **/
class SquareFragment : BaseFragment<Any, BaseViewModel>(),
    Observer<Any>, TabLayout.OnTabSelectedListener {
    private lateinit var adapter: SquareAdapter

    override fun getLayoutId(): Int {
        return R.layout.square_fragment_article
    }

    override fun initCreate(root: View, bundle: Bundle?) {
        initViewPager()
    }

    private fun initViewPager() {
        val tabArray = R.array.tabArray.getStringArray()
        adapter = SquareAdapter(
            activity as FragmentActivity,
            tabArray
        )
        squareViewPager.adapter = adapter
        squareViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })
        squareTabLayout.addOnTabSelectedListener(this)
        TabLayoutMediator(squareTabLayout, squareViewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                val tabView = AppCompatTextView(tab.parent?.context)
                tabView.gravity = Gravity.CENTER
                tabView.text = tabArray[position]
                tab.customView = tabView
            }).attach()
    }

    override fun refreshView(data: Any?) {
        data ?: return
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