package `in`.programy.itunesapi.viewModel

import `in`.programy.itunesapi.model.ItemResponse
import `in`.programy.itunesapi.model.room.Item
import `in`.programy.itunesapi.repository.TunesRepository
import `in`.programy.itunesapi.util.Resource
import `in`.programy.itunesapi.util.TunesApplication
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

class TunesViewModel(
    private val repository: TunesRepository,
    application: Application
): AndroidViewModel(application) {
    private val context = getApplication<TunesApplication>()

    val currentItems: MutableLiveData<Resource<List<Item>>> = MutableLiveData()

    fun getItems(artist: String){
        currentItems.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) getOnlineItems(artist)
            else getOfflineItems(artist)
        }
        catch (t: Throwable){
            t.printStackTrace()
            when(t){
                is IOException -> currentItems.postValue(Resource.Error("Network Failure"))
                else -> currentItems.postValue(Resource.Error("Some Error Occurred"))
            }
        }
    }

    private fun getOfflineItems(artist: String){
        viewModelScope.launch {
            val list = repository.getOfflineItems(artist)
            postItems(list)
        }
    }

    private fun getOnlineItems(artist: String){
        viewModelScope.launch {
            val response = repository.getOnlineItems(artist)
            if(response.isSuccessful){
                val list = response.body()?.results
                list?.let {
                    postItems(list)
                    saveItems(list)
                }
            }
        }
    }

    private fun postItems(list: List<Item>){
        if(list.isNotEmpty()) currentItems.postValue(Resource.Success(list))
        else {
            currentItems.postValue(Resource.Error("No Results Found"))
        }
    }

    private fun saveItems(list: List<Item>){
        viewModelScope.launch {
            for (i in list) repository.insert(i)
        }
    }

    private fun hasInternetConnection(): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
            return false
        }
    }
}