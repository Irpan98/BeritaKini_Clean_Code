package id.itborneo.core.domain.repository

import id.itborneo.core.data.Resource
import id.itborneo.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface IBeritaKiniRepository {

    fun getAllNews(): Flow<Resource<List<NewsEntity>>>

    fun updateBookmark(news: NewsEntity)

    fun searchNews(query: String): Flow<Resource<List<NewsEntity>>>
}