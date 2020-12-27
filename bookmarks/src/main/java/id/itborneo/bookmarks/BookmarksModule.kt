package id.itborneo.bookmarks

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val BookmarksModule = module {
    viewModel {
        BookmarksViewModel(get())
    }
}

