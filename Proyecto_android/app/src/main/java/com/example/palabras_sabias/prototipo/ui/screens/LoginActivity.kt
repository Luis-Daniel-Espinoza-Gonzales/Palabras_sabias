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

        //Configura los observadores para reaccionar a los resultados
        setupObservers()

        //Configura el OnClickListener del botón
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                //Llama a la función del ViewModel para que inicie el proceso de login
                viewModel.iniciar_sesion(email, password)
            } else {
                Toast.makeText(this, "Por favor, introduce email y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        // Observador para el resultado del login exitoso
        viewModel.usuarioLogueado.observe(this) { usuario ->
            // Si el usuario no es nulo, el login fue exitoso
            usuario?.let {
                Toast.makeText(this, "¡Bienvenido, ${it.username}!", Toast.LENGTH_LONG).show()

                /*
                // Aquí iría la lógica para navegar a la siguiente pantalla, por ejemplo MainActivity02
                val intent = Intent(this, MainActivity02::class.java)
                startActivity(intent)
                finish() // Cierra LoginActivity para que el usuario no pueda volver con el botón de atrás
                */
            }
        }

        // Observador para los mensajes de error
        viewModel.mensajeError.observe(this) { mensaje ->
            // Si el mensaje no es nulo, mostramos el error
            mensaje?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}