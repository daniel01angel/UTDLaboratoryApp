package com.laboratory.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.barteksc.pdfviewer.PDFView
import java.io.File
import java.io.FileOutputStream

class LaboratorySpecificationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaboratorySpecificationsScreen(onBackClick = { finish() })
        }
    }

    fun openPdf(fileName: String): File? {
        try {
            Log.d("PDF_DEBUG", "Intentando abrir el archivo PDF: $fileName.pdf")

            val resourceId = resources.getIdentifier(fileName, "raw", packageName)
            if (resourceId == 0) {
                Log.e("PDF_DEBUG", "No se encontró el recurso con el nombre: $fileName")
                Toast.makeText(this, "Archivo PDF no encontrado", Toast.LENGTH_SHORT).show()
                return null
            }

            val inputStream = resources.openRawResource(resourceId)
            val file = File(filesDir, "$fileName.pdf")
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            outputStream.close()
            inputStream.close()

            if (file.exists()) {
                Log.d("PDF_DEBUG", "Archivo copiado en: ${file.absolutePath}")
            } else {
                Log.e("PDF_DEBUG", "El archivo no se encontró después de copiar.")
                Toast.makeText(this, "Error al copiar el archivo PDF", Toast.LENGTH_SHORT).show()
                return null
            }

            return file
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al abrir el PDF", Toast.LENGTH_SHORT).show()
            Log.e("PDF_DEBUG", "Excepción: ${Log.getStackTraceString(e)}")
            return null
        }
    }
}

@Composable
fun LaboratorySpecificationsScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current
    var showPdf by remember { mutableStateOf<File?>(null) }

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
        if (showPdf != null) { BackHandler {
                showPdf = null
            }

            Column(modifier = Modifier.fillMaxSize()) {
                IconButton(
                    onClick = { showPdf = null },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Cerrar PDF",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PdfViewer(pdfFile = showPdf!!)
            }
        } else {
            Column {
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

                GuideItem(
                    iconResId = R.drawable.laboratory_guides_icon,
                    title = "Desktop Multimeter",
                    description = "1 File (PDF) | 22kB",
                    onDownloadClick = {
                        if (context is LaboratorySpecificationsActivity) {
                            val file = context.openPdf("desktop_multimeter")
                            if (file != null) {
                                showPdf = file
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Segundo elemento: Handheld Multimeter
                GuideItem(
                    iconResId = R.drawable.laboratory_guides_icon, // Asegúrate de tener esta imagen en drawable
                    title = "Handheld Multimeter",
                    description = "1 File (PDF) | 50kB",
                    onDownloadClick = {
                        if (context is LaboratorySpecificationsActivity) {
                            val file = context.openPdf("handheld_multimeter") // Abre handheld_multimeter.pdf
                            if (file != null) {
                                showPdf = file
                            }
                        }
                    }
                )
            }
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

        Image(
            painter = painterResource(id = R.drawable.open_icon),
            contentDescription = "Descargar",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onDownloadClick)
        )
    }
}

@Composable
fun PdfViewer(pdfFile: File) {
    AndroidView(
        factory = { context ->
            PDFView(context, null).apply {
                fromFile(pdfFile)
                    .enableSwipe(true) // Habilita el deslizamiento
                    .swipeHorizontal(false) // Configura el deslizamiento horizontal según prefieras
                    .enableAnnotationRendering(true) // Habilita la renderización de anotaciones
                    .load()
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
