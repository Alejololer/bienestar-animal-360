package com.example.bienestaranimal360.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.ContentCut
import androidx.compose.material.icons.outlined.Vaccines
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.bienestaranimal360.R
import com.example.bienestaranimal360.ui.components.GlassCard
import com.example.bienestaranimal360.ui.theme.*

@Composable
fun HistoryScreen() {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 80.dp)
    ) {
        item {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .padding(top = 24.dp)
            ) {
                // Top Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { Toast.makeText(context, "Atrás", Toast.LENGTH_SHORT).show() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "BIENESTAR ANIMAL 360",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }

                    IconButton(onClick = { Toast.makeText(context, "QR Scanner", Toast.LENGTH_SHORT).show() }) {
                        Icon(Icons.Default.QrCodeScanner, contentDescription = "QR", tint = Primary)
                    }
                }

                // Pet Profile Summary
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(
                                    androidx.compose.ui.graphics.Brush.linearGradient(
                                        colors = listOf(Primary, Secondary)
                                    )
                                )
                                .padding(3.dp)
                        ) {
                            AsyncImage(
                                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuCfNCwusRd2BztqKZud7NxKNFJZu-Zk3J2grUQF8oVozeFmsKfcEszM5Ai39CuTXKIRW5mchylYWq8apTd5dLl1wkPdJszxjs4Q4_ShmtV4OO3BKspcpwW2mg0mxioRTR4g7GZWzTAQNW8KhybKgcjAfH90iOLFrLAJnECXr99Ge-E5yOAb44quUoiLHxJEKD5jTr3tkw2hztCdq_0U1ttKQuuZGcA6kI2E8SmJubTVf-J4u2ocEpaxSzqCzuAbGHuLd28x7x8G5N4",
                                contentDescription = "Max",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .border(2.dp, MaterialTheme.colorScheme.background, CircleShape)
                            )
                        }
                        // Age badge
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(Secondary, CircleShape)
                                .border(2.dp, MaterialTheme.colorScheme.background, CircleShape)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                "3.5 años",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Max",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Beagle • Macho • 12.5 kg",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }

                // Filter Tabs
                LazyRow(
                    modifier = Modifier.padding(top = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listOf("Historial", "Vacunas", "Desparasitación", "Cirugías")) { tab ->
                        val isSelected = tab == "Historial"
                        Button(
                            onClick = { Toast.makeText(context, "Filtro: $tab", Toast.LENGTH_SHORT).show() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) Primary else MaterialTheme.colorScheme.surface,
                                contentColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            ),
                            border = if (!isSelected) BorderStroke(1.dp, Color.Gray.copy(alpha = 0.2f)) else null,
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = if (isSelected) 4.dp else 0.dp)
                        ) {
                            Text(tab)
                        }
                    }
                }
            }
        }

        // Next Visit
        item {
            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "PRÓXIMA VISITA",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "15 Nov, 2023",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Refuerzo Vacuna Sextuple",
                            style = MaterialTheme.typography.bodySmall,
                            color = Primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Teal50, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.CalendarToday, null, tint = Primary)
                    }
                }
            }
        }

        // Timeline Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Historial Médico",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Reciente",
                        fontSize = 10.sp,
                        modifier = Modifier
                            .background(Color.LightGray.copy(alpha = 0.3f), CircleShape)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
                Button(
                    onClick = { Toast.makeText(context, "Agregar Registro", Toast.LENGTH_SHORT).show() },
                    colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Icon(Icons.Default.Add, null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Agregar", fontSize = 12.sp)
                }
            }
        }

        // Timeline Items
        item {
            TimelineItem(
                icon = Icons.Default.MedicalServices,
                iconColor = Color.Blue,
                bgIconColor = Color(0xFFE0F2FE),
                category = "Chequeo General",
                date = "24 Oct, 2023",
                title = "Consulta Anual",
                desc = "Peso estable. Dentadura en buen estado. Se recomienda limpieza dental preventiva.",
                verifiedBy = "VetCare Central",
                actionText = "Ver Informe",
                lineColor = Primary
            )
            TimelineItem(
                icon = Icons.Outlined.Vaccines,
                iconColor = Color(0xFFEA580C),
                bgIconColor = Color(0xFFFFEDD5),
                category = "Vacunación",
                date = "10 Ago, 2023",
                title = "Vacuna Antirrábica",
                desc = "Lote #4521-B. Sin reacciones adversas inmediatas.",
                verifiedBy = "Clínica San Francisco",
                actionText = "Certificado",
                lineColor = Secondary
            )
            TimelineItem(
                icon = Icons.Default.Warning,
                iconColor = Color.Red,
                bgIconColor = Color(0xFFFEE2E2),
                category = "Diagnóstico",
                date = "15 Jun, 2023",
                title = "Alergias Detectadas",
                desc = "Reacción leve al polen. Se prescribió tratamiento antihistamínico temporal.",
                verifiedBy = "Dr. Martinez",
                actionText = "Receta",
                lineColor = Color.Gray
            )
            TimelineItem(
                icon = Icons.Outlined.ContentCut,
                iconColor = Color.Magenta,
                bgIconColor = Color(0xFFFAE8FF),
                category = "Higiene",
                date = "02 Feb, 2023",
                title = "Corte de Uñas",
                desc = null,
                verifiedBy = "PetSpa",
                actionText = null,
                lineColor = Color.Gray,
                isLast = true
            )
        }
    }
}

@Composable
fun TimelineItem(
    icon: ImageVector,
    iconColor: Color,
    bgIconColor: Color,
    category: String,
    date: String,
    title: String,
    desc: String?,
    verifiedBy: String,
    actionText: String?,
    lineColor: Color,
    isLast: Boolean = false
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(IntrinsicSize.Min)
    ) {
        // Timeline Line Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(20.dp)
        ) {
            // Dot
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(MaterialTheme.colorScheme.background, CircleShape)
                    .border(3.dp, lineColor, CircleShape)
                    .zIndex(1f)
            )
            // Line
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp)
                        .background(Color.LightGray.copy(alpha = 0.5f))
                )
            }
        }

        // Content
        Spacer(modifier = Modifier.width(16.dp))

        GlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            onClick = { Toast.makeText(context, "Detalles de $title", Toast.LENGTH_SHORT).show() }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(bgIconColor, RoundedCornerShape(8.dp))
                                .padding(4.dp)
                        ) {
                            Icon(icon, null, tint = iconColor, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            category,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                    Text(
                        date,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                if (desc != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        desc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        lineHeight = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Verified, null, tint = Color.Green, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            verifiedBy,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (actionText != null) {
                        Text(
                            actionText,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Primary,
                            modifier = Modifier.clickable {
                                Toast.makeText(context, actionText, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}
