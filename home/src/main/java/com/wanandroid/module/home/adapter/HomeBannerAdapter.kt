package com.wanandroid.module.home.adapter

import android.net.Uri
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.adapter.BannerAdapter
import com.zhixinhuixue.library.net.entity.BannerEntity


/**
 *  @description:首页顶部Banner
 *  @author xcl qq:244672784
 *  @date 2020/7/14
 **/
class HomeBannerAdapter(bannerList: ArrayList<BannerEntity>) :
    BannerAdapter<BannerEntity, HomeBannerAdapter.BannerViewHolder>(bannerList) {

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder? {
        val imageView = AppCompatImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: BannerEntity,
        position: Int,
        size: Int
    ) {
        holder.imageView.setImageURI(Uri.parse(data.imagePath))
    }

    class BannerViewHolder(view: AppCompatImageView) : RecyclerView.ViewHolder(view) {
        var imageView: AppCompatImageView = view
    }
}