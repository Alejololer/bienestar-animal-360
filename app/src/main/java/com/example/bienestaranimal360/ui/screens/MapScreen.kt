package com.example.bienestaranimal360.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.*
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
import com.example.bienestaranimal360.ui.theme.*

data class Veterinary(
    val id: Int,
    val name: String,
    val address: String,
    val rating: Double,
    val distance: String,
    val isOpen: Boolean,
    val isVerified: Boolean,
    val icon: ImageVector,
    val offsetX: Int,
    val offsetY: Int,
    val mainImage: String,
    val services: List<String>
)

@Composable
fun MapScreen() {
    val context = LocalContext.current

    val veterinarians = listOf(
        Veterinary(
            1, "VetCare Center", "Av. Amazonas N21-12", 4.9, "0.8 km", true, true,
            Icons.Default.Pets, -100, -150,
            "https://images.unsplash.com/photo-1584132967334-10e028bd69f7?q=80&w=400&auto=format&fit=crop",
            listOf("Rayos X", "Cirugía", "Peluquería")
        ),
        Veterinary(
            2, "Clínica Veterinaria EPN", "Av. Ladrón de Guevara", 4.8, "1.2 km", true, true,
            Icons.Default.Verified, 60, -50,
            "https://images.unsplash.com/photo-1517849845537-4d257902454a?q=80&w=400&auto=format&fit=crop",
            listOf("Urgencias", "Vacunación", "Laboratorio")
        ),
        Veterinary(
            3, "Hospital Animal Norte", "Panamericana Norte km 10", 4.5, "3.5 km", false, false,
            Icons.Default.MedicalServices, -80, 100,
            "https://images.unsplash.com/photo-1628009368231-7bb7cfcb0def?q=80&w=400&auto=format&fit=crop",
            listOf("Ambulancia", "Hospedaje", "Farmacia")
        )
    )

    var selectedVet by remember { mutableStateOf(veterinarians[1]) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. Map Background (Simulated)
        MapBackground()

        // 2. Pins
        veterinarians.forEach { vet ->
            MapPin(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = vet.offsetX.dp, y = vet.offsetY.dp),
                label = vet.name,
                icon = vet.icon,
                color = if (selectedVet.id == vet.id) Secondary else Primary,
                scale = if (selectedVet.id == vet.id) 1.2f else 1.0f,
                onClick = { selectedVet = vet }
            )
        }

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
                    Icon(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Unspecified
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
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Primary else MaterialTheme.colorScheme.surface,
                            contentColor = if (isSelected) Color.White else Color.Gray
                        ),
                        elevation = ButtonDefaults.buttonElevation(2.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(filter, fontSize = 12.sp)
                    }
                }
            }
        }

        // 4. Bottom Sheet (Clinic Details)
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (selectedVet.isVerified) {
                                Text(
                                    "VERIFICADA",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Secondary,
                                    modifier = Modifier
                                        .background(Secondary.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Text(
                                if (selectedVet.isOpen) "ABIERTO" else "CERRADO",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (selectedVet.isOpen) Color(0xFF16A34A) else Color.Red,
                                modifier = Modifier
                                    .background(if (selectedVet.isOpen) Color(0xFFDCFCE7) else Color(0xFFFFEBEE), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(selectedVet.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                            Icon(Icons.Default.LocationOn, null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                            Text(selectedVet.address, fontSize = 12.sp, color = Color.Gray)
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
                            Text(selectedVet.rating.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Primary.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Icon(Icons.Default.NearMe, null, tint = Primary, modifier = Modifier.size(12.dp))
                            Text(selectedVet.distance, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Primary, modifier = Modifier.padding(start = 2.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Image & Services
                Row(modifier = Modifier.height(80.dp), verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = selectedVet.mainImage,
                        contentDescription = "Clinic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(100.dp)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Servicios destacados:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            selectedVet.services.forEach { service ->
                                Text(
                                    service,
                                    fontSize = 10.sp,
                                    modifier = Modifier
                                        .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = { Toast.makeText(context, "Llamando a ${selectedVet.name}", Toast.LENGTH_SHORT).show() },
                        modifier = Modifier.size(48.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Call, null)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = { Toast.makeText(context, "Navegando a ${selectedVet.name}", Toast.LENGTH_SHORT).show() },
                        colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Directions, null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Ver Ruta", fontWeight = FontWeight.Bold)
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
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(4.dp))
                .padding(horizontal = 4.dp, vertical = 2.dp)
                .shadow(2.dp, RoundedCornerShape(4.dp))
        ) {
            Text(label, fontSize = 8.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(2.dp))
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(color, CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .shadow(4.dp, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
        }
        Canvas(modifier = Modifier.size(8.dp)) {
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
