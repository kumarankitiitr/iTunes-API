package `in`.programy.itunesapi.repository

import `in`.programy.itunesapi.model.retrofit.RetrofitInstance.api
import `in`.programy.itunesapi.model.room.Item
import `in`.programy.itunesapi.model.room.TunesDatabase


class TunesRepository(private val database: TunesDatabase) {
    suspend fun insert(item: Item) = database.getItemDao().insert(item)

    suspend fun getOfflineItems(artist: String) = database.getItemDao().getOfflineItems(artist)

    suspend fun getOnlineItems(artist: String) = api.getOnlineItems(artist)
}