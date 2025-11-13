package com.example.palabras_sabias.prototipo.data.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.palabras_sabias.prototipo.data.model.Favoritos
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONObject

class FavoritosRepository (private val context: Context) {

    fun cargar_favoritos(
        id_usuario: Int,
        onSuccess: (List<Favoritos>) -> Unit,
        onError: (String) -> Unit
    ) {
        val queue = Volley.newRequestQueue(context)
        val url = "http://192.168.0.10/palabras_sabias/Servidor_web/obtener_favoritos.php"

        val stringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener<String> { response ->
                try {
                    // 1. Convertimos la respuesta en un objeto JSON genérico
                    val jsonObject = JSONObject(response)
                    val status = jsonObject.getString("status")

                    // 2. Comprobamos el estado
                    if (status == "success") {
                        // 3. Si es éxito, extraemos el ARRAY "favorites" y lo convertimos
                        val favoritesArray = jsonObject.getJSONArray("favorites").toString()
                        val lista_favoritos = Gson().fromJson(favoritesArray, Array<Favoritos>::class.java).toList()
                        onSuccess(lista_favoritos)
                    } else {
                        // 4. Si el estado es "error", obtenemos el mensaje
                        val message = jsonObject.getString("message")
                        onError(message)
                    }
                } catch (e: Exception) {
                    Log.e("FavoritosError", "Error al parsear JSON: ${e.message}")
                    onError("Respuesta inesperada del servidor")
                }
            },
            Response.ErrorListener { error ->
                onError(error.message ?: "Error de red desconocido")
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id_usuario"] = id_usuario.toString()
                return params
            }
        }

        queue.add(stringRequest)
    }
}