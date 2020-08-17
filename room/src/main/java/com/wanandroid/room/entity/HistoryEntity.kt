package com.wanandroid.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.wanandroid.room.StringTypeConverter
import org.jetbrains.annotations.NotNull

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/8/16
 **/
@Entity(tableName = "historyEntity")
@TypeConverters(StringTypeConverter::class)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @NotNull
    var userName: String,
    var historyValue: MutableList<String>
)