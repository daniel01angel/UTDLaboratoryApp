package com.laboratory.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LaboratorySpecificationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaboratorySpecificationsScreen(onBackClick = { finish() }) // Llamada a finish() para cerrar la actividad
        }
    }

    // Cambié la visibilidad a public para que se pueda acceder desde la UI.
    fun openPdf(fileName: String) {
        val pdfUri = Uri.parse("android.resource://" + packageName + "/raw/" + fileName)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(pdfUri, "application/pdf")
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "No app available to view PDF", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun LaboratorySpecificationsScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF8C42), // Naranja más claro arriba
                        Color(0xFFFF6B35), // Naranja medio
                        Color(0xFFFF4800)  // Naranja más oscuro abajo
                    )
                )
            )
            .padding(16.dp)
    ) {
        Column {
            // Botón de regreso
            IconButton(
                onClick = onBackClick, // Acción para volver atrás
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Primer elemento: Desktop Multimeter
            GuideItem(
                iconResId = R.drawable.laboratory_guides_icon, // Coloca una imagen en drawable
                title = "Desktop Multimeter",
                description = "1 File (PDF) | 22kB",
                onDownloadClick = {
                    if (context is LaboratorySpecificationsActivity) {
                        context.openPdf("desktop_multimeter") // Abre desktop_multimeter.pdf
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Segundo elemento: Handheld Multimeter
            GuideItem(
                iconResId = R.drawable.laboratory_guides_icon, // Coloca una imagen en drawable
                title = "Handheld Multimeter",
                description = "1 File (PDF) | 50kB",
                onDownloadClick = {
                    if (context is LaboratorySpecificationsActivity) {
                        context.openPdf("handheld_multimeter") // Abre handheld_multimeter.pdf
                    }
                }
            )
        }
    }
}

@Composable
fun GuideItem(iconResId: Int, title: String, description: String, onDownloadClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontSize = 18.sp, color = Color.Black)
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Ícono de descarga personalizado
        Image(
            painter = painterResource(id = R.drawable.download_icon),
            contentDescription = "Descargar",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onDownloadClick)
        )
    }
}
