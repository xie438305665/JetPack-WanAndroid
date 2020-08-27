package com.wanandroid.module.user.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wanandroid.module.user.ui.fragment.CollectArticleFragment
import com.wanandroid.module.user.ui.fragment.CollectURLFragment

/**
 *  @description:收藏Adapter
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class CollectAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val sparseArray: SparseArray<Fragment> by lazy {
        SparseArray<Fragment>().apply {
            append(0, CollectArticleFragment())
            append(1, CollectURLFragment())
        }
    }

    override fun getItemCount(): Int {
        return sparseArray.size()
    }

    override fun createFragment(position: Int): Fragment {
        return sparseArray[position]
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        sparseArray.clear()
    }
}