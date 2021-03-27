package ru.alexoheah.fundamentalassignments.model

data class Movie(
    val id: Int,
    val poster: Int,
    val name: String,
    val age: Int,
    val category: String,
    val popular: Int,
    val reviewsCount: Int,
    val duration: Int,
    val description: String
) {
}