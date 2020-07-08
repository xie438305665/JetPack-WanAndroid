package com.zhixinhuixue.library.widget.custom

/**
 *  @description:Toolbar事件监听
 *  @author xcl qq:244672784
 *  @date 2020/7/8
 **/
interface ToolbarClickListener {
    /**
     * Toolbar左边点击事件
     */
    fun onFinishClick()

    /**
     * Toolbar标题点击事件
     */
    fun onTitleClick()

    /**
     * Toolbar菜单点击事件
     */
    fun onMenuClick()
}