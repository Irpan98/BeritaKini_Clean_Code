package id.itborneo.core.utils.mapperUtils

import id.itborneo.core.data.model.News
import id.itborneo.core.data.source.local.entity.NewsEntity
import id.itborneo.core.data.source.remote.response.NewsItemResponse

object DataMapper {
    fun newsEntityToModel(results: List<NewsEntity>): MutableList<News> {
        val allNews = mutableListOf<News>()

        results.forEach { news ->
            val getNews = SingleMapper.newsToModel(news)
            allNews.add(getNews)
        }
        return allNews
    }

    fun newsResponsesToEntities(input: List<NewsItemResponse>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()


        input.map {
            val tourism = NewsEntity(
                title = it.title ?: "",
                id = 0,
                publishedAt = it.publishedAt ?: "",
                author = it.author ?: "",
                urlToImage = it.urlToImage ?: "",
                description = it.description ?: "",
                sourceName = it.source?.name ?: "",
                sourceId = it.source?.id ?: "",
                url = it.url ?: "",
                content = it.content ?: ""
            )
            newsList.add(tourism)
        }
        return newsList
    }

//    fun favoriteEntitiesToModel(input: List<FavoriteEntity>): List<Favorite> {
//        val newsList = ArrayList<Favorite>()
//
//        input.map {
//            val tourism = Favorite(
//                0,
//                publishedAt = it.publishedAt,
//                author = it.author,
//                urlToImage = it.urlToImage,
//                description = it.description,
//                sourceName = it.sourceName,
//                sourceId = it.sourceId,
//                title = it.title,
//                url = it.url,
//                content = it.content
//            )
//            newsList.add(tourism)
//        }
//        return newsList
//    }


}