package com.menezes.teammanager.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.menezes.teammanager.data.db.dao.PlayerDAO
import com.menezes.teammanager.data.db.entity.PlayerEntity

@Database(entities = [PlayerEntity::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val playerDAO: PlayerDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
                }
                return instance
            }
        }
    }
}