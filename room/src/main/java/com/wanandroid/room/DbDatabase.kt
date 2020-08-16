package com.wanandroid.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wanandroid.room.dao.HistoryDao
import com.wanandroid.room.entity.HistoryEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/8/16
 **/
@Database(version = 1, entities = [HistoryEntity::class])
abstract class DbDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        private var instances: DbDatabase? = null
        private var isUpdateDb = false

        @Synchronized
        fun getDatabase(context: Context): DbDatabase {
            instances?.let {
                return it
            }
            val builder = Room.databaseBuilder(
                context.applicationContext,
                DbDatabase::class.java,
                "wanandroid_db"
            ).allowMainThreadQueries()
            if (isUpdateDb) {
                builder.addMigrations(MIGRATION)
            }
            return builder.build().apply {
                instances = this
            }
        }

        /**
         * 数据库升级SQL语句
         */
        private val MIGRATION = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("")
            }
        }
    }
}