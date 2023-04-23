package com.suraj.moviematch.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.suraj.moviematch.R
import com.suraj.moviematch.adapter.MovieAdapter
import com.suraj.moviematch.data.Movie
import com.suraj.moviematch.data.Movies
import com.suraj.moviematch.data.getAllMovieJsonData

class HomeActivity : AppCompatActivity() {

    private lateinit var rvHomeActivity: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initview()

    }

    private fun initview() {

        val rvHomeActivity = findViewById<RecyclerView>(R.id.rvHomeActivity)
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
        rvHomeActivity.layoutManager = layoutManager
        rvHomeActivity.adapter = MovieAdapter(loadMoviesDataInRecyclerView())

    }


    private fun loadMoviesDataInRecyclerView(): List<Movie> {
        val gson = Gson()
        val movies = gson.fromJson(getAllMovieJsonData(), Movies::class.java).movies
        return movies.filterNotNull()
    }


}