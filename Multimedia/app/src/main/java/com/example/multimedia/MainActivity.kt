package com.example.multimedia

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.multimedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showButton(true,
            false,
            false,
            false)

        val mediaPlayer = MediaPlayer()
        val audioAttributes = AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        mediaPlayer.setAudioAttributes(audioAttributes.build())

        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.musikk)
        mediaPlayer.setDataSource(this, uri)

        binding.apply {
            btnPlay.setOnClickListener {
                mediaPlayer.prepare()
                mediaPlayer.start()
                showButton(false,
                    true,
                    true,
                    false)
            }
            btnPause.setOnClickListener {
                if (mediaPlayer.isPlaying) mediaPlayer.pause()
                showButton(false,
                    false,
                    false,
                    true)
            }
            btnStop.setOnClickListener {
                if (mediaPlayer.isPlaying) mediaPlayer.pause()
                showButton(true,
                    false,
                    false,
                    false)
            }

            btnResume.setOnClickListener {
                mediaPlayer.start()
                showButton(false,
                    true,
                    true,
                    false)
            }
            btnVideo.setOnClickListener {
                val intent = Intent(this@MainActivity, VideoActivity::class.java)
                startActivity(intent)
            }
            btnVideoStreaming.setOnClickListener {
                val intent = Intent(this@MainActivity, VideoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showButton(
        isShowPlayButton: Boolean,
        isShowPauseButton: Boolean,
        isShowStopButton: Boolean,
        isShowResumeButton: Boolean
    ) {
        binding.apply {
            btnPlay.isEnabled = isShowPlayButton
            btnPause.isEnabled = isShowPauseButton
            btnStop.isEnabled = isShowStopButton
            btnResume.isEnabled = isShowResumeButton
        }
    }


}