/*package com.example.palabras_sabias.sampledata

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONArray

class FraseViewModel(application: Application) : AndroidViewModel(application) {

    val frases = mutableStateOf<List<Frase>>(emptyList())
    val mensajeError = mutableStateOf<String?>(null)

    init {
        cargarFrases()
    }

    private fun cargarFrases() {
        val context = getApplication<Application>().applicationContext
        val queue = Volley.newRequestQueue(context)

        // URL configurada con la IP de tu PC para que el celular pueda conectarse.
        // Tu PC y tu celular DEBEN estar conectados a la misma red Wi-Fi.
        val url = "http://192.168.0.10/palabras_sabias/Servidor_web/obtener_usuarios.php"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response: JSONArray ->
                try {
                    val gson = Gson()
                    val listaFrases = gson.fromJson(response.toString(), Array<Frase>::class.java).toList()
                    frases.value = listaFrases
                    mensajeError.value = null
                    Log.d("FraseViewModel", "Frases cargadas: ${listaFrases.size}")
                } catch (e: Exception) {
                    mensajeError.value = "Error al procesar la respuesta."
                    Log.e("FraseViewModel", "Error de parseo", e)
                }
            },
            { error: VolleyError ->
                val errorMessage = error.message ?: "Error desconocido"
                mensajeError.value = "Error de red: $errorMessage"
                Log.e("FraseViewModel", "Error de Volley", error)
            }
        )

        queue.add(jsonArrayRequest)
    }
}
*/