package com.example.bienestaranimal360.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bienestaranimal360.R
import com.example.bienestaranimal360.ui.components.GlassCard
import com.example.bienestaranimal360.ui.theme.*

@Composable
fun MapScreen() {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. Map Background (Simulated)
        MapBackground()

        // 2. Pins
        // Pin 1: VetCare Center
        MapPin(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-50).dp, y = (-100).dp),
            label = "VetCare Center",
            icon = Icons.Default.Pets,
            color = Primary,
            onClick = { Toast.makeText(context, "VetCare Selected", Toast.LENGTH_SHORT).show() }
        )

        // Pin 2: Tu elección
        MapPin(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 60.dp, y = (-50).dp),
            label = "Tu elección",
            icon = Icons.Default.Verified,
            color = Secondary,
            scale = 1.2f,
            onClick = { Toast.makeText(context, "Tu ubicación", Toast.LENGTH_SHORT).show() }
        )

        // Pin 3: Clínica Norte
        MapPin(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-80).dp, y = 80.dp),
            label = "Clínica Norte",
            icon = Icons.Default.MedicalServices,
            color = Primary,
            onClick = { Toast.makeText(context, "Clínica Norte Selected", Toast.LENGTH_SHORT).show() }
        )


        // 3. Top Overlay (Search & Filters)
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
        ) {
            // Search Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                        .shadow(2.dp, RoundedCornerShape(12.dp))
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.surface, CircleShape)
                        .shadow(2.dp, CircleShape)
                        .clickable { Toast.makeText(context, "Buscar...", Toast.LENGTH_SHORT).show() }
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Search, null, tint = Color.Gray)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Buscar clínicas, tiendas...", color = Color.Gray, fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(MaterialTheme.colorScheme.surface, CircleShape)
                        .shadow(2.dp, CircleShape)
                        .clickable { Toast.makeText(context, "Filtros", Toast.LENGTH_SHORT).show() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Tune, null, tint = Color.Gray)
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                            .size(8.dp)
                            .background(Secondary, CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Filter Chips
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listOf("Todas", "Abierto ahora", "Urgencias 24h", "Especialistas")) { filter ->
                    val isSelected = filter == "Todas"
                    Button(
                        onClick = { Toast.makeText(context, "Filtro: $filter", Toast.LENGTH_SHORT).show() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Primary else MaterialTheme.colorScheme.surface,
                            contentColor = if (isSelected) Color.White else Color.Gray
                        ),
                        elevation = ButtonDefaults.buttonElevation(2.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(filter, fontSize = 12.sp)
                    }
                }
            }
        }

        // 4. Bottom Sheet (Clinic Details)
        // Fixed at bottom above NavBar
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 90.dp, start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Drag handle
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(40.dp)
                        .height(4.dp)
                        .background(Color.LightGray, CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                "VERIFICADA",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Secondary,
                                modifier = Modifier
                                    .background(Secondary.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                            Text(
                                "ABIERTO",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF16A34A),
                                modifier = Modifier
                                    .background(Color(0xFFDCFCE7), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Clínica Veterinaria EPN", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                            Icon(Icons.Default.LocationOn, null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                            Text("Av. Reforma 123, Zona Centro", fontSize = 12.sp, color = Color.Gray)
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.Star, null, tint = Color(0xFFFACC15), modifier = Modifier.size(14.dp))
                            Text("4.8", fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Primary.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Icon(Icons.Default.NearMe, null, tint = Primary, modifier = Modifier.size(12.dp))
                            Text("1.2 km", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Primary, modifier = Modifier.padding(start = 2.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Images
                Row(modifier = Modifier.height(100.dp)) {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAiAB_k6v7np-f0I56Ufq1hDHQUoWEobNkmuJoyopzdyDqap5hUPvCam2Wn8v7nkDvzB0W5aiA7bPtp3RQCjOcC-DW0uX4Hw_qoOncI-8k559gdVoYVLIp1Y13iso87d0Dak2chdMQpYF_lzRzupIyg1CCAg_WotDMdBsZGWC5mc8zoMQX2bojgI6gmuJ4fV9GpGiPxssJhqki_BjfVUxWVwtEIW9dUm6tuyu6gX-HLy4-SD5NQw-O3L6QXyZEmRraHoWpWvNhKAVM",
                        contentDescription = "Clinic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        AsyncImage(
                            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDAE7dM48D5vA2T8xXukSPGOaHWOvOUKiUIj6Dc_jT6IVFvJ9jioD1Lzx9DYbl9PlhFSk55kKztAv_vX6-QKQ5Qt62OMkG-xyJDCItwQALNSDCaxArqr5V_fNiP-E6x1MNI84EyZGFbatpchfj8I7ohTWjbH7BpKvINOUWZjuu7H7gqCUsNQoAlWjBhMr53Ql2j-2Nezkp2XZ_kfXFC8Eq6ZpNJCE5bxL0YqBhr0rjVQdD0HL2K8vBy1LNVqZrzf_9MK321gwJMiyg",
                            contentDescription = "Dog",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.4f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("+5", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { Toast.makeText(context, "Llamando...", Toast.LENGTH_SHORT).show() },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, Color.LightGray),
                        modifier = Modifier
                            .size(56.dp)
                            .aspectRatio(1f),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.Call, null, tint = Color.Gray)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = { Toast.makeText(context, "Iniciando Navegación", Toast.LENGTH_SHORT).show() },
                        colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.Directions, null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Navegar", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun MapBackground() {
    Canvas(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF3F4F6))) {

        // Draw grid
        val step = 100.dp.toPx()
        for (x in 0..size.width.toInt() step step.toInt()) {
            drawLine(
                color = Color.White.copy(alpha = 0.5f),
                start = Offset(x.toFloat(), 0f),
                end = Offset(x.toFloat(), size.height),
                strokeWidth = 2f
            )
        }
        for (y in 0..size.height.toInt() step step.toInt()) {
            drawLine(
                color = Color.White.copy(alpha = 0.5f),
                start = Offset(0f, y.toFloat()),
                end = Offset(size.width, y.toFloat()),
                strokeWidth = 2f
            )
        }

        // Draw some "roads"
        val path = Path().apply {
            moveTo(-50f, 400f)
            cubicTo(200f, 300f, 400f, 700f, 800f, 600f)
            quadraticBezierTo(1200f, 200f, 1600f, 400f)
        }
        drawPath(
            path = path,
            color = Color.White,
            style = Stroke(width = 40f)
        )

        val riverPath = Path().apply {
            moveTo(200f, 0f)
            lineTo(200f, 2000f)
        }
        drawPath(
            path = riverPath,
            color = Color.White,
            style = Stroke(width = 30f)
        )
    }
}

@Composable
fun MapPin(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector,
    color: Color,
    scale: Float = 1f,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .scale(scale)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Label
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(4.dp))
                .padding(horizontal = 4.dp, vertical = 2.dp)
                .shadow(2.dp, RoundedCornerShape(4.dp))
        ) {
            Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        // Pin
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color, CircleShape)
                .border(3.dp, Color.White, CircleShape)
                .shadow(4.dp, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
        }
        // Pointer
        Canvas(modifier = Modifier.size(12.dp)) {
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width, 0f)
                lineTo(size.width / 2, size.height)
                close()
            }
            drawPath(path = path, color = color)
        }
    }
}
