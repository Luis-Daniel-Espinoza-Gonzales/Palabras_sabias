package com.example.palabras_sabias.prototipo.ui.screens

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.example.palabras_sabias.R
import com.example.palabras_sabias.prototipo.data.model.Usuario

open class BaseActivity : AppCompatActivity() {

    protected lateinit var drawerLayout: DrawerLayout
    protected lateinit var navView: NavigationView
    protected lateinit var toolbar: Toolbar
    protected var currentUser: Usuario? = null // Variable para guardar el usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        // --- Recuperar el usuario del Intent ---
        currentUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("USER_DATA", Usuario::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Usuario>("USER_DATA")
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

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
                    val intent = Intent(this, EventosActivity::class.java)
                    startActivity(intent)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> { return true }
            R.id.action_profile -> {
                // ANCHOR FIX: Usamos la Toolbar como ancla, ya que findViewById no puede encontrar IDs de menú.
                showProfilePopupMenu(toolbar)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showProfilePopupMenu(anchor: View) {
        // Añadimos Gravity.END para alinear el menú a la derecha de la toolbar.
        val popup = PopupMenu(this, anchor, Gravity.END)
        popup.menuInflater.inflate(R.menu.profile_popup_menu, popup.menu)

        // --- Mostrar el nombre de usuario dinámicamente ---
        currentUser?.let {
            val usernameItem = popup.menu.findItem(R.id.action_username_display)
            usernameItem.title = it.username
        }

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_view_channel -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    // --- Pasar los datos del usuario a ProfileActivity ---
                    intent.putExtra("USER_DATA", currentUser)
                    startActivity(intent)
                    true
                }
                R.id.action_logout -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}