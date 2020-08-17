package com.wanandroid.module.home.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.wanandroid.bridge.adapter.*
import com.wanandroid.bridge.annotation.AnnotationValue.Companion.BUNDLE_KEY_SEARCH
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.ext.*
import com.wanandroid.module.home.R
import com.wanandroid.module.home.model.HomeHotSearchModel
import com.zhixinhuixue.library.net.entity.SearchEntity
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import kotlinx.android.synthetic.main.home_activity_hot_search.*


/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/7/29
 **/
class HomeHotSearchActivity : BaseActivity<MutableList<SearchEntity>, HomeHotSearchModel>(),
    Observer<MutableList<SearchEntity>>, SimpleAdapterListener<SimpleMultipleItem, BaseViewHolder> {
    private lateinit var mAdapter: SimpleMultipleAdapter
    private var searchValue: String? = null

    companion object {
        const val CODE = 0X107
    }

    override fun getLayoutId(): Int {
        return R.layout.home_activity_hot_search
    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setMenuText("搜索")
        toolbar.setEditText {
            this.searchValue = it.toString().trim()
        }
    }

    override fun initCreate(bundle: Bundle?) {
        initAdapter()
        baseVm.onNetRequest(params = null)
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.hotVm.observe(this, this)
    }

    private fun initAdapter() {
        mAdapter = SimpleMultipleAdapter(
            mutableListOf(), this, mutableListOf(
                SimpleMultipleType(SimpleMultipleType.FORMAT, R.layout.home_item_hot_search_format),
                SimpleMultipleType(SimpleMultipleType.ITEM, R.layout.home_item_hot_search_flex_box)
            )
        )
        rvHotSearch.run {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: SimpleMultipleItem, position: Int) {
        when (item.itemType) {
            SimpleMultipleType.FORMAT -> {
                holder.setText(
                    R.id.tvItemHotSearchFormat,
                    item.content.toString()
                )
                holder.getView<AppCompatTextView>(R.id.tvItemHistoryAllDelete).run {
                    this.visibleOrGone(position == 2)
                    clickNoRepeat {
                        showDialogMessage(
                            "您确定删除全部搜索记录？",
                            cancelable = true,
                            positiveAction = { deleteAllHistory() })
                    }
                }
            }
            SimpleMultipleType.ITEM -> {
                val flexBoxAdapter = SimpleAdapter(R.layout.home_item_hot_search_tag,
                    item.content as MutableList<String>,
                    object : SimpleAdapterListener<String, BaseViewHolder> {
                        override fun onBindViewHolder(
                            holder: BaseViewHolder,
                            item: String,
                            childPosition: Int
                        ) {
                            holder.getView<AppCompatImageButton>(R.id.btnSearchTagDelete).run {
                                this.visibleOrGone(position > 2)
                                clickNoRepeat {
                                    showDialogMessage(
                                        "您确定删除这条搜索记录？",
                                        cancelable = true,
                                        positiveAction = { deleteItemHistory(item) })
                                }
                            }
//                            holder.getView<RelativeLayout>(R.id.flItemSearch).run {
//                                val lp: ViewGroup.LayoutParams = layoutParams
//                                if (lp is FlexboxLayoutManager.LayoutParams) {
//                                    lp.flexGrow = 1.0f
//                                }
//                            }
                            holder.getView<AppCompatTextView>(R.id.tvItemHotSearchTag).run {
                                text = item
                                clickNoRepeat {
                                    HomeSearchListActivity.start(
                                        item,
                                        CODE,
                                        this@HomeHotSearchActivity
                                    )
                                }
                            }
                        }
                    })
                holder.getView<RecyclerView>(R.id.rvHotSearchFlexBox).run {
                    setHasFixedSize(true)
                    layoutManager = FlexboxLayoutManager(holder.itemView.context).apply {
                        flexWrap = FlexWrap.WRAP
                        flexDirection = FlexDirection.ROW
                        alignItems = AlignItems.CENTER
//                        justifyContent = JustifyContent.CENTER
                    }
                    adapter = flexBoxAdapter
                }
            }
        }
    }

    override fun refreshView(data: MutableList<SearchEntity>?) {
        if (data.isNullOrEmpty()) return
        mAdapter.data.clear()
        mAdapter.data.addAll(baseVm.createMultipleData(queryHistory()?.historyValue, data))
        mAdapter.notifyDataSetChanged()
    }

    override fun onMenuClick() {
        if (searchValue.isNullOrEmpty()) return
        HomeSearchListActivity.start(this.searchValue, CODE, this)
    }

    /**
     * 删除全部历史记录
     */
    private fun deleteAllHistory() {
        mAdapter.data.removeAt(3)
        mAdapter.data.removeAt(2)
        mAdapter.notifyDataSetChanged()
        deleteHistory()
    }

    /**
     * 删除单个记录
     * @param item String
     */
    private fun deleteItemHistory(item: String) {
        (mAdapter.data[3].content as MutableList<String>).run {
            remove(item)
            if (isNullOrEmpty()) {
                deleteAllHistory()
            } else {
                mAdapter.notifyItemChanged(3)
                queryHistory()?.let {
                    it.historyValue =
                        (mAdapter.data[3].content as MutableList<String>)
                    updateHistory(it)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != CODE || resultCode != Activity.RESULT_OK || data == null) return
        val search = data.getStringExtra(BUNDLE_KEY_SEARCH)
        search ?: return
        if (mAdapter.data.size > 3) {
            (mAdapter.data[3].content as MutableList<String>).let {
                if (!it.contains(search)) {
                    it.add(search)
                    mAdapter.notifyItemChanged(3)
                }
            }
        } else {
            mAdapter.data.run {
                add(SimpleMultipleItem(SimpleMultipleType.FORMAT, "历史搜索"))
                add(
                    SimpleMultipleItem(
                        SimpleMultipleType.ITEM,
                        mutableListOf(search)
                    )
                )
            }
            mAdapter.notifyDataSetChanged()
        }
    }
}