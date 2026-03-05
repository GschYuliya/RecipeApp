package com.example.myapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val dish = intent.getStringExtra("dish")
        val textRecipe = findViewById<TextView>(R.id.textRecipe)

        when (dish) {
            "pancakes" -> textRecipe.text = getString(R.string.recipe_pancakes)
            "pasta" -> textRecipe.text = getString(R.string.recipe_pasta)
            "salad" -> textRecipe.text = getString(R.string.recipe_salad)
        }
    }
}