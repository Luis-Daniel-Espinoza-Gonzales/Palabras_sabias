package com.example.palabras_sabias.prototipo.data.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.palabras_sabias.prototipo.data.model.Autor
import com.example.palabras_sabias.prototipo.data.model.ObraDestacada
import com.google.gson.Gson

class HomeRepository(private val context: Context) {

    fun cargarHomeData(
        onSuccess: (List<Autor>, ObraDestacada) -> Unit,
        onError: (String) -> Unit
    ) {
        val url = "http://192.168.1.46/palabras_sabias/Servidor_web/home_data.php"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val autores = Gson().fromJson(
                        response.getJSONArray("autores").toString(),
                        Array<Autor>::class.java
                    ).toList()

                    val obra = Gson().fromJson(
                        response.getJSONObject("obra_destacada").toString(),
                        ObraDestacada::class.java
                    )

                    onSuccess(autores, obra)

                } catch (e: Exception) {
                    onError("Error procesando datos")
                }
            },
            { error ->
                onError(error.message ?: "Error de red")
            }
        )

        Volley.newRequestQueue(context).add(request)
    }
}
