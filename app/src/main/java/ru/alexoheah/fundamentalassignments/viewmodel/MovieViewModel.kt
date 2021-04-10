package ru.alexoheah.fundamentalassignments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexoheah.fundamentalassignments.MovieApp
import ru.alexoheah.fundamentalassignments.data.JsonMovieRepository
import ru.alexoheah.fundamentalassignments.model.Movie

class MovieViewModel: ViewModel() {

    private var movie: Movie? = null
    private val liveMovie = MutableLiveData<Movie>()

    fun loadMovie(movieId: Int?) {
        viewModelScope.launch {
            movie = JsonMovieRepository(MovieApp.instance).loadMovie(movieId?:0)
            liveMovie.value = movie
        }
    }

    fun getMovie(): LiveData<Movie> = liveMovie
}