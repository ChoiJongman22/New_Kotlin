package com.jongman22.room_viewmodel

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    //@어노테이션 이름
    //SELECT * FROM 테이블이름 명령
    //함수
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll():LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //onconflict는 중복을 제거 해줄 수 있다.
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}