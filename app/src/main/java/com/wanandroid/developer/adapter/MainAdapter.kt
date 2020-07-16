package com.wanandroid.developer.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wanandroid.module.article.ArticleFragment
import com.wanandroid.module.home.ui.HomeFragment
import com.wanandroid.module.project.ui.ProjectFragment
import com.wanandroid.module.user.UserFragment

/**
 *  @description: Main Adapter
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class MainAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val sparseArray: SparseArray<Fragment> by lazy {
        val array = SparseArray<Fragment>()
        array.append(0, HomeFragment())
        array.append(1, ProjectFragment())
        array.append(2, ArticleFragment())
        array.append(3, ArticleFragment())
        array.append(4, UserFragment())
        return@lazy array
    }

    override fun getItemCount(): Int {
        return sparseArray.size()
    }

    override fun createFragment(position: Int): Fragment {
        return sparseArray[position]
    }
}