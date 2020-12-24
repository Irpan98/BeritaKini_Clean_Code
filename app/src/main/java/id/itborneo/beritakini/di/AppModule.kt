package id.itborneo.beritakini.di

import id.itborneo.beritakini.detail.DetailViewModel
import id.itborneo.core.domain.useCase.BeritaKiniInteractor
import id.itborneo.core.domain.useCase.BeritaKiniUseCase
import id.itborneo.core.viewModel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<BeritaKiniUseCase> {
        BeritaKiniInteractor(get())
    }
}
val viewModelModule = module {
    //2 fragment user
    single {
        MainViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }

}