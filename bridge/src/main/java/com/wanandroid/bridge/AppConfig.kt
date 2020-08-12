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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AppConfig

        if (themeColor != other.themeColor) return false
        if (showTop != other.showTop) return false
        if (showTag != other.showTag) return false
        if (animation != other.animation) return false
        if (model != other.model) return false

        return true
    }

    override fun hashCode(): Int {
        var result = themeColor
        result = 31 * result + showTop.hashCode()
        result = 31 * result + showTag.hashCode()
        result = 31 * result + animation
        result = 31 * result + model.hashCode()
        return result
    }
}