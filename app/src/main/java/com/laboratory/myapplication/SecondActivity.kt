package com.laboratory.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
            SecondScreen(onExitClick = { finish() })
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
                        Color(0xFFFFF8DC),
                        Color(0xFFFFEBCD),
                        Color(0xFFFFE4B5)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botón de regreso
            IconButton(
                onClick = onExitClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(130.dp))

            // Botón para "Laboratory Specifications"
            CustomButton(
                iconResId = R.drawable.laboratory_guides_icon,
                text = "Laboratory Specifications",
                onClick = {
                    val intent = Intent(context, LaboratorySpecificationsActivity::class.java)
                    context.startActivity(intent)
                }
            )

            // Botón para "Laboratory Materials"
            CustomButton(
                iconResId = R.drawable.laboratory_materials_icon,
                text = "Visual laboratory",
                onClick = {
                    val intent = Intent(context, LasboratoryMaterials::class.java)
                    context.startActivity(intent)
                }
            )

            // Botón para "Support Videos"
            CustomButton(
                iconResId = R.drawable.support_videos_icon,
                text = "Developers",
                onClick = {
                    val intent = Intent(context, Developers::class.java)
                    context.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Logos en la parte inferior
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.utd_logo),
                    contentDescription = "Logo 1",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.utdallas.edu/"))
                            context.startActivity(intent)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.ucatolica_logo),
                    contentDescription = "Logo 2",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ucatolica.edu.co/portal/"))
                            context.startActivity(intent)
                        }
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
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
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
