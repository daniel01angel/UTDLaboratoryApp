package com.laboratory.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laboratory.myapplication.ui.theme.UTDLaboratoryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTDLaboratoryAppTheme {
                MainScreen(onStartClick = {
                    startActivity(Intent(this, SecondActivity::class.java))
                })
            }
        }
    }
}

@Composable
fun MainScreen(onStartClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF6600)) // Fondo naranja
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Alinea toda la columna al centro
        ) {
            // Logos de las universidades
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.utd_logo), // Asegúrate de agregar las imágenes al directorio 'res/drawable'
                    contentDescription = "UTD Logo",
                    modifier = Modifier.size(100.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ucatolica_logo),
                    contentDescription = "UCatólica Logo",
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Texto de bienvenida centrado con Box
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome to UTDlaboratory application!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de inicio
            Button(
                onClick = onStartClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Color verde
            ) {
                Text(text = "START", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}
