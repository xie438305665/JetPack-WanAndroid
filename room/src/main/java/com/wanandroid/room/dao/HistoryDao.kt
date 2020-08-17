package com.wanandroid.room.dao

import androidx.room.*
import com.wanandroid.room.entity.HistoryEntity

/**
 *  @description: 搜索历史纪录
 *  @author xcl qq:244672784
 *  @Date 2020/8/16
 **/
@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(historyEntity: HistoryEntity)

    @Update
    fun updateHistory(historyEntity: HistoryEntity)

    @Delete
    fun deleteHistory(historyEntity: HistoryEntity)

    @Query("select * from HistoryEntity where userName == :userName")
    fun queryHistory(userName: String): HistoryEntity?

    @Query("delete from HistoryEntity where userName = :userName ")
    fun deleteHistory(userName: String)

    @Query("update  HistoryEntity  set  historyValue = :historyValue  where userName=:userName")
    fun updateHistory(userName: String, historyValue: MutableList<String>)
}

