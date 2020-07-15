package com.wanandroid.bridge.ext

import com.zhixinhuixue.library.widget.custom.CustomToolbar
import com.zhixinhuixue.library.widget.custom.ToolbarClickListener

/**
 *  @description:标题栏
 *  @author xcl qq:244672784
 *  @date 2020/7/15
 **/

/**
 * 初始化Toolbar左边Finish
 * @param icon 图标
 */
fun CustomToolbar.setToolbarFinish(icon: Any?) {
    setLeftIcon(icon)
}


/**
 * 初始化Toolbar中间Title
 * @param title 标题
 */
fun CustomToolbar.setToolbarTitle(title: Any?) {
    setTitleText(title)
}

/**
 * 初始化Toolbar右边Menu
 * @param menu Any? 菜单内容
 * @param isTvMenu Boolean  true只显示tvMenu  false只显示btnMenu
 */
fun CustomToolbar.setToolbarMenu(menu: Any?, isTvMenu: Boolean) {
    if (menu == null) {
        goneViews(tvMenu, btnMenu)
        return
    }
    if (isTvMenu) {
        setMenuText(menu)
    } else {
        setMenuIcon(menu)
    }
}

/**
 * 设置监听事件
 * @param clickListener ToolbarClickListener?
 */
fun CustomToolbar.addToolbarClickListener(clickListener: ToolbarClickListener?) {
    setToolbarClickListener(clickListener)
}
