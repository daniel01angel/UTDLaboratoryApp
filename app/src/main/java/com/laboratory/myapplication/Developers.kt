package com.laboratory.myapplication


import android.os.Bundle
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Developer(
    val imageId: Int,
    val name: String,
    val university: String,
    val career: String
)

class Developers : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevelopersScreen(onBackClick = { finish() })
        }
    }
}

@Composable
fun DevelopersScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current
    val developers = listOf(
        Developer(R.drawable.fotojasmin, "Jasmine Flores", "University of Texas at Dallas", "Biomedical Engineering"),
        Developer(R.drawable.fotogeoff, "Geoff Daniel", "University of Texas at Dallas", "Biomedical Engineering"),
        Developer(R.drawable.fotojustin, "Justin Dao", "University of Texas at Dallas", "Biomedical Engineering"),
        Developer(R.drawable.fotomaurya, "Maurya Gowda", "University of Texas at Dallas", "Biomedical Engineering"),
        Developer(R.drawable.fotoemer, "Emerson Toledo", "Universidad Catolica de Colombia", "Engineer Systems "),
        Developer(R.drawable.fotoangel, "Daniel Angel", "Universidad Catolica de Colombia", "Engineer Systems"),
        Developer(R.drawable.fotosantiago, "Santigo Mendoza", "Universidad Catolica de Colombia", "Engineer Systems"),
    )

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
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 60.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(developers) { developer ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFDAB9)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = developer.imageId),
                                contentDescription = "Developer photo",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text(
                                    text = developer.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = developer.university,
                                    fontSize = 16.sp,
                                    color = Color.DarkGray
                                )
                                Text(
                                    text = developer.career,
                                    fontSize = 16.sp,
                                    color = Color.DarkGray
                                )
                            }
                        }
                    }
                }
            }

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
