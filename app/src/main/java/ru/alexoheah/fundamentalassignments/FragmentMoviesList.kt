package ru.alexoheah.fundamentalassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.alexoheah.fundamentalassignments.adapters.MovieAdapter
import ru.alexoheah.fundamentalassignments.utils.DataUtil

class FragmentMoviesList: Fragment(R.layout.fragment_movies_list), MovieListListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = view.findViewById<RecyclerView>(R.id.movieList)
        val movies = DataUtil.generateMovies()
        val adapter = MovieAdapter(view.context, movies, this)
        list.adapter = adapter
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