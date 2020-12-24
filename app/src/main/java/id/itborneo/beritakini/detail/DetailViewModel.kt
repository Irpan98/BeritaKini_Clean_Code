package id.itborneo.beritakini.detail

import androidx.lifecycle.ViewModel
import id.itborneo.core.data.source.local.entity.NewsEntity
import id.itborneo.core.domain.useCase.BeritaKiniUseCase

class DetailViewModel(private val useCase: BeritaKiniUseCase) : ViewModel() {

    fun updateBookmark(favorite: NewsEntity, newState: Boolean) =
        useCase.updateBookmark(favorite, newState)


}