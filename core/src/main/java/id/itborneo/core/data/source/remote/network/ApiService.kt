package id.itborneo.core.data.source.remote.network

import id.itborneo.core.utils.constant.API_KEY
import id.itborneo.core.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET("top-headlines?country=id&apiKey=$API_KEY")
    suspend fun getIDNews(): NewsResponse


    @GET("everything?apiKey=$API_KEY&totalResults=5")
    suspend fun searchNews(@Query("q") query: String): NewsResponse


}
