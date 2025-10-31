package com.example.palabras_sabias.sampledata
import retrofit2.Call
import retrofit2.http.GET

class API_interface {
    @GET("obtener_usuarios.php")
    fun obtenerUsuarios(): Call<List<Usuario>>
}