package com.example.palabras_sabias.prototipo.data.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.palabras_sabias.prototipo.data.model.Usuario
import com.example.palabras_sabias.prototipo.data.model.Usuarios
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONObject

class UsuariosRepository (private val context: Context)  {

    fun cargar_usuarios(
        onSuccess: (List<Usuarios>) -> Unit,
        onError: (String) -> Unit
    ) {
        val queue = Volley.newRequestQueue(context)
        val url = "http://192.168.1.46/palabras_sabias/Servidor_web/obtener_usuarios.php"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val lista_usuarios = Gson().fromJson(response.toString(), Array<Usuarios>::class.java).toList()
                    onSuccess(lista_usuarios)
                } catch (e: Exception) {
                    onError("Error al procesar la respuesta del servidor")
                }
            },
            { error ->
                onError(error.message ?: "Error de red desconocido")
            }
        )
        queue.add(jsonArrayRequest)
    }

    fun verificar_usuario(
        email: String,
        password: String,
        onSuccess: (Usuario) -> Unit, // Esperamos un único usuario
        onError: (String) -> Unit
    ) {
        val queue = Volley.newRequestQueue(context)
        val url = "http://192.168.1.46/palabras_sabias/Servidor_web/verificar_usuario.php"

        val stringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener<String> { response ->
                Log.d("Respuesta Login", "Respuesta del servidor: $response")
                try {
                    // 1. Convertimos la respuesta en un objeto JSON genérico
                    val jsonObject = JSONObject(response)
                    val status = jsonObject.getString("status")

                    // 2. Comprobamos el estado
                    if (status == "success") {
                        // 3. Si es éxito, extraemos el objeto "user" y lo convertimos a nuestra clase Usuario
                        val userObject = jsonObject.getJSONObject("user").toString()
                        val usuario = Gson().fromJson(userObject, Usuario::class.java)
                        onSuccess(usuario) // ¡Éxito!
                    } else {
                        // 4. Si el estado es "error", obtenemos el mensaje y lo pasamos al callback de error
                        val message = jsonObject.getString("message")
                        onError(message)
                    }
                } catch (e: Exception) {
                    // Esto ocurre si la respuesta no es un JSON válido
                    Log.e("LoginError", "Error al parsear JSON: ${e.message}")
                    onError("Respuesta inesperada del servidor")
                }
            },
            Response.ErrorListener { error ->
                onError(error.message ?: "Error de red desconocido")
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["email"] = email
                params["password"] = password
                return params
            }
        }

        queue.add(stringRequest)
    }
}