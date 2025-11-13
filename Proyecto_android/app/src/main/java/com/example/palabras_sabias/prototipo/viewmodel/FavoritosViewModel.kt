package com.example.palabras_sabias.prototipo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.palabras_sabias.prototipo.data.model.Favoritos
import com.example.palabras_sabias.prototipo.data.repository.FavoritosRepository

class FavoritosViewModel (application: Application) : AndroidViewModel(application){

    private val repository = FavoritosRepository(application.applicationContext)

    // Para la lista de todos los favoritos del usuario
    private val _favoritos = MutableLiveData<List<Favoritos>>()
    val favoritos: LiveData<List<Favoritos>> = _favoritos

    // Para los mensajes de error
    private val _mensajeError = MutableLiveData<String?>()
    val mensajeError: LiveData<String?> = _mensajeError

    init {
        // cargar_usuarios() // No es necesario cargarlos todos al inicio si la primera pantalla es el login
    }

    fun cargar_favoritos(id_usuario: Int) {
        // Llamada a la función correcta del repositorio
        repository.cargar_favoritos(id_usuario,
            onSuccess = {
                _favoritos.value = it
                _mensajeError.value = null
            },
            onError = { _mensajeError.value = it }
        )
    }

}