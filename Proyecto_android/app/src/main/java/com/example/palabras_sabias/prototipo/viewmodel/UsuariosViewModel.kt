package com.example.palabras_sabias.prototipo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.palabras_sabias.prototipo.data.model.Usuario
import com.example.palabras_sabias.prototipo.data.model.Usuarios
import com.example.palabras_sabias.prototipo.data.repository.UsuariosRepository

class UsuariosViewModel (application: Application) : AndroidViewModel(application) {

    private val repository = UsuariosRepository(application.applicationContext)

    // Para la lista de todos los usuarios
    private val _usuarios = MutableLiveData<List<Usuarios>>()
    val usuarios: LiveData<List<Usuarios>> = _usuarios

    // --- NUEVO: LiveData para el resultado del login ---
    private val _usuarioLogueado = MutableLiveData<Usuario?>()
    val usuarioLogueado: LiveData<Usuario?> = _usuarioLogueado

    // Para los mensajes de error
    private val _mensajeError = MutableLiveData<String?>()
    val mensajeError: LiveData<String?> = _mensajeError

    init {
        // cargar_usuarios() // No es necesario cargarlos todos al inicio si la primera pantalla es el login
    }

    fun cargar_usuarios() {
        repository.cargar_usuarios(
            onSuccess = { 
                _usuarios.value = it
                _mensajeError.value = null 
            },
            onError = { _mensajeError.value = it }
        )
    }

    fun iniciar_sesion(email: String, password: String) {
        // Llamada a la función correcta del repositorio
        repository.verificar_usuario(email, password,
            onSuccess = { usuario ->
                // Si el login es exitoso, guardamos el usuario y limpiamos el error.
                _usuarioLogueado.value = usuario
                _mensajeError.value = null
            },
            onError = { mensajeDeError ->
                // Si hay un error, el usuario logueado es nulo y guardamos el mensaje de error.
                _usuarioLogueado.value = null
                _mensajeError.value = mensajeDeError
            }
        )
    }
}