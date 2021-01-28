package com.example.diaryinmyhand

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="data")
class Data
    (@PrimaryKey(autoGenerate = true) var id:Long?,
     @ColumnInfo(name="datatitle") var dataTitle:String?,
     @ColumnInfo(name="dataimage") var dataImage:Int,
     @ColumnInfo(name="datacontent") var dataContent:String
){
        constructor():this(null,"",0,"")

}