package id.itborneo.core.data.source.local

import id.itborneo.core.data.source.local.entity.NewsEntity
import id.itborneo.core.data.source.local.room.BeritaKiniDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: BeritaKiniDao) {

    fun getAllNews(): Flow<List<NewsEntity>> = dao.getAllTourism()

    suspend fun insertAllNews(newsList: List<NewsEntity>) = dao.insertAllNews(newsList)

    fun updateBookmark(news: NewsEntity) = dao.insertBookmark(news)
}