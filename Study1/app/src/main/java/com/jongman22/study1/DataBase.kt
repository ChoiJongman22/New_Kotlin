package com.jongman22.study1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NameData::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun nameDao(): NameDao

    //인스턴스 없이 클래스 내부에 진입하고 싶을 때 companion object를 사용한다.
    companion object {
        private var instance: DataBase? = null

        //중복 방지
        @Synchronized
        fun getInstance(context: Context): DataBase? {
            if (instance == null) {
                instance= Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "namedata"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }

    }
}