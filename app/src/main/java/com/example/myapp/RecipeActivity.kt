package com.example.myapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.media.MediaPlayer
import android.widget.VideoView
import android.net.Uri
import android.os.CountDownTimer
import android.graphics.Color
import android.widget.EditText

class RecipeActivity : AppCompatActivity() {

    private var timer1: CountDownTimer? = null
    private var timeLeft1: Long = 120000
    private var timerRunning1 = false

    private var timer2: CountDownTimer? = null
    private var timeLeft2: Long = 60000
    private var timerRunning2 = false

    private var timer3: CountDownTimer? = null
    private var timeLeft3: Long = 30000
    private var timerRunning3 = false

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

        val timerText1 = findViewById<TextView>(R.id.timer1Text)
        val start1 = findViewById<Button>(R.id.startTimer1)
        val pause1 = findViewById<Button>(R.id.pauseTimer1)
        val reset1 = findViewById<Button>(R.id.resetTimer1)
        val input = findViewById<EditText>(R.id.customTime)

        val timerText2 = findViewById<TextView>(R.id.timer2Text)
        val start2 = findViewById<Button>(R.id.startTimer2)
        val pause2 = findViewById<Button>(R.id.pauseTimer2)
        val reset2 = findViewById<Button>(R.id.resetTimer2)

        val timerText3 = findViewById<TextView>(R.id.timer3Text)
        val start3 = findViewById<Button>(R.id.startTimer3)
        val pause3 = findViewById<Button>(R.id.pauseTimer3)
        val reset3 = findViewById<Button>(R.id.resetTimer3)

        start1.setOnClickListener {

            val secondsText = input.text.toString()

            if (secondsText.isNotEmpty()) {
                val seconds = secondsText.toLong()
                timeLeft1 = seconds * 1000
            }

            if (!timerRunning1) {

                timer1 = object : CountDownTimer(timeLeft1, 1000) {

                    override fun onTick(millisUntilFinished: Long) {

                        timeLeft1 = millisUntilFinished

                        val seconds = (millisUntilFinished / 1000).toInt()
                        val minutes = seconds / 60
                        val sec = seconds % 60

                        timerText1.text = "%02d:%02d".format(minutes, sec)

                        if (millisUntilFinished < 60000) {
                            timerText1.setTextColor(Color.RED)
                        }

                    }

                    override fun onFinish() {

                        timerRunning1 = false

                        val sound = MediaPlayer.create(this@RecipeActivity, R.raw.timer1_sound)
                        sound.start()

                    }

                }.start()

                timerRunning1 = true
            }
        }

        pause1.setOnClickListener {

            timer1?.cancel()
            timerRunning1 = false

        }

        reset1.setOnClickListener {

            timer1?.cancel()

            timeLeft1 = 120000
            timerRunning1 = false

            timerText1.text = "02:00"
            timerText1.setTextColor(Color.BLACK)

        }

        start2.setOnClickListener {

            if (!timerRunning2) {

                timer2 = object : CountDownTimer(timeLeft2, 1000) {

                    override fun onTick(millisUntilFinished: Long) {

                        timeLeft2 = millisUntilFinished

                        val seconds = (millisUntilFinished / 1000).toInt()
                        val minutes = seconds / 60
                        val sec = seconds % 60

                        timerText2.text = "%02d:%02d".format(minutes, sec)

                        if (millisUntilFinished < 60000) {
                            timerText2.setTextColor(Color.RED)
                        }

                    }

                    override fun onFinish() {

                        timerRunning2 = false

                        val sound = MediaPlayer.create(this@RecipeActivity, R.raw.timer2_sound)
                        sound.start()

                    }

                }.start()

                timerRunning2 = true
            }
        }

        pause2.setOnClickListener {

            timer2?.cancel()
            timerRunning2 = false

        }

        reset2.setOnClickListener {

            timer2?.cancel()

            timeLeft2 = 60000
            timerRunning2 = false

            timerText2.text = "01:00"
            timerText2.setTextColor(Color.BLACK)

        }

        start3.setOnClickListener {

            if (!timerRunning3) {

                timer3 = object : CountDownTimer(timeLeft3, 1000) {

                    override fun onTick(millisUntilFinished: Long) {

                        timeLeft3 = millisUntilFinished

                        val seconds = (millisUntilFinished / 1000).toInt()
                        val minutes = seconds / 60
                        val sec = seconds % 60

                        timerText3.text = "%02d:%02d".format(minutes, sec)

                        if (millisUntilFinished < 60000) {
                            timerText3.setTextColor(Color.RED)
                        }

                    }

                    override fun onFinish() {

                        timerRunning3 = false

                        val sound = MediaPlayer.create(this@RecipeActivity, R.raw.timer3_sound)
                        sound.start()

                    }

                }.start()

                timerRunning3 = true
            }
        }

        pause3.setOnClickListener {

            timer3?.cancel()
            timerRunning3 = false

        }

        reset3.setOnClickListener {

            timer3?.cancel()

            timeLeft3 = 30000
            timerRunning3 = false

            timerText3.text = "00:30"
            timerText3.setTextColor(Color.BLACK)

        }
    }
}