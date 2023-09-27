package com.tworld.tviewing.search.data


import com.google.gson.annotations.SerializedName

data class Item(
    var etag: String,
    var id: Id,
    var kind: String,
    var snippet: Snippet
)