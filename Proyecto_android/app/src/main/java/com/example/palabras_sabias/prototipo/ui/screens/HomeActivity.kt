package com.example.palabras_sabias.prototipo.ui.screens

import android.os.Bundle
import com.example.palabras_sabias.R

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutInflater.inflate(R.layout.activity_home, findViewById(R.id.content_frame))
    }
}