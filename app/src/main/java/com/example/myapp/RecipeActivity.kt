package com.example.myapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.media.MediaPlayer
import android.widget.VideoView
import android.net.Uri

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val recipe = intent.getStringExtra("recipe")

        val textRecipe = findViewById<TextView>(R.id.textRecipe)
        val buttonVoice = findViewById<Button>(R.id.buttonVoice)
        val video = findViewById<VideoView>(R.id.videoRecipe)

        textRecipe.text = recipe

        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.pancakes_video)
        video.setVideoURI(uri)
        video.start()

        buttonVoice.setOnClickListener {

            val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
            val language = prefs.getString("My_Lang", "ru")

            val audio = when(language){
                "ru" -> R.raw.pancakes_ru
                "fr" -> R.raw.pancakes_fr
                else -> R.raw.pancakes_en
            }

            val mediaPlayer = MediaPlayer.create(this, audio)
            mediaPlayer.start()
        }
    }
}