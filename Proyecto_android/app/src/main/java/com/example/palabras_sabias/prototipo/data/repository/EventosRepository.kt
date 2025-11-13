package com.example.palabras_sabias.prototipo.data.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.palabras_sabias.prototipo.data.model.Evento
import com.google.gson.Gson

class EventosRepository(private val context: Context) {

    fun cargar_eventos(
        onSuccess: (List<Evento>) -> Unit,
        onError: (String) -> Unit
    ) {
        val url = "http://192.168.1.46/palabras_sabias/Servidor_web/eventos_data.php"

        Log.d("EVENTOS_REPOSITORY", "🔗 Solicitando datos desde: $url")

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    Log.d("EVENTOS_REPOSITORY", "✅ Respuesta JSON: $response")
                    val eventos = Gson().fromJson(response.toString(), Array<Evento>::class.java).toList()
                    onSuccess(eventos)
                } catch (e: Exception) {
                    Log.e("EVENTOS_REPOSITORY", "❌ Error procesando JSON: ${e.message}")
                    onError("Error procesando datos: ${e.message}")
                }
            },
            { error ->
                Log.e("EVENTOS_REPOSITORY", "🚫 Error HTTP: ${error.message}")
                onError(error.message ?: "Error de red")
            }
        )

        Volley.newRequestQueue(context).add(request)
    }
}
