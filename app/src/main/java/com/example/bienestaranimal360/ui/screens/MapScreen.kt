package com.example.bienestaranimal360.ui.screens

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
import androidx.compose.material.icons.filled.*
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
        modifier = Modifier.fillMaxSize().background(BackgroundDark)
    ) {
        // 1. Map Background (Dark Theme Consistent)
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
                .padding(top = 40.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(SurfaceDark, RoundedCornerShape(12.dp))
                        .shadow(6.dp, RoundedCornerShape(12.dp))
                        .padding(6.dp)
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
                        .background(SurfaceDark, CircleShape)
                        .shadow(6.dp, CircleShape)
                        .clickable { }
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Search, null, tint = SubtextDark)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Buscar veterinarias...", color = SubtextDark, fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(SurfaceDark, CircleShape)
                        .shadow(6.dp, CircleShape)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Tune, null, tint = Primary)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Centered Filters
            LazyRow(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listOf("Cerca de mí", "24 Horas", "Urgencias", "Peluquería")) { filter ->
                    val isSelected = filter == "Cerca de mí"
                    Surface(
                        onClick = { },
                        shape = CircleShape,
                        color = if (isSelected) Primary else SurfaceDark,
                        contentColor = if (isSelected) Color.White else TextDark,
                        shadowElevation = 4.dp,
                        modifier = Modifier.padding(horizontal = 4.dp).height(34.dp)
                    ) {
                        Text(
                            filter, 
                            fontSize = 12.sp, 
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        // 4. Info Card (Dark Theme)
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceDark),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
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
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFFFDE68A), // Amber
                                    modifier = Modifier
                                        .background(Color(0xFF92400E).copy(alpha = 0.3f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Text(
                                if (selectedVet.isOpen) "ABIERTO" else "CERRADO",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = if (selectedVet.isOpen) Color(0xFF4ADE80) else Color(0xFFF87171),
                                modifier = Modifier
                                    .background(if (selectedVet.isOpen) Color(0xFF064E3B).copy(alpha = 0.3f) else Color(0xFF7F1D1D).copy(alpha = 0.3f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(selectedVet.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextDark)
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                            Icon(Icons.Default.LocationOn, null, tint = SubtextDark, modifier = Modifier.size(14.dp))
                            Text(selectedVet.address, fontSize = 12.sp, color = SubtextDark)
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color(0xFF78350F).copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.Star, null, tint = Color(0xFFFBBF24), modifier = Modifier.size(14.dp))
                            Text(selectedVet.rating.toString(), fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFFFBBF24), modifier = Modifier.padding(start = 4.dp))
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(selectedVet.distance, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, color = Primary)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.height(100.dp)) {
                    AsyncImage(
                        model = selectedVet.mainImage,
                        contentDescription = "Clinic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1.2f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Servicios:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextDark)
                        Spacer(modifier = Modifier.height(4.dp))
                        selectedVet.services.forEach { service ->
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 1.dp)) {
                                Box(modifier = Modifier.size(4.dp).background(Primary, CircleShape))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(service, fontSize = 11.sp, color = SubtextDark, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.height(48.dp).weight(0.3f),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.5.dp, SubtextDark.copy(alpha = 0.5f))
                    ) {
                        Icon(Icons.Default.Call, null, tint = TextDark)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Directions, null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cómo llegar", fontWeight = FontWeight.Bold, color = Color.White)
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
        .background(BackgroundDark)) {

        val step = 80.dp.toPx()
        // Grid lines
        for (x in 0..size.width.toInt() step step.toInt()) {
            drawLine(
                color = Color.White.copy(alpha = 0.05f),
                start = Offset(x.toFloat(), 0f),
                end = Offset(x.toFloat(), size.height),
                strokeWidth = 1f
            )
        }

        // Main Roads
        val roadColor = SurfaceDark
        val roadPath = Path().apply {
            moveTo(-50f, size.height * 0.3f)
            quadraticBezierTo(size.width * 0.5f, size.height * 0.2f, size.width + 50f, size.height * 0.5f)
            
            moveTo(size.width * 0.3f, -50f)
            lineTo(size.width * 0.4f, size.height + 50f)
        }
        drawPath(path = roadPath, color = roadColor, style = Stroke(width = 45f))

        // River
        val riverColor = Color(0xFF1E293B).copy(alpha = 0.5f)
        val riverPath = Path().apply {
            moveTo(size.width * 0.8f, -50f)
            cubicTo(size.width * 0.7f, size.height * 0.3f, size.width * 0.9f, size.height * 0.7f, size.width * 0.8f, size.height + 50f)
        }
        drawPath(path = riverPath, color = riverColor, style = Stroke(width = 60f))
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
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = SurfaceDark,
            shadowElevation = 8.dp,
            border = BorderStroke(1.dp, SubtextDark.copy(alpha = 0.3f))
        ) {
            Text(
                text = label, 
                fontSize = 10.sp, 
                fontWeight = FontWeight.ExtraBold,
                color = TextDark,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(color, CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .shadow(8.dp, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon, 
                contentDescription = null, 
                tint = Color.White, 
                modifier = Modifier.size(18.dp)
            )
        }

        Canvas(modifier = Modifier.size(10.dp).offset(y = (-2).dp)) {
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
