package com.jongman22.study1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "namedata")
data class NameData(
    @PrimaryKey(autoGenerate = true) var uid: Long = 0
    ,@ColumnInfo(name = "firstName") var FirstName: String = ""
    ,@ColumnInfo(name = "lastName") var LastName: String = ""
)