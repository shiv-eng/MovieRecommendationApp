package com.example.movierecommnedation.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRecommendationService {
    @GET("/recommend_by_genre")
    suspend fun getRecommendationsByGenre(@Query("genre") genre: String): Response<List<String>>
}