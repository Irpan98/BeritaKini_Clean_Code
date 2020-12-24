package id.itborneo.core.utils.mapperUtils

import id.itborneo.core.data.model.News
import id.itborneo.core.data.source.local.entity.NewsEntity

object SingleMapper {
    fun newsToEntity(it: News): NewsEntity {

        return NewsEntity(
            title = it.title,

            publishedAt = it.publishedAt,
            author = it.author,
            urlToImage = it.urlToImage,
            description = it.description,
            sourceName = it.sourceName,
            sourceId = it.sourceId,
            id = it.id,

            url = it.url,
            content = it.content,
            isBookmarked = it.isBookmarked
        )
    }

    fun newsToModel(it: NewsEntity): News {

        return News(
            it.id,
            publishedAt = it.publishedAt,
            author = it.author,
            urlToImage = it.urlToImage,
            description = it.description,
            sourceName = it.sourceName,
            sourceId = it.sourceId,
            title = it.title,
            url = it.url,
            content = it.content,
            isBookmarked = it.isBookmarked

        )
    }
}
