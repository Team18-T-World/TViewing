package com.tworld.tviewing.data

data class MyVideoItems(
    val videoUri: String,
    var title: String,
    var thumbnail: String,
    var content: String,
    var isLike: Boolean
)