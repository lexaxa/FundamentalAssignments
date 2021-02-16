package ru.alexoheah.fundamentalassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMoviesList: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val btn = view.findViewById<ImageView>(R.id.ivPoster)
        btn?.setOnClickListener {
            moveToNextScreen()
        }
    }

    private fun moveToNextScreen() {
        fragmentManager?.beginTransaction()
                ?.replace(R.id.frame_layout, FragmentMoviesDetails())
                ?.addToBackStack(FragmentMoviesDetails::class.java.canonicalName)
                ?.commit()
    }
}