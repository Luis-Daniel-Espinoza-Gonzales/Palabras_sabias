package com.example.palabras_sabias.prototipo.ui.screens

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.viewmodel.UsuariosViewModel

// Se cambió ComponentActivity por BaseActivity para usar el navbar
class MainActivity02 : BaseActivity() {

    private val viewModel: UsuariosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aquí se infla el layout dentro del content_frame del navbar (NO usar setContentView)
        layoutInflater.inflate(R.layout.activity_main, findViewById(R.id.content_frame))



        //id de lista del activity_main.xml
        val list = findViewById<ListView>(R.id.user_list_view)

        //Intermediario entre los datos y el listview
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        list.adapter = adapter

        // Observa los cambios en los usuarios
        viewModel.usuarios.observe(this) { lista_usuarios ->
            adapter.clear()
            adapter.addAll(lista_usuarios.map { "${it.username} - ${it.email} - ${it.registration_day}" })
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
