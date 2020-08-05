package com.wanandroid.module.square.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wanandroid.module.square.ui.fragment.SquareChildFragment

/**
 *  @description: 广场 Adapter
 *  @author xcl qq:244672784
 *  @date 2020/8/5
 **/
class SquareAdapter(
    fragmentActivity: FragmentActivity,
    private val data: Array<String>
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
            val fragment = SquareChildFragment.newInstance(position)
            sparseArray.append(position, fragment)
            return fragment
        }
        return sparseArray[position]
    }
}