package com.wanandroid.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.bridge.adapter.SimpleAdapter
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.base.BaseFragment
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.ext.logD
import com.wanandroid.module.home.R
import com.wanandroid.module.home.model.HomeViewModel
import com.zhixinhuixue.library.net.NetViewModel
import com.zhixinhuixue.library.net.entity.ArticleTopEntity
import com.zhixinhuixue.library.net.entity.BannerEntity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  @description:首页
 *  @author xcl qq:244672784
 *  @date 2020/7/13
 **/
class HomeFragment : BaseFragment<MutableList<BannerEntity>, HomeViewModel>(),
    Observer<MutableList<BannerEntity>>,
    SimpleAdapterListener<ArticleTopEntity, BaseViewHolder> {
    lateinit var adapter: SimpleAdapter<ArticleTopEntity, BaseViewHolder>
    private var page: Int = 0
    private var position: Int = 0
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initCreate(root: View, bundle: Bundle?) {
        homeRecyclerView.setHasFixedSize(true)
        adapter = SimpleAdapter(R.layout.item_article_home, mutableListOf(), this)
        adapter.setOnItemChildClickListener() { _, _, position ->
            position.logD()
        }
        adapter.apply {
            addLoadMoreModule(adapter)
            setEmptyView(R.layout.layout_load_empty)
            setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        }
        homeRecyclerView.adapter = adapter
//        homeBanner.addBannerLifecycleObserver(this)
//            .setAdapter(HomeBannerAdapter(ArrayList()))
//            .setIndicator(CircleIndicator(activity))
//            .setOnBannerListener { _, position -> position.logD() }
        baseVm.onNetRequest(NetViewModel.RequestType.DEFAULT)
        baseVm.onNetArticleList(page, NetViewModel.RequestType.DEFAULT)
    }

    override fun initObserver() {
        super.initObserver()
        baseVm.bannerVm.observe(this, this)
        baseVm.homeVm.observe(this, Observer {
            adapter.data.addAll(it.datas)
            adapter.notifyDataSetChanged()
        })
        baseVm.collectVm.observe(this, Observer {
            adapter.data[position].collect = it.collect
            adapter.notifyItemChanged(this.position)
        })
    }

    override fun onNetRetry() {
        super.onNetRetry()
        this.page = 0
        baseVm.onNetArticleList(this.page, NetViewModel.RequestType.DEFAULT)
    }

    override fun refreshView(data: MutableList<BannerEntity>) {
//        homeBanner.adapter.setDatas(data.toList())
//        homeBanner.adapter.notifyDataSetChanged()
//        homeBanner.start()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ArticleTopEntity, position: Int) {
        holder.setText(
            R.id.tv_home_article_item_date,
            "${R.string.home_article_date.getString()}${item.niceDate}"
        )
        holder.setText(
            R.id.tv_home_article_item_type,
            "${item.superChapterName}/${item.chapterName}"
        )
        val name =
            if (item.author.isEmpty()) "${R.string.home_article_shareUser.getString()}${item.shareUser}" else "${R.string.home_article_author.getString()}${item.author}"
        holder.setText(R.id.tv_home_article_item_author, name)
        holder.setText(R.id.tv_home_article_item_title, item.title)
        val ivCollect = holder.getView<AppCompatImageView>(R.id.iv_home_article_item_collect)
        ivCollect.isSelected = item.collect
        ivCollect.setOnClickListener {
            this.position = position
            this.baseVm.onNetCollect(!it.isSelected, item)
        }
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<ArticleTopEntity, BaseViewHolder>,
        view: View,
        position: Int
    ) {
        position.logD()
    }
}