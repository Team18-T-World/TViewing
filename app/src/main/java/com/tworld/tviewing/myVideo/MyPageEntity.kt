package com.tworld.tviewing.myVideo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mypage")
data class MyPageEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    @ColumnInfo(name = "thumbnail")
    var thumbnail_text: String,

    @ColumnInfo(name = "title")
    var title_text: String
) {
    constructor() : this(id = null, "", "")

}


