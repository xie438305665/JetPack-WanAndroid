package com.zhixinhuixue.library.net.api

import com.zhixinhuixue.library.net.entity.*
import retrofit2.http.*

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
interface NetService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST(NetUrl.LOGIN_URL)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): BaseNetEntity<LoginEntity>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST(NetUrl.REGISTER_URL)
    suspend fun register(
        @Field("username") username: String,
        @Field("password") pwd: String,
        @Field("repassword") rpwd: String
    ): BaseNetEntity<Any?>

    /**
     * 获取banner数据
     */
    @GET(NetUrl.BANNER_URL)
    suspend fun getBanner(): BaseNetEntity<MutableList<BannerEntity>>

    /**
     * 获取置顶文章集合
     */
    @GET(NetUrl.ARTICLE_TOP_URL)
    suspend fun getArticleTopList(): BaseNetEntity<MutableList<ArticleEntity>>

    /**
     * 获取首页文章列表
     */
    @GET(NetUrl.ARTICLE_LIST_URL)
    suspend fun getArticleList(@Path("page") pageNo: Int): BaseNetEntity<ListNetEntity<MutableList<ArticleEntity>>>

    /**
     * 项目分类标题
     */
    @GET(NetUrl.PROJECT_TREE_URL)
    suspend fun getProjectTreeTitle(): BaseNetEntity<MutableList<ProjectTreeEntity>>

    /**
     * 通过分类cid获取项目
     */
    @GET(NetUrl.PROJECT_LIST_PAGE_URL)
    suspend fun getProjectByType(
        @Path("page") page: Int,
        @Query("cid") cid: String
    ): BaseNetEntity<ListNetEntity<MutableList<ArticleEntity>>>

    /**
     * 获取最新项目
     */
    @GET(NetUrl.NEW_LIST_PROJECT_PAGE_URL)
    suspend fun getNewProject(@Path("page") page: Int): BaseNetEntity<ListNetEntity<MutableList<ArticleEntity>>>

    /**
     * 公众号分类
     */
    @GET(NetUrl.WX_ARTICLE_CHAPTER_URL)
    suspend fun getWxArticleChapter(): BaseNetEntity<MutableList<ProjectTreeEntity>>

    /**
     * 获取公众号数据
     */
    @GET(NetUrl.WX_ARTICLE_LIST_ID_PAGE_URL)
    suspend fun getWxArticle(
        @Path("page") page: Int,
        @Path("id") id: String
    ): BaseNetEntity<ListNetEntity<MutableList<ArticleEntity>>>

    /**
     * 获取热门搜索数据
     */
    @GET(NetUrl.HOT_KEY_URL)
    suspend fun getHotSearch(): BaseNetEntity<MutableList<SearchEntity>>

    /**
     * 根据关键词搜索数据
     */
    @POST(NetUrl.ARTICLE_QUERY_PAGE_URL)
    suspend fun getArticleQueryByKey(
        @Path("page") pageNo: Int,
        @Query("k") searchKey: String
    ): BaseNetEntity<ListNetEntity<MutableList<ArticleEntity>>>

    /**
     * 获取广场列表数据
     */
    @GET(NetUrl.USER_ARTICLE_LIST_PAGE_URL)
    suspend fun getUserArticleList(@Path("page") page: Int): BaseNetEntity<ListNetEntity<MutableList<ArticleEntity>>>

    /**
     * 每日一问列表数据
     */
    @GET(NetUrl.WENDA_LIST_PAGE_URL)
    suspend fun getQuestionList(@Path("page") page: Int): BaseNetEntity<ListNetEntity<MutableList<ArticleEntity>>>


    /**
     * 收藏文章
     */
    @POST(NetUrl.COLLECT_ID_URL)
    suspend fun collect(@Path("id") id: Int): BaseNetEntity<Any?>

    /**
     * 取消收藏文章
     */
    @POST(NetUrl.UN_COLLECT_ID_URL)
    suspend fun unCollect(@Path("id") id: Int): BaseNetEntity<Any?>

    /**
     * 收藏网址
     */
    @POST(NetUrl.COLLECT_TOOL_URL)
    suspend fun collectTool(
        @Query("name") name: String,
        @Query("link") link: String
    ): BaseNetEntity<CollectToolEntity>

    /**
     * 取消收藏网址
     */
    @POST(NetUrl.UN_COLLECT_TOOL_URL)
    suspend fun unCollectTool(@Query("id") id: Int): BaseNetEntity<Any?>

    /**
     * 获取收藏文章列表
     */
    @GET(NetUrl.COLLECT_LIST_PAGE_URL)
    suspend fun getCollectList(@Path("page") pageNo: Int): BaseNetEntity<ListNetEntity<MutableList<CollectToolEntity>>>

    /**
     * 获取收藏网址数据
     */
    @GET(NetUrl.COLLECT_USER_TOOL_URL)
    suspend fun getCollectUserTool(): BaseNetEntity<MutableList<CollectToolEntity>>

    /**
     * 获取他人分享文章列表数据
     */
    @GET(NetUrl.USER_ID_SHARE_ARTICLE_PAGE_URL)
    suspend fun getUserIdShareArticle(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): BaseNetEntity<ShareEntity>

    /**
     * 获取当前账户的个人积分
     */
    @GET(NetUrl.COIN_USER_INFO_URL)
    suspend fun getCoinUserInfo(): BaseNetEntity<IntegralEntity>

    /**
     * 获取自己分享的文章列表数据
     */
    @GET(NetUrl.USER_PRIVATE_ARTICLES_URL)
    suspend fun getUserShardArticles(@Path("page") page: Int): BaseNetEntity<ShareEntity>

    /**
     *  删除自己分享的文章
     */
    @DELETE(NetUrl.USER_ARTICLE_DELETE_ID_URL)
    suspend fun deleteUserShareArticle(@Path("id") id: Int): BaseNetEntity<Any?>

    /**
     * 添加文章
     */
    @POST(NetUrl.USER_ARTICLE_ADD_URL)
    @FormUrlEncoded
    suspend fun addArticle(
        @Field("title") title: String,
        @Field("link") content: String
    ): BaseNetEntity<Any?>
}