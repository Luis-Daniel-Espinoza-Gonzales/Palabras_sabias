package com.example.palabras_sabias.prototipo.data.model

data class Evento(
    val id: Int,
    val titulo: String?,
    val fecha_evento: String?,
    val ubicacion: String?,
    val descripcion: String?,
    val organizador: String?,
    val fecha_creacion: String?
)
