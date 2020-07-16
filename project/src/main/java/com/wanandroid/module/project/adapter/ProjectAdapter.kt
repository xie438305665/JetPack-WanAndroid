package com.wanandroid.module.project.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wanandroid.module.project.ui.ProjectChildFragment
import com.zhixinhuixue.library.net.entity.ProjectTreeEntity

/**
 *  @description: 項目 Adapter
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class ProjectAdapter(
    fragmentActivity: FragmentActivity,
    private val data: MutableList<ProjectTreeEntity>
) :
    FragmentStateAdapter(fragmentActivity) {

    private val sparseArray: SparseArray<Fragment> by lazy {
        SparseArray<Fragment>()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        if (sparseArray[position] == null) {
            val fragment = ProjectChildFragment.newInstance(data[position])
            sparseArray.append(position, fragment)
            return fragment
        }
        return sparseArray[position]
    }
}