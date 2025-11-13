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

    private val viewModel: FavoritosViewModel by viewModels()
    private lateinit var favoritosAdapter: FavoritosAdapter

    // --- Factory Method para crear instancias de este fragmento ---
    companion object {
        private const val ARG_USER_ID = "user_id"

        fun newInstance(userId: Int): FavoritosFragment {
            val fragment = FavoritosFragment()
            val args = Bundle()
            args.putInt(ARG_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favoritos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.favoritos_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        favoritosAdapter = FavoritosAdapter(emptyList())
        recyclerView.adapter = favoritosAdapter

        viewModel.favoritos.observe(viewLifecycleOwner) { favoritosList ->
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
        
        // 5. OBTENER EL ID DEL USUARIO DESDE LOS ARGUMENTOS
        arguments?.getInt(ARG_USER_ID)?.let { userId ->
            viewModel.cargar_favoritos(userId)
        } ?: run {
            Toast.makeText(context, "No se proporcionó el ID de usuario al fragmento", Toast.LENGTH_SHORT).show()
        }
    }
}