package com.suraj.moviematch.adapter

import android.content.Intent
import android.graphics.Color
import android.support.annotation.ColorRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suraj.moviematch.R
import com.suraj.moviematch.data.Movie

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    interface SetOnClickListener {
        fun onClick(movieUrl: String)

    }

    var setOnClickListener: SetOnClickListener? = null

    private fun setOnSaveButtonClick(setOnClickListener: SetOnClickListener) {
        this.setOnClickListener = setOnClickListener;
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        lateinit var imgMovie: ImageView
        lateinit var mainContainer: LinearLayout
        lateinit var moviesLayout: LinearLayout

        lateinit var txtMovieName: TextView

        init {

            imgMovie = view.findViewById(R.id.imgMovie)

            txtMovieName = view.findViewById(R.id.txtMovieName)
            mainContainer = view.findViewById(R.id.mainContainer)
            moviesLayout = view.findViewById(R.id.moviesLayout)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_movies_layout, null)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies[position]


        holder.txtMovieName.text = movie.movieName

        Glide.with(holder.view.context).load(movie.imageUrl).into(holder.imgMovie)



        holder.mainContainer.setOnClickListener {
            setOnClickListener?.onClick(movie.movieUrl)
        }
        holder.moviesLayout.setOnClickListener {
            setOnClickListener?.onClick(movie.movieUrl)
        }

    }

    override fun getItemCount() = movies.size - 1

}
