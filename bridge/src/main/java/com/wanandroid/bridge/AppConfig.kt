package com.wanandroid.bridge

import com.wanandroid.developer.library.bridge.R

/**
 *  @description:App相关配置类
 *  @author xcl qq:244672784
 *  @date 2020/8/11
 **/
data class AppConfig(
    var themeColor: Int = R.color.colorAccent, //主题颜色
    var showTop: Boolean = true,//是否显示置顶文章
    var showTag: Boolean = true,//是否显示文章得TAG
    var animation: Int = 0,//列表动画
    var model: Boolean = true//是否开启夜间模式
)