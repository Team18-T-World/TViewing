package com.tworld.tviewing.data

data class MyVideoItems(
    val videoUri: String,
    var title: String,
    var content: String,
    var hits: String,
    var isLike: Boolean
)