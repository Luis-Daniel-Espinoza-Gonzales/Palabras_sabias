package com.example.palabras_sabias.prototipo.ui.screens

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.palabras_sabias.R

class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val username = findViewById<EditText>(R.id.inputUsername)
        val email = findViewById<EditText>(R.id.inputEmail)
        val password = findViewById<EditText>(R.id.inputPassword)
        val biography = findViewById<EditText>(R.id.inputBiography)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {

            // Validación: email obligatorio que contenga @
            if (!email.text.contains("@")) {
                Toast.makeText(this, "El email debe contener @", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val queue = Volley.newRequestQueue(this)
            val url = "http://192.168.1.46/palabras_sabias/Servidor_web/registrar_usuario.php"

            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                { response ->
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                },
                { error ->
                    Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
                }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = username.text.toString()
                    params["email"] = email.text.toString()
                    params["password"] = password.text.toString()
                    params["biography"] = biography.text.toString()
                    return params
                }
            }
            queue.add(stringRequest)
        }
    }
}
