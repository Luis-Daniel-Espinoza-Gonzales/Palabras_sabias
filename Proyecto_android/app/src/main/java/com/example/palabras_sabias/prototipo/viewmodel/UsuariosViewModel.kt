package com.example.palabras_sabias.prototipo.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.palabras_sabias.prototipo.data.model.Usuarios
import com.example.palabras_sabias.prototipo.data.repository.UsuariosRepository

class UsuariosViewModel (application: Application) : AndroidViewModel(application) {

    // Creación de instancia de repositorio (objeto)
    private val repository = UsuariosRepository(application.applicationContext)

    // Contenedor de datos de usuarios privado y editable
    private val _usuarios = MutableLiveData<List<Usuarios>>()
    // Contenedor de datos de usuarios público y no editable (solo lectura)
    val usuarios: LiveData<List<Usuarios>> = _usuarios

    private val _mensajeError = MutableLiveData<String?>()
    val mensajeError: LiveData<String?> = _mensajeError

    // Inicializacion automatica del viewmodel (lo primero que hace)
    init {
        cargar_usuarios()
    }

    fun cargar_usuarios() {
        // Llamada al repositorio para cargar los usuarios
        repository.cargar_usuarios(
            onSuccess = { _usuarios.value = it; _mensajeError.value = null },
            onError = { _mensajeError.value = it }
        )
    }
}