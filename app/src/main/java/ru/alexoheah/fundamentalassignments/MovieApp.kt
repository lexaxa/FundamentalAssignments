package ru.alexoheah.fundamentalassignments

import android.app.Application

class MovieApp: Application() {
    companion object {
        val instance = MovieApp()
    }
}