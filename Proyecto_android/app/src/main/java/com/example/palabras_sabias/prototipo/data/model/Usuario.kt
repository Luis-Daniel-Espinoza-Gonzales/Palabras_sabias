package com.example.palabras_sabias.prototipo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Usuario (
    val id: Int,
    val username: String,
    val email: String,
    val registration_day: String,
    val biography: String,
    val role: String
) : Parcelable