package com.tworld.tviewing.myVideo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mypage")
data class MyPageEntity(
    @ColumnInfo(name = "id") val videoId: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String
) {
    constructor() : this("", "", "", "")
    @PrimaryKey(autoGenerate = true) var primaryKey = 0
}


