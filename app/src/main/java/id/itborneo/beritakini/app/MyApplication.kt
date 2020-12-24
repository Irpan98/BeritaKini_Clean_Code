package id.itborneo.beritakini.app

import android.app.Application
import id.itborneo.core.di.databaseModule
import id.itborneo.core.di.networkModule
import id.itborneo.core.di.repositoryModule
import id.itborneo.beritakini.di.useCaseModule
import id.itborneo.beritakini.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}