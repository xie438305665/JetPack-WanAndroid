package com.zhixinhuixue.library.net.api

/**
 *  @description:请求地址相关类
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
object NetUrl {
    const val BASE_URL = "https://wanandroid.com/"

    /**
     * Apk检测升级
     */
    const val APK_UPGRADE = ""

    /**
     * 登录
     */
    const val LOGIN_URL = "user/login"

    /**
     * 注册
     */
    const val REGISTER_URL = "user/register"

    /**
     * banner轮播图
     */
    const val BANNER_URL = "banner/json"

    /**
     * 置顶文章
     */
    const val ARTICLE_TOP_URL = "article/top/json"

    /**
     * 首页文章列表
     */
    const val ARTICLE_LIST_URL = "article/list/{page}/json"

    /**
     * 项目分类标题
     */
    const val PROJECT_TREE_URL = "project/tree/json"

    /**
     * 根据Page获取项目数据
     */
    const val PROJECT_LIST_PAGE_URL = "project/list/{page}/json"

    /**
     * 获取最新项目
     */
    const val NEW_LIST_PROJECT_PAGE_URL = "article/listproject/{page}/json"

    /**
     * 获取公众号分类
     */
    const val WX_ARTICLE_CHAPTER_URL = "wxarticle/chapters/json"

    /**
     * 获取公众号数据
     */
    const val WX_ARTICLE_LIST_ID_PAGE_URL = "wxarticle/list/{id}/{page}/json"

    /**
     * 获取热门搜索数据
     */
    const val HOT_KEY_URL = "hotkey/json"

    /**
     * 根据关键词查询
     */
    const val ARTICLE_QUERY_PAGE_URL = "article/query/{page}/json"

    /**
     * 广场列表数据
     */
    const val USER_ARTICLE_LIST_PAGE_URL = "user_article/list/{page}/json"

    /**
     * 每日一问列表
     */
    const val WENDA_LIST_PAGE_URL = "wenda/list/{page}/json"

    /**
     * 获取知识体系
     */
    const val KNOWLEDGE_TREE_URL = "tree/json"

    /**
     * 知识体系下的文章数据
     */
    const val ARTICLE_KNOWLEDGE_TREE_CHILD_LIST_URL = "article/list/{page}/json"

    /**
     * 获取导航数据
     */
    const val ARTICLE_NAVIGATION_URL = "navi/json"

    /**
     * 收藏文章
     */
    const val COLLECT_ID_URL = "lg/collect/{id}/json"

    /**
     * 取消收藏文章
     */
    const val UN_COLLECT_ID_URL = "lg/uncollect_originId/{id}/json"

    /**
     * 收藏网址
     */
    const val COLLECT_TOOL_URL = "lg/collect/addtool/json"

    /**
     * 取消收藏网址
     */
    const val UN_COLLECT_TOOL_URL = "lg/collect/deletetool/json"

    /**
     * 获取收藏文章列表
     */
    const val COLLECT_LIST_PAGE_URL = "lg/collect/list/{page}/json"

    /**
     * 获取收藏网址数据
     */
    const val COLLECT_USER_TOOL_URL = "lg/collect/usertools/json"

    /**
     * 获取他人分享文章列表数据
     */
    const val USER_ID_SHARE_ARTICLE_PAGE_URL = "user/{id}/share_articles/{page}/json"

    /**
     * 获取当前账户的个人积分
     */
    const val COIN_USER_INFO_URL = "lg/coin/userinfo/json"

    /**
     * 获取积分排行榜
     */
    const val COIN_RANK_PAGE_URL = "coin/rank/{page}/json"

    /**
     * 获取积分历史
     */
    const val HISTORY_COIN_LIST_PAGE_URL = "lg/coin/list/{page}/json"

    /**
     * 获取自己分享的文章列表数据
     */
    const val USER_PRIVATE_ARTICLES_URL = "user/lg/private_articles/{page}/json"

    /**
     * 删除自己分享的文章
     */
    const val USER_ARTICLE_DELETE_ID_URL = "lg/user_article/delete/{id}/json"

    /**
     * 添加文章
     */
    const val USER_ARTICLE_ADD_URL = "lg/user_article/add/json"

    /**
     * 获取Todo列表数据 根据完成时间排序
     */
    const val TODO_LIST_PAGE_URL = "/lg/todo/v2/list/{page}/json"

    /**
     * 添加一个TODO
     */
    const val ADD_TODO_URL = "lg/collect/{id}/json"

    /**
     * 修改一个TODO
     */
    const val UPDATE_TODO_URL = "lg/todo/update/{id}/json"

    /**
     * 删除一个TODO
     */
    const val DELETE_TODO_URL = "lg/todo/delete/{id}/json"

    /**
     * 完成一个TODO
     */
    const val DONE_TODO_URL = "lg/todo/done/{id}/json"
}