package com.mad_pagination.network

import com.mad_pagination.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager {

    private const val BASE_URL = "https://5b5cb0546a725000148a67ab.mockapi.io/"

    private fun getRetrofit(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val okHttpClient = OkHttpClient.Builder().apply {
            val timeOut: Long = 30 * 1000
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
            }
            connectTimeout(timeOut, TimeUnit.MILLISECONDS)
            readTimeout(timeOut, TimeUnit.MILLISECONDS)
            writeTimeout(timeOut, TimeUnit.MILLISECONDS)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}