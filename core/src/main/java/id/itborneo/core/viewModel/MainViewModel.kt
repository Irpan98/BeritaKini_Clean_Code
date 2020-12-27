package id.itborneo.core.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.itborneo.core.data.Resource
import id.itborneo.core.data.source.local.entity.NewsEntity
import id.itborneo.core.domain.useCase.BeritaKiniUseCase

class MainViewModel(private val useCase: BeritaKiniUseCase) : ViewModel() {

    private lateinit var listNews: LiveData<Resource<List<NewsEntity>>>

    fun getAllNews(): LiveData<Resource<List<NewsEntity>>> {

//        Log.d("MainViewModel", "getAllnews" + ::listNews.isInitialized)
        return if (::listNews.isInitialized) {
            listNews
        } else {

            listNews = useCase.getAllNews().asLiveData()
            useCase.getAllNews().asLiveData()
        }
    }

    fun searchNews(query: String) = useCase.searchNews(query).asLiveData()


}