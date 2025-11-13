package com.example.palabras_sabias.prototipo.ui.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.viewmodel.UsuariosViewModel

class LoginActivity : ComponentActivity() {

    private val viewModel: UsuariosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.email_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.register_button) // 🔹 NUEVO

        setupObservers()

        // Botón de Login
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.iniciar_sesion(email, password)
            } else {
                Toast.makeText(this, "Por favor, introduce email y contraseña", Toast.LENGTH_SHORT).show()
            }
        }


        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupObservers() {
        viewModel.usuarioLogueado.observe(this) { usuario ->
            usuario?.let {
                Toast.makeText(this, "¡Bienvenido, ${it.username}!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("USER_DATA", it)
                startActivity(intent)
                finish()
            }
        }

        viewModel.mensajeError.observe(this) { mensaje ->
            mensaje?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}
