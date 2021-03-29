package ru.alexoheah.fundamentalassignments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import ru.alexoheah.fundamentalassignments.FragmentMoviesDetails
import ru.alexoheah.fundamentalassignments.MovieListListener
import ru.alexoheah.fundamentalassignments.R
import ru.alexoheah.fundamentalassignments.model.Movie


class MovieAdapter(
    context: Context,
    var movies: List<Movie>,
    private val listener: MovieListListener
): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), listener)
    }

    private fun getItem(position: Int): Movie = movies[position]

    override fun getItemCount(): Int = movies.size


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val poster: ImageView = view.findViewById(R.id.ivPoster)
        private val name: TextView = view.findViewById(R.id.tvMovieName)
        private val age: TextView = view.findViewById(R.id.tvAge)
        private val category: TextView = view.findViewById(R.id.tvMovieCategory)
        private val popular: ImageView = view.findViewById(R.id.ivMoviePop)
        private val reviewsCount: TextView = view.findViewById(R.id.tvMovieCountReviews)
        private val duration: TextView = view.findViewById(R.id.tvMovieTime)

        fun onBind(movie: Movie, listener: MovieListListener) {

            itemView.setOnClickListener {
                listener.onClicked(FragmentMoviesDetails.newInstance(movie.id))
            }

            Glide.with(itemView)
                .load(movie.imageUrl)
                .override(SIZE_ORIGINAL, SIZE_ORIGINAL)
                .into(poster)


            name.text = movie.title
            age.text = movie.pgAge.toString()
            category.text = movie.genres.firstOrNull()?.name
            popular.setImageResource(R.drawable.ic_star_filled)
            reviewsCount.text = movie.reviewCount.toString()
            duration.text = movie.runningTime.toString()
        }

    }

}