package com.massita.vanhack.presentation.api

import android.util.Log
import com.google.gson.GsonBuilder
import com.massita.vanhack.presentation.data.JobsResponse
import com.massita.vanhack.utils.DateTypeDeserializer
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface JobApi {

    @GET("v1/job/search/full/")
    fun getJobs(@Query("MaxResultCount") maxResultCount: Int,
                @Query("SkipCount") skipCount: Int,
                @Query("query") searchQuery: String?,
                @Query("experiencelevels", encoded = true) experienceLevels: String?,
                @Query("countries", encoded = true) countries: String?,
                @Query("cities", encoded = true) cities: String?) : Call<JobsResponse>

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

            val gson = GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateTypeDeserializer())
                .create()

            // Criação do client retrofit, utilizando URL base, client okhttp criado anteriormente
            // conversor de json para objeto, adaptador de chamadas para uso do RXJava
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(JobApi::class.java)
        }
    }

}