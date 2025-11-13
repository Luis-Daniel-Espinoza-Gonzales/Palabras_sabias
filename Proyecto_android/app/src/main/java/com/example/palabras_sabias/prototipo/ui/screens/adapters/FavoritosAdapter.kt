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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorito, parent, false)
        return FavoritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        val favorito = favoritos[position]
        holder.bind(favorito)
    }

    override fun getItemCount(): Int = favoritos.size

    fun updateData(newFavoritos: List<Favoritos>) {
        favoritos = newFavoritos
        notifyDataSetChanged()
    }

    class FavoritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.favorito_title)
        private val synopsisTextView: TextView = itemView.findViewById(R.id.favorito_synopsis)

        fun bind(favorito: Favoritos) {
            titleTextView.text = favorito.title
            synopsisTextView.text = favorito.synopsis
        }
    }
}