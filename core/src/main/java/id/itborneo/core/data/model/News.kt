package id.itborneo.core.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(

    var id: Int,
    val publishedAt: String = "",
    val author: String = "",
    val urlToImage: String = "",
    val description: String = "",
    val title: String = "",
    val url: String = "",
    val content: String = "",
    var sourceName: String = "",
    val sourceId: String = "",
    var isBookmarked: Boolean = false,

    ) : Parcelable