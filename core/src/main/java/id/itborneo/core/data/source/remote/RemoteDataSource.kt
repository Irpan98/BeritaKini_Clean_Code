package id.itborneo.core.data.source.remote

import id.itborneo.core.data.source.remote.network.ApiResponse
import id.itborneo.core.data.source.remote.network.ApiService
import id.itborneo.core.utils.test.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllNews() =
        flow {
            EspressoIdlingResource.increment()

            try {

                val response = apiService.getIDNews()

                val articles = response.articles
                if (!articles.isNullOrEmpty()) {
                    EspressoIdlingResource.decrement()

                    emit(ApiResponse.Success(articles))

                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)


    suspend fun searchNews(query: String) =
        flow {
            try {

                val response = apiService.searchNews(query)

                val articles = response.articles
                if (!articles.isNullOrEmpty()) {

                    emit(ApiResponse.Success(articles))

                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}