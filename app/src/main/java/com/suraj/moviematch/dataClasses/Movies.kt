package com.suraj.moviematch.data


data class Movies(
    val movies: List<Movie>
)

data class Movie(
    val movieUrl: String,
    val imageUrl: String,
    val movieName: String,
    val categories: List<String>,
    val length: String
)

