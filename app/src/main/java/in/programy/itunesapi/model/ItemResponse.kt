package `in`.programy.itunesapi.model

import `in`.programy.itunesapi.model.room.Item

data class ItemResponse(
    val resultCount: Int,
    val results: List<Item>
)