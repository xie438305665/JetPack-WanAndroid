package com.zhixinhuixue.library.net.entity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/8/13
 **/
data class ShardEntity(
    var shareArticles: ListNetEntity<MutableList<ArticleEntity>>
)