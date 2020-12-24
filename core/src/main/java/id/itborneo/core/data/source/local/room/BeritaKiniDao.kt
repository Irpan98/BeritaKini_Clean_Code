package id.itborneo.core.data.source.local.room

import androidx.room.*
import id.itborneo.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface BeritaKiniDao {

    @Query("SELECT * FROM newsEntity")
    fun getAllTourism(): Flow<List<NewsEntity>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllNews(newsList: List<NewsEntity>)

    @Update
    fun insertBookmark(favorite: NewsEntity)


}