package com.example.movierecommnedation.data

import com.example.movierecommnedation.network.MovieRecommendationService
import com.example.movierecommnedation.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

class MovieRepository {

    private val service: MovieRecommendationService by lazy {
        RetrofitInstance.create(MovieRecommendationService::class.java)
    }

    suspend fun getRecommendationsByGenre(genre: String): Response<List<String>> {
        return try {
            withContext(Dispatchers.IO) {
                service.getRecommendationsByGenre(genre.trim().toLowerCase())
            }
        } catch (e: IOException) {
            Response.error(500, ResponseBody.create(null, "Network error. Please check your connection."))
        } catch (e: Exception) {
            Response.error(500, ResponseBody.create(null, "An unexpected error occurred. Please try again."))
        }
    }
}
