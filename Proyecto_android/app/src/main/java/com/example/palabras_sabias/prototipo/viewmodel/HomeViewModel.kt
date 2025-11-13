package com.example.palabras_sabias.prototipo.viewmodel
import android.util.Log
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.palabras_sabias.prototipo.data.model.Autor
import com.example.palabras_sabias.prototipo.data.model.ObraDestacada
import com.example.palabras_sabias.prototipo.data.repository.HomeRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HomeRepository(application.applicationContext)

    private val _autores = MutableLiveData<List<Autor>>()
    val autores: LiveData<List<Autor>> = _autores

    private val _obraDestacada = MutableLiveData<ObraDestacada>()
    val obraDestacada: LiveData<ObraDestacada> = _obraDestacada

    private val _mensajeError = MutableLiveData<String>()
    val mensajeError: LiveData<String> = _mensajeError

    fun cargarHomeData() {
        Log.d("HOME_VIEWMODEL", " cargarHomeData() ejecutado")
        repository.cargarHomeData(
            onSuccess = { autores, obra ->
                Log.d("HOME_VIEWMODEL", "HTTP OK, autores: $autores  obra: $obra")
                _autores.value = autores
                _obraDestacada.value = obra
            },
            onError = { mensaje ->
                Log.e("HOME_VIEWMODEL", " Error: $mensaje")
                _mensajeError.value = mensaje
            }
        )
    }

}
