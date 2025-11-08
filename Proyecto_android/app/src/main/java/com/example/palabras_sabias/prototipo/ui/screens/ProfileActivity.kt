package com.example.palabras_sabias.prototipo.ui.screens

import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.data.model.Usuario

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutInflater.inflate(R.layout.activity_profile, findViewById(R.id.content_frame))

        val usernameTextView = findViewById<TextView>(R.id.username_textview)
        val biographyTextView = findViewById<TextView>(R.id.biography_textview)

        //Recupera el objeto Usuario del Intent
        val usuario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("USER_DATA", Usuario::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Usuario>("USER_DATA")
        }

        //Si el usuario no es nulo, actualizar la UI
        usuario?.let {
            usernameTextView.text = it.username
            biographyTextView.text = it.biography
        }
    }
}