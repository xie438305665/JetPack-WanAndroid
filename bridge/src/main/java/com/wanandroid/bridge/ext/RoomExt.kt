package com.wanandroid.bridge.ext

import com.wanandroid.bridge.annotation.AnnotationValue
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.util.GsonUtils
import com.wanandroid.bridge.util.SpUtils
import com.wanandroid.room.DbDatabase
import com.wanandroid.room.entity.HistoryEntity
import com.zhixinhuixue.library.net.entity.UserInfoEntity


/**
 * 查询搜索记录
 * @return HistoryEntity?
 */
fun queryHistory(): HistoryEntity? {
    val userInfoEntity = getUserInfo()
    userInfoEntity ?: return null
    val historyDao = DbDatabase.getDatabase(appContext).historyDao()
    return historyDao.queryHistory(userInfoEntity.userName)
}

/**
 * 更新搜索记录
 * @return HistoryEntity?
 */
fun updateHistory(historyEntity: HistoryEntity) {
    val userInfoEntity = getUserInfo()
    userInfoEntity ?: return
    val historyDao = DbDatabase.getDatabase(appContext).historyDao()
    return historyDao.updateHistory(historyEntity)
}

/**
 * 刪除搜索记录
 * @return HistoryEntity?
 */
fun deleteHistory() {
    val userInfoEntity = getUserInfo()
    userInfoEntity ?: return
    val historyDao = DbDatabase.getDatabase(appContext).historyDao()
    return historyDao.deleteHistory(userInfoEntity.userName)
}

/**
 * 获取用户信息
 * @return UserInfoEntity?
 */
fun getUserInfo(): UserInfoEntity? {
    return GsonUtils.toClazz(
        SpUtils.getValue(AnnotationValue.SP_KEY_USER_INFO, ""),
        UserInfoEntity::class.java
    )
}

