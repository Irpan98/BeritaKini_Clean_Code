package id.itborneo.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "newsEntity")
data class NewsEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    val title: String = "",


    @ColumnInfo(name = "publishedAt")
    val publishedAt: String = "",

    @ColumnInfo(name = "author")
    val author: String = "",

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String = "",

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "url")
    val url: String = "",

    @ColumnInfo(name = "content")
    val content: String = "",

    @ColumnInfo(name = "name_source")
    var sourceName: String = "",

    @ColumnInfo(name = "id_source")
    val sourceId: String = "",

    @ColumnInfo(name = "is_bookmarked")
    var isBookmarked: Boolean = false

)
