package com.example.palabras_sabias.prototipo.ui.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.example.palabras_sabias.R

open class BaseActivity : AppCompatActivity() {

    protected lateinit var drawerLayout: DrawerLayout
    protected lateinit var navView: NavigationView
    protected lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       
        setContentView(R.layout.activity_base)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        // Mostrar icono ☰ (hamburger)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Clicks del menú lateral (solo estructura, sin acción aún)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_eventos -> {
                    // FUTURA FUNCIÓN: abrir eventos
                }
                R.id.nav_comunidad -> {
                    // FUTURA FUNCIÓN: abrir comunidad
                }
                R.id.nav_generos -> {
                    // FUTURA FUNCIÓN: abrir géneros
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    // Inflar menú superior (lupa y perfil)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                // FUTURA FUNCIÓN DE BUSCAR
                return true
            }
            R.id.action_profile -> {
                // FUTURA FUNCIÓN DE PERFIL
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
