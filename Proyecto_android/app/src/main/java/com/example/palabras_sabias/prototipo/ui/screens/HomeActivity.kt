package com.example.palabras_sabias.prototipo.ui.screens

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.viewmodel.HomeViewModel

class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutInflater.inflate(R.layout.activity_home, findViewById(R.id.content_frame))

        val txtTituloObra = findViewById<TextView>(R.id.txtTituloObra)
        val txtAutorObra = findViewById<TextView>(R.id.txtAutorObra)
        val txtRatingObra = findViewById<TextView>(R.id.txtRatingObra)

        val listAutores = findViewById<ListView>(R.id.listaAutores)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        listAutores.adapter = adapter

        viewModel.autores.observe(this) { autores ->
            Log.d("AUTORES_API", "Autores recibidos: $autores")

            if (autores.isNullOrEmpty()) {
                Toast.makeText(this, "No hay autores disponibles", Toast.LENGTH_SHORT).show()
                return@observe
            }

            adapter.clear()
            adapter.addAll(autores.map { it.nombre ?: "Sin nombre" })
            adapter.notifyDataSetChanged()
        }

        viewModel.obraDestacada.observe(this) { obra ->
            txtTituloObra.text = obra.titulo ?: "Sin título"
            txtAutorObra.text = "Autor: ${obra.autor ?: "Desconocido"}"
            txtRatingObra.text = "Calificación: ${obra.calificacion_promedio ?: 0}/10 "
        }

        viewModel.mensajeError.observe(this) { mensaje ->
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

        Log.d("HOME_ACTIVITY", "🔥 HomeActivity iniciada, llamando a cargarHomeData()")
        viewModel.cargarHomeData()

    }
}
