package ru.alexoheah.fundamentalassignments.utils

import ru.alexoheah.fundamentalassignments.R
import ru.alexoheah.fundamentalassignments.model.Actor
import ru.alexoheah.fundamentalassignments.model.Movie

object DataUtil {
    fun generateMovies(): List<Movie> {
        val list = mutableListOf<Movie>()
        repeat(10) {
            list.add(Movie(it,
                R.drawable.movie1, "Movie $it", 16, "Action",
                R.drawable.ic_star_filled, 100 * it, 30 * it,
            "description"))
        }
        return list
    }

    fun generateActors(): List<Actor> = listOf(
        Actor(R.drawable.actor1, "Actor 1"),
        Actor(R.drawable.actor2, "Actor 2"),
        Actor(R.drawable.actor3, "Actor 3"),
        Actor(R.drawable.actor4, "Actor 4"),
        Actor(R.drawable.actor1, "Actor 1"),
        Actor(R.drawable.actor2, "Actor 2"),
        Actor(R.drawable.actor3, "Actor 3"),
        Actor(R.drawable.actor4, "Actor 4"),
    )
}
