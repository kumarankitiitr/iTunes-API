package `in`.programy.itunesapi.model.retrofit

import `in`.programy.itunesapi.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api by lazy {
        retrofit.create(
            TunesApi::class.java)
    }
}