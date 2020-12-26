package id.itborneo.core.data

import id.itborneo.core.data.source.local.LocalDataSource
import id.itborneo.core.data.source.local.entity.NewsEntity
import id.itborneo.core.data.source.remote.RemoteDataSource
import id.itborneo.core.data.source.remote.network.ApiResponse
import id.itborneo.core.data.source.remote.response.NewsItemResponse
import id.itborneo.core.domain.repository.IBeritaKiniRepository
import id.itborneo.core.utils.AppExecutors
import id.itborneo.core.utils.mapperUtils.DataMapper
import id.itborneo.core.utils.test.EspressoIdlingResource
import kotlinx.coroutines.flow.Flow

class BeritaKiniRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors

) : IBeritaKiniRepository {

    override fun getAllNews(): Flow<Resource<List<NewsEntity>>> {

        return object : NetworkBoundResource<List<NewsEntity>, List<NewsItemResponse>>() {
            override fun loadFromDB(): Flow<List<NewsEntity>> {

                return localDataSource.getAllNews()

            }

            override fun shouldFetch(data: List<NewsEntity>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<NewsItemResponse>>> =
                remoteDataSource.getAllNews()

            override suspend fun saveCallResult(data: List<NewsItemResponse>) {
                val newsList = DataMapper.newsResponsesToEntities(data)
                localDataSource.insertAllNews(newsList)
            }

        }.asFlow()
    }

    override fun updateBookmark(news: NewsEntity) =
        appExecutors.diskIO().execute { localDataSource.updateBookmark(news) }

    override fun searchNews(query: String) =
        object :
            NetworkBoundResource<List<NewsEntity>, List<NewsItemResponse>>() {
            override fun loadFromDB(): Flow<List<NewsEntity>> =
                localDataSource.getAllNews()


            override fun shouldFetch(data: List<NewsEntity>?): Boolean =
                true


            override suspend fun createCall(): Flow<ApiResponse<List<NewsItemResponse>>> =
                remoteDataSource.searchNews(query)


            override suspend fun saveCallResult(data: List<NewsItemResponse>) {
                val newsList = DataMapper.newsResponsesToEntities(data)
                localDataSource.insertAllNews(newsList)
            }

        }.asFlow()


}