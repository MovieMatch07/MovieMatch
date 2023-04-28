package com.suraj.moviematch.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.view.View
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.suraj.moviematch.R

class PlayMoviesActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var player: SimpleExoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play_movies)
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, "MyApp::MyWakelockTag")
        wakeLock.acquire()

        val intent = getIntent()

        val movieUrl = intent.getStringExtra("movieUrl")

         playerView = findViewById<PlayerView>(R.id.playerView)
         player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player

        val videoUrl = movieUrl
        val mediaItem = videoUrl?.let { MediaItem.fromUri(it) }
        if (mediaItem != null) {
            player.setMediaItem(mediaItem)
        }

        player.prepare()
        player.play()


    }
    override fun onStop() {
        super.onStop()
        player.release()
    }

    override fun onBackPressed() {
        val intent = Intent(this@PlayMoviesActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}