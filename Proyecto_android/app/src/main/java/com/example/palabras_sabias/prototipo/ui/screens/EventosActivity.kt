package com.example.palabras_sabias.prototipo.ui.screens

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.viewmodel.EventosViewModel

class EventosActivity : BaseActivity() {

    private val viewModel: EventosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        layoutInflater.inflate(R.layout.activity_eventos, findViewById(R.id.content_frame))


        val listView = findViewById<ListView>(R.id.listaEventos)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        listView.adapter = adapter

        // Observamos los eventos
        viewModel.eventos.observe(this) { eventos ->
            if (eventos.isNullOrEmpty()) {
                Toast.makeText(this, "No hay eventos disponibles", Toast.LENGTH_SHORT).show()
                return@observe
            }

            adapter.clear()
            adapter.addAll(eventos.map {
                """
                📚 ${it.titulo ?: "Sin título"}
                🗓 Fecha: ${it.fecha_evento ?: "No definida"}
                📍 Lugar: ${it.ubicacion ?: "No definido"}
                🧑‍💼 Organizador: ${it.organizador ?: "Desconocido"}
                📝 ${it.descripcion ?: ""}
                """.trimIndent()
            })
            adapter.notifyDataSetChanged()
        }


        viewModel.mensajeError.observe(this) { mensaje ->
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

        Log.d("EVENTOS_ACTIVITY", " Cargando eventos...")
        viewModel.cargar_eventos()
    }
}
