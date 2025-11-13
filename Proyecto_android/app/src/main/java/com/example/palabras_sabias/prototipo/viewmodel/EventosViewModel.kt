package com.example.palabras_sabias.prototipo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.palabras_sabias.prototipo.data.model.Evento
import com.example.palabras_sabias.prototipo.data.repository.EventosRepository

class EventosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = EventosRepository(application.applicationContext)


    private val _eventos = MutableLiveData<List<Evento>>()
    val eventos: LiveData<List<Evento>> = _eventos


    private val _mensajeError = MutableLiveData<String?>()
    val mensajeError: LiveData<String?> = _mensajeError


    fun cargar_eventos() {
        repository.cargar_eventos(
            onSuccess = { listaEventos ->
                _eventos.value = listaEventos
                _mensajeError.value = null
            },
            onError = { error ->
                _mensajeError.value = error
            }
        )
    }


}
