package com.example.diaryinmyhand

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Data::class], version = 1)
abstract class DataDB : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        private var INSTANCE: DataDB? = null
        fun getInstance(context: Context): DataDB? {
            if (INSTANCE == null) {
                synchronized(DataDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataDB::class.java, "data.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}