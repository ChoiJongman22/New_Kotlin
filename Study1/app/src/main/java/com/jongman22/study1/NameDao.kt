package com.jongman22.study1

import androidx.room.*

@Dao
interface NameDao {
    @Query("SELECT * FROM namedata")
    fun getAll():List<NameData>
    @Query("SELECT * FROM namedata WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds:IntArray):List<NameData>
    @Query("SELECT * FROM namedata WHERE firstname LIKE :first AND " + "lastname LIKE :last LIMIT 1")
    fun findByName(first:String,last:String):NameData
    @Insert
    fun insertAll(vararg name:NameData)
    @Update
    fun updateAll(vararg name:NameData)
    @Delete
    fun delete(name:NameData)
}