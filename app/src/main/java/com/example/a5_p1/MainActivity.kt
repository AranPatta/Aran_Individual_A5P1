package com.example.a5_p1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var imageView: ImageView
    private val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
    private var currentImageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        imageView = findViewById(R.id.imageView)
        val button: Button = findViewById(R.id.button)

        val prefs = getSharedPreferences("appPrefs", Context.MODE_PRIVATE)
        currentImageIndex = prefs.getInt("imageIndex", 0)
        imageView.setImageResource(images[currentImageIndex])
        editText.setText(prefs.getString("editText", ""))

        button.setOnClickListener {
            currentImageIndex = Random.nextInt(images.size)
            imageView.setImageResource(images[currentImageIndex])
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getSharedPreferences("appPrefs", Context.MODE_PRIVATE).edit().apply {
            putInt("imageIndex", currentImageIndex)
            putString("editText", editText.text.toString())
            apply()
        }
    }
}
