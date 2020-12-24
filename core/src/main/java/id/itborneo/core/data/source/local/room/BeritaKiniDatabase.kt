package id.itborneo.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.itborneo.core.data.source.local.entity.NewsEntity

@Database(
    entities = [
        NewsEntity::class,
//        FavoriteEntity::class,
//        SourceEntity::class
    ],
    version = 1, exportSchema = false
)
abstract class BeritaKiniDatabase : RoomDatabase() {
    abstract fun beritaKiniDao(): BeritaKiniDao
}
