package com.example.diaryinmyhand

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface DataDao{
    @Query("SELECT * FROM data")
    fun getAll():List<Data>

    @Insert(onConflict=REPLACE)
    fun insert(data:Data)

    @Query("DELETE from data")
    fun deleteAll()
}