package com.example.palabras_sabias.prototipo.ui.screens

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.viewmodel.UsuariosViewModel

class MainActivity02 : ComponentActivity() {

    private val viewModel: UsuariosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //id de lista del activity_main.xml
        val list = findViewById<ListView>(R.id.user_list_view)

        //Intermediario entre los datos y el listview
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        list.adapter = adapter

        // Observar los cambios en los usuarios
        viewModel.usuarios.observe(this) { lista_usuarios ->
            adapter.clear()
            adapter.addAll(lista_usuarios.map { it.username })
            adapter.notifyDataSetChanged()
        }

        // Opcional: observar errores
        viewModel.mensajeError.observe(this) { mensaje ->
            mensaje?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        // Cargar los usuarios
        viewModel.cargar_usuarios()
    }
}