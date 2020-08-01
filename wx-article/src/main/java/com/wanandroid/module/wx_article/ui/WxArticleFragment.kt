package com.wanandroid.module.wx_article.ui

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
import com.wanandroid.bridge.ext.textAppearance
import com.wanandroid.bridge.util.XLog
import com.wanandroid.module.wx_article.R
import com.wanandroid.module.wx_article.adapter.WxArticleAdapter
import com.wanandroid.module.wx_article.model.WxArticleViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ProjectTreeEntity
import kotlinx.android.synthetic.main.fragment_article_wx.*


/**
 *  @description:微信公众号
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class WxArticleFragment : BaseFragment<MutableList<ProjectTreeEntity>, WxArticleViewModel>(),
    Observer<MutableList<ProjectTreeEntity>>, TabLayout.OnTabSelectedListener {
    private lateinit var adapter: WxArticleAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_article_wx
    }

    override fun initCreate(root: View, bundle: Bundle?) {
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT, null)
    }

    override fun onResume() {
        super.onResume()
        XLog.d("tag", "onResume")
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.projectVm.observe(this, this)
    }

    private fun initViewPager(arrayList: MutableList<ProjectTreeEntity>) {
        adapter = WxArticleAdapter(activity as FragmentActivity, arrayList)
        wxViewPager.adapter = adapter
        wxViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })
        wxTabLayout.addOnTabSelectedListener(this)
        TabLayoutMediator(wxTabLayout, wxViewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                val tabView = AppCompatTextView(tab.parent?.context)
                tabView.gravity = Gravity.CENTER
                tabView.text = arrayList[position].name
                tab.customView = tabView
            }).attach()
    }

    override fun refreshView(data: MutableList<ProjectTreeEntity>?) {
        data ?: return
        initViewPager(data)
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