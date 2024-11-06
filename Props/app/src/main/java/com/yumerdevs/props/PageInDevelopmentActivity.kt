package com.yumerdevs.props

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PageInDevelopmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_in_development)

        // Mostrar mensaje en la interfaz
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Página en desarrollo"
    }
}
