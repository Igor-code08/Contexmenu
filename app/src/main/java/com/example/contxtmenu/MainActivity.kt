package com.example.contxtmenu

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var gradeInput: EditText
    private lateinit var randomNumberButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gradeInput = findViewById(R.id.gradeInput)
        randomNumberButton = findViewById(R.id.randomNumberButton)

        // Регистрация контекстного меню для поля ввода
        registerForContextMenu(gradeInput)

        randomNumberButton.setOnClickListener {
            val randomNumber = Random.nextInt(1, 51)
            gradeInput.setText(randomNumber.toString())
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.color_quality -> {
                applyColorQuality()
                true
            }
            R.id.exit_app -> {
                finish()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun applyColorQuality() {
        val inputText = gradeInput.text.toString()
        val value = inputText.toIntOrNull()

        if (value != null) {
            val color = when (value) {
                1 -> Color.parseColor("#FFA500") // оранжевый
                2 -> Color.YELLOW
                3 -> Color.GREEN
                4 -> Color.BLUE
                5 -> Color.RED
                in 1..10 -> Color.RED
                in 11..20 -> Color.parseColor("#FFA500") // оранжевый
                in 21..30 -> Color.YELLOW
                in 31..40 -> Color.GREEN
                in 41..50 -> Color.BLUE
                else -> Color.WHITE // стандартный цвет
            }
            gradeInput.setBackgroundColor(color)
        }
    }
}