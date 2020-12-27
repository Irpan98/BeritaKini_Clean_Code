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
import okhttp3.CertificatePinner
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

    val hostname = "newsapi.org"
    val certificatePinner = CertificatePinner.Builder()
        .add(hostname, "sha256/LAlZB272xQABCgeTFXzq0MuyQTFpu4lb7LOBjVoJdrE=")
        .add(hostname, "sha256/c5XTqkOxoXqc60M3HuT9fgmfeexiP2+Q8BD3+6abZYU=")
        .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
        .add(hostname, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")



        .build()


    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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