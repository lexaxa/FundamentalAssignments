package ru.alexoheah.fundamentalassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.alexoheah.fundamentalassignments.adapters.ActorAdapter
import ru.alexoheah.fundamentalassignments.data.JsonMovieRepository
import ru.alexoheah.fundamentalassignments.model.Movie
import ru.alexoheah.fundamentalassignments.utils.DataUtil


class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val movieId by lazy {
        arguments?.getInt("MOVIE_ID")
    }

    companion object {
        fun newInstance(movieId: Int) =
            FragmentMoviesDetails().apply {
                arguments = bundleOf(
                    "MOVIE_ID" to movieId
                )
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar? = view.findViewById(R.id.toolbar)
        toolbar?.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_actors)
        var movie: Movie? = null //movieId?.let { DataUtil.generateMovies().get(it) }
        runBlocking {
            launch {
                if (movieId != null)
                    movie = JsonMovieRepository(context!!).loadMovie(movieId!!)
            }
        }
        recyclerView.adapter = movie?.actors?.let {
            ActorAdapter(
                view.context,
                it
            )
        }
        val category = view.findViewById<TextView>(R.id.tvMovieCategory)
        val reviews = view.findViewById<TextView>(R.id.tvMovieCountReviews)
        val descr = view.findViewById<TextView>(R.id.tvMovieStoryDescription)
        category.text = movie?.genres?.firstOrNull()?.name
        reviews.text = movie?.reviewCount.toString()
        descr.text = movie?.storyLine
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

}