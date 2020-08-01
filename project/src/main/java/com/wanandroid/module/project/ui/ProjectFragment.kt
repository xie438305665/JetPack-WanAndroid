package com.wanandroid.module.project.ui

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
import com.wanandroid.module.project.R
import com.wanandroid.module.project.adapter.ProjectAdapter
import com.wanandroid.module.project.model.ProjectViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ProjectTreeEntity
import kotlinx.android.synthetic.main.fragment_project.*


/**
 *  @description:项目
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class ProjectFragment : BaseFragment<MutableList<ProjectTreeEntity>, ProjectViewModel>(),
    Observer<MutableList<ProjectTreeEntity>>, TabLayout.OnTabSelectedListener {
    private lateinit var adapter: ProjectAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initCreate(root: View, bundle: Bundle?) {
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT, null)
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.projectVm.observe(this, this)
    }

    private fun initViewPager(arrayList: MutableList<ProjectTreeEntity>) {
        adapter = ProjectAdapter(activity as FragmentActivity, arrayList)
        projectViewPager.adapter = adapter
        projectViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })
        projectTabLayout.addOnTabSelectedListener(this)
        TabLayoutMediator(projectTabLayout, projectViewPager,
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