package com.example.palabras_sabias.sampledata

import com.google.gson.annotations.SerializedName

data class Frase(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val registration_day: String,
    // Usamos @SerializedName para mapear el campo "biograpy" del JSON
    // a nuestra variable "biography" en Kotlin. Es más limpio que
    // usar un nombre con una errata en nuestro código.
    @SerializedName("biograpy")
    val biography: String,
    val role: String
)