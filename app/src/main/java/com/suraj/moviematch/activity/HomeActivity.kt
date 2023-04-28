package com.suraj.moviematch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.suraj.moviematch.R
import com.suraj.moviematch.Utils.Utils
import com.suraj.moviematch.adapter.MovieAdapter
import com.suraj.moviematch.data.Movie
import com.suraj.moviematch.data.Movies
import com.suraj.moviematch.data.getAllMovieJsonData
import com.suraj.moviematch.data.getAnimationMovieJsonData
import com.suraj.moviematch.data.getTeluguMovieJsonData

class HomeActivity : AppCompatActivity() {

    private lateinit var rvHomeActivity: RecyclerView

    private lateinit var toolbar: Toolbar

    private lateinit var txt_filterAll: TextView

    private lateinit var txt_filterTelugu: TextView
    private lateinit var txt_filterAction: TextView
    private lateinit var txt_filterComedy: TextView
    private lateinit var txt_filterHorror: TextView
    private lateinit var txt_filterThriller: TextView
    private lateinit var txt_filterAdventure: TextView
    private lateinit var txt_filterAnimation: TextView

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initview()
        loadMoviesDataInRecyclerView("All")

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        setOnCLick()
        setUpListener()


    }

    private fun initview() {

        toolbar = findViewById<Toolbar>(R.id.custom_toolbar)


        txt_filterAll = findViewById<TextView>(R.id.txt_filterAll)
        txt_filterTelugu = findViewById<TextView>(R.id.txt_filterTelugu)
        txt_filterAction = findViewById<TextView>(R.id.txt_filterAction)
        txt_filterComedy = findViewById<TextView>(R.id.txt_filterComedy)
        txt_filterHorror = findViewById<TextView>(R.id.txt_filterHorror)
        txt_filterThriller = findViewById<TextView>(R.id.txt_filterThriller)
        txt_filterAdventure = findViewById<TextView>(R.id.txt_filterAdventure)
        txt_filterAnimation = findViewById<TextView>(R.id.txt_filterAnimation)

        rvHomeActivity = findViewById<RecyclerView>(R.id.rvHomeActivity)

    }


    private fun setOnCLick() {

        val onClickListener = OnClickListener()
        txt_filterAll.setOnClickListener(onClickListener)
        txt_filterTelugu.setOnClickListener(onClickListener)
        txt_filterAction.setOnClickListener(onClickListener)
        txt_filterComedy.setOnClickListener(onClickListener)
        txt_filterHorror.setOnClickListener(onClickListener)
        txt_filterThriller.setOnClickListener(onClickListener)
        txt_filterAdventure.setOnClickListener(onClickListener)
        txt_filterAnimation.setOnClickListener(onClickListener)


    }

    private inner class OnClickListener : View.OnClickListener {
        override fun onClick(view: View?) {


            when (view!!.id) {

                R.id.txt_filterAll -> {
                    loadMoviesDataInRecyclerView("All")
                }

                R.id.txt_filterTelugu -> {

                    loadMoviesDataInRecyclerView("Telugu")

                }

                R.id.txt_filterAction -> {
                    loadMoviesDataInRecyclerView("Action")

                }

                R.id.txt_filterComedy -> {
                    loadMoviesDataInRecyclerView("Comedy")

                }

                R.id.txt_filterHorror -> {
                    loadMoviesDataInRecyclerView("Horror")

                }

                R.id.txt_filterThriller -> {
                    loadMoviesDataInRecyclerView("Thriller")
                }

                R.id.txt_filterAdventure -> {
                    loadMoviesDataInRecyclerView("Adventure")
                }

                R.id.txt_filterAnimation -> {
                    loadMoviesDataInRecyclerView("Animation")
                }
            }
            movieAdapter.notifyDataSetChanged()
        }
    }

    private fun loadMoviesDataInRecyclerView(filter: String): ArrayList<Movie> {

        var movieList = ArrayList<Movie>()
        val gson = Gson()

        setupUi(filter)

        when (filter) {

            "All" -> {
                movieList = gson.fromJson(
                    getAllMovieJsonData(),
                    Movies::class.java
                ).movies as ArrayList<Movie>

                val layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

                rvHomeActivity.layoutManager = layoutManager

                movieAdapter = MovieAdapter(movieList)

                rvHomeActivity.adapter = movieAdapter
            }


            "Telugu" -> {
                Utils.makeToast(this@HomeActivity, "Telugu")

                movieList = gson.fromJson(
                    getTeluguMovieJsonData(),
                    Movies::class.java
                ).movies as ArrayList<Movie>

                val layoutManager =
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

                rvHomeActivity.layoutManager = layoutManager

                movieAdapter = MovieAdapter(movieList)

                rvHomeActivity.adapter = movieAdapter

            }


            "Action" -> {
                movieList = gson.fromJson(
                    getAllMovieJsonData(),
                    Movies::class.java
                ).movies as ArrayList<Movie>
                val layoutManager =
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

                rvHomeActivity.layoutManager = layoutManager
                movieAdapter = MovieAdapter(movieList)

                rvHomeActivity.adapter = movieAdapter

            }

            "Comedy" -> {
                movieList = gson.fromJson(
                    getTeluguMovieJsonData(),
                    Movies::class.java
                ).movies as ArrayList<Movie>

                val layoutManager =
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

                rvHomeActivity.layoutManager = layoutManager

                movieAdapter = MovieAdapter(movieList)

                rvHomeActivity.adapter = movieAdapter

            }

            "Horror" -> {
                movieList = gson.fromJson(
                    getTeluguMovieJsonData(),
                    Movies::class.java
                ).movies as ArrayList<Movie>

                val layoutManager =
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

                rvHomeActivity.layoutManager = layoutManager

                movieAdapter = MovieAdapter(movieList)

                rvHomeActivity.adapter = movieAdapter

            }

            "Thriller" -> {
                movieList = gson.fromJson(
                    getTeluguMovieJsonData(),
                    Movies::class.java
                ).movies as ArrayList<Movie>

                val layoutManager =
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

                rvHomeActivity.layoutManager = layoutManager

                movieAdapter = MovieAdapter(movieList)

                rvHomeActivity.adapter = movieAdapter

            }

            "Adventure" -> {
                movieList = gson.fromJson(
                    getTeluguMovieJsonData(),
                    Movies::class.java
                ).movies as ArrayList<Movie>

                val layoutManager =
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

                rvHomeActivity.layoutManager = layoutManager

                movieAdapter = MovieAdapter(movieList)

                rvHomeActivity.adapter = movieAdapter

            }

            "Animation" -> {
                movieList = gson.fromJson(
                    getAnimationMovieJsonData(),
                    Movies::class.java
                ).movies as ArrayList<Movie>

                val layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

                rvHomeActivity.layoutManager = layoutManager

                movieAdapter = MovieAdapter(movieList)

                rvHomeActivity.adapter = movieAdapter

            }
        }
        return movieList
    }

    private fun setupUi(selectFilter: String) {

        setBackgroundUnselected()

        when (selectFilter) {

            "All" -> {
                txt_filterAll.setTextColor(resources.getColor(R.color.purple))
                txt_filterAll.setBackgroundColor(resources.getColor(R.color.white))
            }

            "Telugu" -> {
                txt_filterTelugu.setTextColor(resources.getColor(R.color.purple))
                txt_filterTelugu.setBackgroundColor(resources.getColor(R.color.white))
            }

            "Action" -> {
                txt_filterAction.setTextColor(resources.getColor(R.color.purple))
                txt_filterAction.setBackgroundColor(resources.getColor(R.color.white))
            }

            "Comedy" -> {
                txt_filterComedy.setTextColor(resources.getColor(R.color.purple))
                txt_filterComedy.setBackgroundColor(resources.getColor(R.color.white))
            }

            "Horror" -> {
                txt_filterHorror.setTextColor(resources.getColor(R.color.purple))
                txt_filterHorror.setBackgroundColor(resources.getColor(R.color.white))
            }

            "Thriller" -> {
                txt_filterThriller.setTextColor(resources.getColor(R.color.purple))
                txt_filterThriller.setBackgroundColor(resources.getColor(R.color.white))
            }

            "Adventure" -> {
                txt_filterAdventure.setTextColor(resources.getColor(R.color.purple))
                txt_filterAdventure.setBackgroundColor(resources.getColor(R.color.white))
            }

            "Animation" -> {
                txt_filterAnimation.setTextColor(resources.getColor(R.color.purple))
                txt_filterAnimation.setBackgroundColor(resources.getColor(R.color.white))
            }
        }
    }


    private fun setBackgroundUnselected() {

        txt_filterAll.setTextColor(resources.getColor(R.color.white))
        txt_filterAll.setBackgroundColor(resources.getColor(R.color.purpleLight))

        txt_filterTelugu.setTextColor(resources.getColor(R.color.white))
        txt_filterTelugu.setBackgroundColor(resources.getColor(R.color.purpleLight))

        txt_filterAction.setTextColor(resources.getColor(R.color.white))
        txt_filterAction.setBackgroundColor(resources.getColor(R.color.purpleLight))

        txt_filterComedy.setTextColor(resources.getColor(R.color.white))
        txt_filterComedy.setBackgroundColor(resources.getColor(R.color.purpleLight))

        txt_filterHorror.setTextColor(resources.getColor(R.color.white))
        txt_filterHorror.setBackgroundColor(resources.getColor(R.color.purpleLight))

        txt_filterThriller.setTextColor(resources.getColor(R.color.white))
        txt_filterThriller.setBackgroundColor(resources.getColor(R.color.purpleLight))

        txt_filterAdventure.setTextColor(resources.getColor(R.color.white))
        txt_filterAdventure.setBackgroundColor(resources.getColor(R.color.purpleLight))

        txt_filterAnimation.setTextColor(resources.getColor(R.color.white))
        txt_filterAnimation.setBackgroundColor(resources.getColor(R.color.purpleLight))

    }


    private fun setUpListener() {

        movieAdapter.setOnClickListener = object : MovieAdapter.SetOnClickListener {
            override fun onClick(movieUrl: String) {
                val intent = Intent(this@HomeActivity, MovieDetailsActivity::class.java)
                intent.putExtra("movieUrl", movieUrl)
                startActivity(intent)
                finish()
            }
        }
    }

}