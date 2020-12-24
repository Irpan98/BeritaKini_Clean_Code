package id.itborneo.core.domain.useCase

import id.itborneo.core.data.Resource
import id.itborneo.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface BeritaKiniUseCase {
    fun getAllNews(): Flow<Resource<List<NewsEntity>>>
    fun updateBookmark(newsEntity: NewsEntity, newState: Boolean)
    fun searchNews(query: String): Flow<Resource<List<NewsEntity>>>
}