package id.itborneo.core.domain.useCase

import id.itborneo.core.data.Resource
import id.itborneo.core.data.source.local.entity.NewsEntity
import id.itborneo.core.domain.repository.IBeritaKiniRepository
import kotlinx.coroutines.flow.Flow

class BeritaKiniInteractor(private val repository: IBeritaKiniRepository) : BeritaKiniUseCase {
    override fun getAllNews(): Flow<Resource<List<NewsEntity>>> =
        repository.getAllNews()

    override fun updateBookmark(newsEntity: NewsEntity, newState: Boolean) {
        newsEntity.isBookmarked = newState
        repository.updateBookmark(newsEntity)
    }

    override fun searchNews(query: String): Flow<Resource<List<NewsEntity>>> =
        repository.searchNews(query)


}