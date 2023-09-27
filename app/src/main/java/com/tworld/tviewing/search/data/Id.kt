package com.tworld.tviewing.search.data


import com.google.gson.annotations.SerializedName

data class Id(
    var channelId: String,
    var kind: String,
    var playlistId: String,
    var videoId: String
)