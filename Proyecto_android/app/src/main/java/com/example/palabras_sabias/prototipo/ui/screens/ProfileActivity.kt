package com.example.palabras_sabias.prototipo.ui.screens

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.ui.screens.fragments.FavoritosFragment
import com.example.palabras_sabias.prototipo.ui.screens.fragments.InicioFragment
import com.example.palabras_sabias.prototipo.ui.screens.fragments.PublicacionesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileActivity : BaseActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutInflater.inflate(R.layout.activity_profile, findViewById(R.id.content_frame), true)

        val usernameTextView = findViewById<TextView>(R.id.username_textview)
        val biographyTextView = findViewById<TextView>(R.id.biography_textview)
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        // Usar los datos del usuario recuperados por BaseActivity
        currentUser?.let {
            usernameTextView.text = it.username
            biographyTextView.text = it.biography

            // --- Configuración del ViewPager y TabLayout ---
            // Le pasamos el ID del usuario al adaptador
            val adapter = ViewPagerAdapter(this, it.id)
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Inicio"
                    1 -> "Favoritos"
                    2 -> "Publicaciones"
                    else -> null
                }
            }.attach()
        }        
    }

    // --- Adaptador para el ViewPager ---
    private class ViewPagerAdapter(activity: AppCompatActivity, private val userId: Int) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> InicioFragment() // Podríamos pasarle el ID si lo necesitara
                // Aquí usamos el nuevo método para crear el fragmento con el ID del usuario
                1 -> FavoritosFragment.newInstance(userId)
                2 -> PublicacionesFragment() // Podríamos pasarle el ID si lo necesitara
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }
    }
}