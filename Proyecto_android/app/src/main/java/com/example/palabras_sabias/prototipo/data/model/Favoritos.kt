package com.example.palabras_sabias.prototipo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Favoritos (
    val id: Int,
    val fecha_agregado: String,
    val id_obra: Int,
    val title: String,
    val synopsis: String,
    val autor: String,
    val genero: String,
    val formato: String,
    val fecha_publicacion: String
): Parcelable