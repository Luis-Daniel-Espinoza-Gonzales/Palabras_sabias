package com.example.palabras_sabias.prototipo.data.repository

import android.content.Context
import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.palabras_sabias.prototipo.data.model.Usuarios
import com.google.gson.Gson
import org.json.JSONArray

class UsuariosRepository (private val context: Context)  {

    fun cargar_usuarios(
        onSuccess: (List<Usuarios>) -> Unit,
        onError: (String) -> Unit
    ) {
        val queue = Volley.newRequestQueue(context)
        val url = "http://10.220.125.210/palabras_sabias/Servidor_web/obtener_usuarios.php"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val lista_usuarios = Gson().fromJson(response.toString(), Array<Usuarios>::class.java).toList()
                    onSuccess(lista_usuarios)
                } catch (e: Exception) {
                    onError("Error al cargar los usuarios")
                }
            },
            { error ->
                onError(error.message ?: "Error desconocido")
            }
        )
        queue.add(jsonArrayRequest)
    }
}