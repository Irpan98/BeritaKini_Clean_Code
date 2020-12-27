package id.itborneo.core.di

import androidx.room.Room
import id.itborneo.core.data.BeritaKiniRepository
import id.itborneo.core.data.source.local.LocalDataSource
import id.itborneo.core.data.source.local.room.BeritaKiniDatabase
import id.itborneo.core.data.source.remote.RemoteDataSource
import id.itborneo.core.data.source.remote.network.ApiService
import id.itborneo.core.domain.repository.IBeritaKiniRepository
import id.itborneo.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<BeritaKiniDatabase>().beritaKiniDao() }
    single {

        val passphrase: ByteArray = SQLiteDatabase.getBytes("beritakini".toCharArray())
        val factory = SupportFactory(passphrase)


        Room.databaseBuilder(
            androidContext(),
            BeritaKiniDatabase::class.java, "BeritaKini.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}


val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }


}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IBeritaKiniRepository> {
        BeritaKiniRepository(
            get(),
            get(),
            get()
        )
    }
}