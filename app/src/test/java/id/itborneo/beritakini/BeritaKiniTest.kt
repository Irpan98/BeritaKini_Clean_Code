package id.itborneo.beritakini

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import id.itborneo.core.data.Resource
import id.itborneo.core.data.source.local.entity.NewsEntity
import id.itborneo.core.domain.useCase.BeritaKiniInteractor
import id.itborneo.core.viewModel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BeritaKiniTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: BeritaKiniInteractor


    @Before
    fun setUp() {

        viewModel = MainViewModel(useCase)
    }


    @Test
    fun beritaKiniTest() {

        val dummyData = MutableLiveData<Resource<List<NewsEntity>>>()
        Mockito.`when`(useCase.getAllNews()).thenReturn(dummyData.asFlow())

        //make sure, viewModel getAllNews() can be called
        Assert.assertNotNull(viewModel.getAllNews())

    }
}