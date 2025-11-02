package com.example.palabras_sabias.prototipo.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.palabras_sabias.prototipo.data.model.Usuarios
import com.example.palabras_sabias.prototipo.data.repository.UsuariosRepository

class UsuariosViewModel (application: Application) : AndroidViewModel(application) {

    private val repository = UsuariosRepository(application.applicationContext)

    private val _usuarios = MutableLiveData<List<Usuarios>>()
    val usuarios: LiveData<List<Usuarios>> = _usuarios

    private val _mensajeError = MutableLiveData<String?>()
    val mensajeError: LiveData<String?> = _mensajeError


    init {
        cargar_usuarios()
    }

    fun cargar_usuarios() {
        repository.cargar_usuarios(
            onSuccess = { _usuarios.value = it; _mensajeError.value = null },
            onError = { _mensajeError.value = it }
        )
    }
}