package `in`.programy.itunesapi.model.retrofit

import `in`.programy.itunesapi.model.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TunesApi {
    @GET("/search")
    suspend fun getOnlineItems(
        @Query("term") artistName: String
    ): Response<ItemResponse>
}