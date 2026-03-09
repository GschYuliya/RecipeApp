package com.example.myapp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // Загружаем сохранённый язык
        loadLocale()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val languageButton = findViewById<Button>(R.id.buttonLanguage)

        val breakfastButton = findViewById<Button>(R.id.buttonBreakfast)
        val lunchButton = findViewById<Button>(R.id.buttonLunch)
        val dinnerButton = findViewById<Button>(R.id.buttonDinner)

        breakfastButton.setOnClickListener {
            openCategory("breakfast")
        }

        lunchButton.setOnClickListener {
            openCategory("lunch")
        }

        dinnerButton.setOnClickListener {
            openCategory("dinner")
        }

        languageButton.setOnClickListener {

            val prefs = getSharedPreferences("Settings", Context.MODE_PRIVATE)
            val currentLang = prefs.getString("My_Lang", "ru")

            val newLang = when (currentLang) {
                "ru" -> "en"
                "en" -> "fr"
                else -> "ru"
            }

            setLocale(newLang)
        }
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        config.setLocale(locale)

        resources.updateConfiguration(config, resources.displayMetrics)

        val prefs = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        prefs.edit().putString("My_Lang", languageCode).apply()

        recreate()
    }

    private fun loadLocale() {
        val prefs = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val language = prefs.getString("My_Lang", "ru")

        val locale = Locale(language!!)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun openCategory(category: String) {
        val intent = Intent(this, DishActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}