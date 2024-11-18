package com.laboratory.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondScreen(onExitClick = { finish() }) // Llamada para cerrar la actividad cuando se presiona el botón "EXIT"
        }
    }
}

@Composable
fun SecondScreen(onExitClick: () -> Unit) {
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
            ), // Fondo naranja
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón para "Laboratory Specifications"
            CustomButton(
                iconResId = R.drawable.laboratory_guides_icon,
                text = "Laboratory Specifications",
                onClick = {
                    val intent = Intent(context, LaboratorySpecificationsActivity::class.java)
                    context.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para "Laboratory Materials"
            CustomButton(
                iconResId = R.drawable.laboratory_materials_icon,
                text = "Visual laboratory",
                onClick = {
                    val intent = Intent(context, LasboratoryMaterials::class.java)
                    context.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para "Support Videos"
            CustomButton(
                iconResId = R.drawable.support_videos_icon,
                text = "Developers",
                onClick = {
                    val intent = Intent(context, Developers::class.java)
                    context.startActivity(intent)
                    // Acción específica para Support Videos
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de salida "EXIT"
            Button(
                onClick = onExitClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(50), // Botón con bordes redondeados
                modifier = Modifier
                    .width(200.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = "EXIT",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CustomButton(iconResId: Int, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
            )
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
