package com.suraj.moviematch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.suraj.moviematch.R

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var playButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val intent = getIntent()

        val movieUrl = intent.getStringExtra("movieUrl")

        playButton = findViewById(R.id.btnPlayMovie)

        playButton.setOnClickListener {
            val intent = Intent(this@MovieDetailsActivity, PlayMoviesActivity::class.java)
            intent.putExtra("movieUrl", movieUrl)
            startActivity(intent)
            finish()
        }

    }
}