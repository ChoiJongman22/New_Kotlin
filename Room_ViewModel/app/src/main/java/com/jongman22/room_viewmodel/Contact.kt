package com.jongman22.room_viewmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//table이름을 정해주고
@Entity(tableName = "contact")
data class Contact(
    //primarykey는 기본키가 되는 id,생성자에 null로 해놓고 자동으로 생성.
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "number") var number: String,
    @ColumnInfo(name = "initial") var initial: Char
) {
    constructor() : this(null, "", "", '\u0000')
}