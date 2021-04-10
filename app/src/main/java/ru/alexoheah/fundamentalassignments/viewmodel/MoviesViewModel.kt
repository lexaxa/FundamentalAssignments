package ru.alexoheah.fundamentalassignments.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexoheah.fundamentalassignments.MovieApp
import ru.alexoheah.fundamentalassignments.data.JsonMovieRepository
import ru.alexoheah.fundamentalassignments.data.MovieRepository
import ru.alexoheah.fundamentalassignments.model.Movie

class MoviesViewModel: ViewModel() {

    private val movies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>().also{
            loadMovies()
        }
    }

    private fun loadMovies(): LiveData<List<Movie>> {
        viewModelScope.launch {
            movies.value = JsonMovieRepository(MovieApp.instance).loadMovies()
        }
        return MutableLiveData<List<Movie>>().also {
            CoroutineScope(Dispatchers.IO).launch {
                JsonMovieRepository(MovieApp.instance).loadMovies()
            }
        }
    }

    fun getMovies(): LiveData<List<Movie>> = movies

    class Factory(val movieId: Int?): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                return MovieViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}
