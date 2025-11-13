package com.example.palabras_sabias.prototipo.ui.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.ui.screens.adapters.FavoritosAdapter
import com.example.palabras_sabias.prototipo.viewmodel.FavoritosViewModel

class FavoritosFragment : Fragment() {

    // 1. Obtener una instancia del ViewModel
    private val viewModel: FavoritosViewModel by viewModels()
    private lateinit var favoritosAdapter: FavoritosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favoritos, container, false)

        // 2. Configurar el RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.favoritos_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        favoritosAdapter = FavoritosAdapter(emptyList())
        recyclerView.adapter = favoritosAdapter

        // 3. Observar el LiveData del ViewModel
        viewModel.favoritos.observe(viewLifecycleOwner) { favoritosList ->
            // 4. Actualizar el Adapter con los nuevos datos
            favoritosAdapter.updateData(favoritosList)
        }

        viewModel.mensajeError.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 5. Pedir los datos al ViewModel
        // Aquí necesitaríamos el ID del usuario. Por ahora, usaré 1 como ejemplo.
        // En una app real, este ID vendría del usuario que ha iniciado sesión.
        viewModel.cargar_favoritos(2)
    }
}