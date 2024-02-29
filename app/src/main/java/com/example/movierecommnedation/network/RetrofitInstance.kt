package com.example.movierecommnedation.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://fbc1-2804-14c-32-96ab-4064-61ec-bb49-b379.ngrok-free.app"  // Your ngrok URL

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // Adjust the timeout as needed
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(java: Class<MovieRecommendationService>): MovieRecommendationService {
        return retrofit.create(MovieRecommendationService::class.java)
    }
}
