package ru.alexoheah.fundamentalassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.alexoheah.fundamentalassignments.adapters.MovieAdapter
import ru.alexoheah.fundamentalassignments.data.JsonMovieRepository
import ru.alexoheah.fundamentalassignments.model.Movie
import ru.alexoheah.fundamentalassignments.utils.DataUtil
import ru.alexoheah.fundamentalassignments.viewmodel.MoviesViewModel

class FragmentMoviesList: Fragment(R.layout.fragment_movies_list), MovieListListener {

    private val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var viewModel: MoviesViewModel // by activityViewModel()
    private var liveMovies: LiveData<List<Movie>>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = view.findViewById<RecyclerView>(R.id.movieList)
        var movies = DataUtil.generateMovies()
        runBlocking {
            launch {
                movies = JsonMovieRepository(view.context).loadMovies()
            }
        }
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        liveMovies = viewModel.getMovies()
        val adapter = MovieAdapter(view.context, movies, this)
        viewModel.getMovies().observe(this.viewLifecycleOwner, Observer<List<Movie>> {
            adapter.movies = it
            adapter.notifyDataSetChanged()
        })
        list.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onClicked(fragment: Fragment) {
        moveToNextScreen(fragment)
    }

    private fun moveToNextScreen(fragment: Fragment) {
        fragmentManager?.beginTransaction()
                ?.replace(R.id.frame_layout, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }
}
interface MovieListListener {
    fun onClicked(fragment: Fragment)
}