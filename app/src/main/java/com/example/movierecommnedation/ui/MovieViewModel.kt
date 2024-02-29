package com.example.movierecommnedation.ui

import androidx.lifecycle.ViewModel
import com.example.movierecommnedation.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException

class MovieViewModel(private val repository: MovieRepository = MovieRepository()) : ViewModel() {

    private val _recommendations = MutableStateFlow<List<String>>(emptyList())
    val recommendations: StateFlow<List<String>> get() = _recommendations.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error.asStateFlow()

    suspend fun getRecommendationsByGenre(genre: String) {
        try {
            _recommendations.value = emptyList()
            val response = repository.getRecommendationsByGenre(genre)
            if (response.isSuccessful) {
                _recommendations.value = response.body() ?: emptyList()
            } else {
                _error.value = "Error: ${response.code()} ${response.message()}"
            }
        } catch (e: IOException) {
            _error.value = "Network error. Please check your connection."
        } catch (e: Exception) {
            _error.value = "An unexpected error occurred. Please try again."
        }
    }

    fun clearError() {
        _error.value = null
    }
}
