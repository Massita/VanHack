package com.massita.vanhack.model.api

import android.util.Log
import com.massita.vanhack.model.data.JobsResponse
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface JobApi {

    @GET("v1/job/search/full/")
    fun getJobs(@Query("MaxResultCount") maxResultCount: Int,
                @Query("SkipCount") skipCount: Int,
                @Query("query") searchQuery: String?,
                @Query("experiencelevels", encoded = true) experienceLevels: String?,
                @Query("countries", encoded = true) countries: String?,
                @Query("cities", encoded = true) cities: String?) : Observable<JobsResponse>

    // Request URL: https://app.vanhack.com/JobByUser/ApplyNow?idJob=1348
//Request Method: POST
//Status Code: 200 OK

    companion object {
        private const val BASE_URL = "https://api-vanhack.azurewebsites.net/"

        fun create() : JobApi = create(HttpUrl.parse(BASE_URL)!!)

        private fun create(baseUrl: HttpUrl): JobApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            // Criação do client do OkHttp, contendo regras de cache e log
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            // Criação do client retrofit, utilizando URL base, client okhttp criado anteriormente
            // conversor de json para objeto, adaptador de chamadas para uso do RXJava
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(JobApi::class.java)
        }
    }

}