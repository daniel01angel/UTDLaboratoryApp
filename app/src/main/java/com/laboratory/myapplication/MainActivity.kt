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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
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
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF8DC), // Cream color más claro
                        Color(0xFFFFEBCD), // Cream color medio
                        Color(0xFFFFE4B5)  // Cream color más oscuro
                    )
                )
            )
    ) {
        // Título en la parte superior
        Text(
            text = "UTD Laboratory",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
            color = Color(0xFFFF5722),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
        )

        // Contenido central
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logos de las universidades
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.utd_logo),
                    contentDescription = "UTD Logo",
                    modifier = Modifier.size(180.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ucatolica_logo),
                    contentDescription = "UCatólica Logo",
                    modifier = Modifier.size(180.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Texto de bienvenida centrado con Box
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de inicio
            Button(
                onClick = onStartClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text(text = "START", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}
