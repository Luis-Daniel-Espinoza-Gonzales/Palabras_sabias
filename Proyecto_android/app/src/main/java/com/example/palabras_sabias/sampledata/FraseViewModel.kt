package com.example.palabras_sabias.sampledata

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.add
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class FraseViewModel(application: Application) : AndroidViewModel(application) {

    // Estado que la UI de Compose puede observar.
    // Inicialmente, la lista de frases está vacía.
    val frases = mutableStateOf<List<Frase>>(emptyList())
    val mensajeError = mutableStateOf<String?>(null)

    init {
        // Al iniciar el ViewModel, cargamos las frases.
        cargarFrases()
    }

    private fun cargarFrases() {
        val context = getApplication<Application>().applicationContext
        val queue = com.android.volley.toolbox.Volley.newRequestQueue(context)
        val url = "http://10.0.2.2/Palabras_sabias/obtener_usuarios.php" // URL de tu API

        val jsonArrayRequest = com.android.volley.toolbox.JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    // Usamos Gson para convertir la respuesta JSON en una lista de objetos Frase.
                    // Esto es más limpio que parsear el JSON manualmente.
                    val gson = Gson()
                    val listaFrases = gson.fromJson(response.toString(), Array<Frase>::class.java).toList()

                    // Actualizamos el estado con la nueva lista.
                    frases.value = listaFrases
                    mensajeError.value = null
                    Log.d("FraseViewModel", "Frases cargadas: ${listaFrases.size}")

                } catch (e: Exception) {
                    mensajeError.value = "Error al procesar la respuesta."
                    Log.e("FraseViewModel", "Error de parseo: ${e.message}")
                }
            },
            { error ->
                // Este bloque se ejecuta si hay un error en la red.
                mensajeError.value = "Error de red: ${error.message}"
                Log.e("FraseViewModel", "Error de Volley: ${error.message}")
            }
        )

        // Añadir la petición a la cola para que se ejecute.
        queue.add(jsonArrayRequest)
    }
}