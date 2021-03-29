package ru.alexoheah.fundamentalassignments.utils

import ru.alexoheah.fundamentalassignments.R
import ru.alexoheah.fundamentalassignments.model.Actor
import ru.alexoheah.fundamentalassignments.model.Genre
import ru.alexoheah.fundamentalassignments.model.Movie

object DataUtil {
    fun generateMovies(): List<Movie> {
        val list = mutableListOf<Movie>()
        repeat(10) {
            list.add(Movie(it, 16, "Movie $it",
                emptyList(),  16, 100 * it, true,
                30, "https://image.tmdb.org/t/p/w342/9HT9982bzgN5on1sLRmc1GMn6ZC.jpg",
                "https://image.tmdb.org/t/p/w342/gnf4Cb2rms69QbCnGFJyqwBWsxv.jpg",
                "description", generateActors()
            ))
        }
        return list
    }

    fun generateActors(): List<Actor> = listOf(
        Actor(1, "Actor 1", "https://image.tmdb.org/t/p/w342/h94QUkKrwUncYhJ1eMz23kBAJuc.jpg"),
        Actor(2, "Actor 2", "https://image.tmdb.org/t/p/w342/h94QUkKrwUncYhJ1eMz23kBAJuc.jpg"),
        Actor(3, "Actor 3", "https://image.tmdb.org/t/p/w342/h94QUkKrwUncYhJ1eMz23kBAJuc.jpg"),
        Actor(4, "Actor 4", "https://image.tmdb.org/t/p/w342/h94QUkKrwUncYhJ1eMz23kBAJuc.jpg"),
        Actor(5, "Actor 1", "https://image.tmdb.org/t/p/w342/h94QUkKrwUncYhJ1eMz23kBAJuc.jpg"),
        Actor(6, "Actor 2", "https://image.tmdb.org/t/p/w342/h94QUkKrwUncYhJ1eMz23kBAJuc.jpg"),
        Actor(7, "Actor 3", "https://image.tmdb.org/t/p/w342/h94QUkKrwUncYhJ1eMz23kBAJuc.jpg"),
        Actor(8, "Actor 4", "https://image.tmdb.org/t/p/w342/h94QUkKrwUncYhJ1eMz23kBAJuc.jpg"),
    )
}
