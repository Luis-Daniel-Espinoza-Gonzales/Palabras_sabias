package com.example.palabras_sabias.prototipo.ui.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.data.model.Favoritos

class FavoritosAdapter(private var favoritos: List<Favoritos>) : RecyclerView.Adapter<FavoritosAdapter.FavoritoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        // Infla (crea) la vista del layout 'item_favorito.xml' que diseñamos para una sola fila.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorito, parent, false)
        // Crea y devuelve un ViewHolder que contiene esta nueva vista.
        return FavoritoViewHolder(view)
    }

    // Actualiza el contenido de un ViewHolder existente para mostrar los datos correspondientes
    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        // Obtiene el objeto de datos (Favoritos) para la posición actual.
        val favorito = favoritos[position]
        // Rellena el ViewHolder con los datos del objeto Favoritos.
        holder.bind(favorito)
    }

    // Devuelve el número total de elementos que hay en la lista de datos.
    override fun getItemCount(): Int = favoritos.size

    // Actualiza la lista de datos del adaptador y notifica al RecyclerView que los datos han cambiado.
    fun updateData(newFavoritos: List<Favoritos>) {
        // Actualiza la lista interna de datos.
        favoritos = newFavoritos
        // Notifica al RecyclerView que los datos han cambiado, para que se redibuje a sí mismo.
        notifyDataSetChanged()
    }

    // Clase interna que representa un ViewHolder para un elemento de la lista de favoritos.
    class FavoritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Mantiene las referencias a los TextViews dentro de 'item_favorito.xml'.
        private val titleTextView: TextView = itemView.findViewById(R.id.favorito_title)
        private val synopsisTextView: TextView = itemView.findViewById(R.id.favorito_synopsis)

        // Rellena las vistas del ViewHolder con los datos de un objeto Favoritos específico.
        fun bind(favorito: Favoritos) {
            titleTextView.text = favorito.title
            synopsisTextView.text = favorito.synopsis
        }
    }
}