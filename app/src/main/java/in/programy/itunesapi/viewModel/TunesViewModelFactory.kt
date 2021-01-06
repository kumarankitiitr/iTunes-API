package `in`.programy.itunesapi.viewModel

import `in`.programy.itunesapi.repository.TunesRepository
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TunesViewModelFactory(private val repository: TunesRepository,private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TunesViewModel(repository,application) as T
    }
}