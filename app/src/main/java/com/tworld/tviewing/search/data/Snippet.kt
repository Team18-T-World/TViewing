package com.tworld.tviewing.search.data


import com.google.gson.annotations.SerializedName

data class Snippet(
    var channelId: String,
    var channelTitle: String,
    var description: String,
    var liveBroadcastContent: String,
    var publishedAt: String,
    var thumbnails: Thumbnails,
    var title: String
)