package ru.alexoheah.fundamentalassignments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, FragmentMoviesList(), FragmentMoviesList::class.java.simpleName)
                    .commit()
        }
    }

}