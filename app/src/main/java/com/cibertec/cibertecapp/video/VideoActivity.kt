package com.cibertec.cibertecapp.video

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.cibertecapp.R

class VideoActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val videoView = findViewById<VideoView>(R.id.videoView)

        val urlVideo =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"

        val mediaControllers = MediaController(this)
        mediaControllers.setAnchorView(videoView)
        videoView.setMediaController(mediaControllers)

        videoView.setVideoURI(Uri.parse(urlVideo))
        videoView.start()
    }

}