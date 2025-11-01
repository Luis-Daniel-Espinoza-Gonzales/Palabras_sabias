package com.example.palabras_sabias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.palabras_sabias.ui.theme.Palabras_sabiasTheme

class MainActivity : ComponentActivity() {

    // Inicializamos el ViewModel
    private val fraseViewModel: FraseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Palabras_sabiasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Pasamos la lista de frases y el mensaje de error del ViewModel a nuestra UI
                    PantallaFrases(
                        listaDeFrases = fraseViewModel.frases.value,
                        mensajeError = fraseViewModel.mensajeError.value
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaFrases(listaDeFrases: List<Frase>, mensajeError: String?) {
    when {
        // Si hay un error, mostramos el mensaje de error
        mensajeError != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = mensajeError, color = MaterialTheme.colorScheme.error)
            }
        }
        // Si la lista está vacía (y no hay error), mostramos un indicador de carga
        listaDeFrases.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        // Si tenemos frases, las mostramos en una lista
        else -> {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(listaDeFrases) { frase ->
                    FraseCard(frase = frase)
                }
            }
        }
    }
}

@Composable
fun FraseCard(frase: Frase) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "\"${frase.texto}\"",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "- ${frase.autor}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}