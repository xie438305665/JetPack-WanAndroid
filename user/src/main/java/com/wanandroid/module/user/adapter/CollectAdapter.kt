package com.wanandroid.module.user.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wanandroid.module.user.ui.fragment.CollectChildFragment

/**
 *  @description: 項目 Adapter
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class CollectAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val sparseArray: SparseArray<Fragment> by lazy {
        SparseArray<Fragment>()
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (sparseArray[position] == null) {
            val fragment = CollectChildFragment.newInstance(position)
            sparseArray.append(position, fragment)
            return fragment
        }
        return sparseArray[position]
    }
}