package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

data class Dish(
    val nameResId: Int,
    val recipeResId: Int,
    val category: String,
    val cuisine: String
)

class DishActivity : AppCompatActivity() {

    private lateinit var buttonDish1: Button
    private lateinit var buttonDish2: Button
    private lateinit var buttonDish3: Button
    private lateinit var spinnerKitchen: Spinner

    private var selectedCuisine: String? = null
    private var selectedCategory: String? = null

    // 🔥 Все блюда здесь (можешь добавлять новые)
    private val allDishes = listOf(
        Dish(R.string.pancakes, R.string.recipe_pancakes, "breakfast", "russian"),
        Dish(R.string.pasta, R.string.recipe_pasta, "lunch", "italian"),
        Dish(R.string.salad, R.string.recipe_salad, "dinner", "russian")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)

        buttonDish1 = findViewById(R.id.buttonDish1)
        buttonDish2 = findViewById(R.id.buttonDish2)
        buttonDish3 = findViewById(R.id.buttonDish3)
        spinnerKitchen = findViewById(R.id.spinnerKitchen)

        selectedCategory = intent.getStringExtra("category")

        setupSpinner()
        updateDishButtons()
    }

    private fun setupSpinner() {

        val kitchens = listOf(
            getString(R.string.all),
            getString(R.string.italian),
            getString(R.string.russian),
            getString(R.string.french)
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            kitchens
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKitchen.adapter = adapter

        spinnerKitchen.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    selectedCuisine = when (position) {
                        1 -> "italian"
                        2 -> "russian"
                        3 -> "french"
                        else -> null
                    }

                    updateDishButtons()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
    }

    private fun getFilteredDishes(): List<Dish> {
        return allDishes.filter { dish ->
            dish.category == selectedCategory &&
                    (selectedCuisine == null || dish.cuisine == selectedCuisine)
        }
    }

    private fun updateDishButtons() {

        val filtered = getFilteredDishes()
        val buttons = listOf(buttonDish1, buttonDish2, buttonDish3)

        for (i in buttons.indices) {

            if (i < filtered.size) {

                buttons[i].visibility = View.VISIBLE
                buttons[i].text = getString(filtered[i].nameResId)

                buttons[i].setOnClickListener {
                    val intent = Intent(this, RecipeActivity::class.java)
                    intent.putExtra(
                        "recipe",
                        getString(filtered[i].recipeResId)
                    )
                    startActivity(intent)
                }

            } else {
                buttons[i].visibility = View.INVISIBLE
            }
        }
    }
}