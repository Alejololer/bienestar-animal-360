package com.example.bienestaranimal360.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ContentCut
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.PestControl
import androidx.compose.material.icons.outlined.Vaccines
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bienestaranimal360.R
import com.example.bienestaranimal360.ui.components.GlassCard
import com.example.bienestaranimal360.ui.theme.*

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 80.dp)
            .verticalScroll(scrollState)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { }) {
                Surface(
                    shape = CircleShape,
                    shadowElevation = 2.dp,
                    modifier = Modifier.size(44.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.padding(6.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Bienestar Animal 360",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Hola, Carlos \uD83D\uDC4B",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                }
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface, CircleShape)
                    .padding(4.dp)
            ) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.Gray)
            }
        }

        // Hero Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(40.dp))
        ) {
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAlSPbsNbgKtFiXnjNnlvedvL6ISWfTumy4AyUEvfh1pvVW9JP4VCmjKUwAyhRe28Bf3cLJHzel7y7UCtzn6pxyOJmmvOvwDMrUfUdxEHBtZ7eqlbLeeJ84wyTpzx-VDMtRjeN4Bsstpi1Sqc-gb4mis7kBFuIdkDPfON5UNWVqLgFjbFjxau2Gp1JQickJDgmNLQKSuR71Uw57D-5uljRNrMZ2VLclUbvkrAyIeo-t6Wa3sBakk8QoWsezOBRM39yV_FACiLOjKfM",
                contentDescription = "Bruno",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 300f
                        )
                    )
            )

            GlassCard(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                    val alpha by infiniteTransition.animateFloat(
                        initialValue = 0.3f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "alpha"
                    )

                    Box(modifier = Modifier.size(12.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .scale(1.5f)
                                .alpha(alpha)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.primary, CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ESTADO: SALUDABLE",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Bruno",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            Icons.Default.Verified,
                            contentDescription = "Verified",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = "Golden Retriever",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }

                GlassCard {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "3",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "AÑOS",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }

        // Health & Care Section
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Salud & Cuidados",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                // Vaccine Card
                HealthChipCard(
                    title = "PRÓXIMA VACUNA",
                    date = "12 Nov",
                    statusText = "PENDIENTE",
                    statusColor = Secondary,
                    statusBg = Orange100,
                    icon = Icons.Outlined.Vaccines,
                    iconBg = Orange50,
                    bottomText = "En 2 semanas",
                    bottomIcon = Icons.Default.Schedule,
                    modifier = Modifier.weight(1f)
                )

                // Parasite Card
                HealthChipCard(
                    title = "DESPARASITACIÓN",
                    date = "05 Dic",
                    statusText = "OK",
                    statusColor = Primary,
                    statusBg = Teal100,
                    icon = Icons.Outlined.PestControl,
                    iconBg = Teal50,
                    bottomText = "Al día",
                    bottomIcon = Icons.Outlined.CheckCircle,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Recent History Section
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Historial Reciente",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.AddCircle, null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Nueva Entrada", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            HistoryItem(
                title = "Control de Peso",
                subtitle = "Clínica VetCare • Dr. Martínez",
                value = "28.5 kg",
                time = "Hoy",
                icon = Icons.Outlined.MonitorWeight,
                iconBg = Teal50,
                iconColor = Primary
            )

            HistoryItem(
                title = "Grooming Completo",
                subtitle = "PetSpa Deluxe",
                value = "-$450",
                time = "Ayer",
                icon = Icons.Outlined.ContentCut,
                iconBg = Orange50,
                iconColor = Secondary
            )
        }
    }
}

@Composable
fun HealthChipCard(
    title: String,
    date: String,
    statusText: String,
    statusColor: Color,
    statusBg: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBg: Color,
    bottomText: String,
    bottomIcon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier.height(185.dp) // Adjusted height
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp) // Use spacedBy instead of SpaceBetween to avoid big gaps
        ) {
            // Top Row: Icon and Status Pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(iconBg, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, null, tint = statusColor, modifier = Modifier.size(20.dp))
                }
                Surface(
                    shape = CircleShape,
                    color = statusBg,
                    modifier = Modifier.width(80.dp)
                ) {
                    Text(
                        text = statusText,
                        color = statusColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp),
                        maxLines = 1
                    )
                }
            }

            // Content Column
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    fontSize = 12.sp // Slightly adjusted
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(bottomIcon, null, tint = statusColor, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = bottomText,
                        style = MaterialTheme.typography.labelSmall,
                        color = statusColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryItem(
    title: String,
    subtitle: String,
    value: String,
    time: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBg: Color,
    iconColor: Color
) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(iconBg, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = iconColor, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(subtitle, fontSize = 11.sp, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(time, fontSize = 11.sp, color = if (time == "Hoy") iconColor else Color.Gray)
            }
        }
    }
}
