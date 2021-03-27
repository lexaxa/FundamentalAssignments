package ru.alexoheah.fundamentalassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.alexoheah.fundamentalassignments.adapters.ActorAdapter
import ru.alexoheah.fundamentalassignments.utils.DataUtil

class FragmentMoviesDetails: Fragment(R.layout.fragment_movies_details) {

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
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_actors)
        recyclerView.adapter = ActorAdapter(view.context, DataUtil.generateActors())
        val movie = movieId?.let { DataUtil.generateMovies().get(it) }
        val category = view.findViewById<TextView>(R.id.tvMovieCategory)
        val reviews = view.findViewById<TextView>(R.id.tvMovieCountReviews)
        val descr = view.findViewById<TextView>(R.id.tvMovieStoryDescription)
        category.text = movie?.category
        reviews.text = movie?.reviewsCount.toString()
        descr.text = movie?.description
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

}