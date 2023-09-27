package com.tworld.tviewing.search.data


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    var etag: String,
    var items: List<Item>,
    var kind: String,
    var nextPageToken: String,
    var pageInfo: PageInfo,
    var prevPageToken: String,
    var regionCode: String
)