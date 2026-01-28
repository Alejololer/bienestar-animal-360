package com.example.bienestaranimal360.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bienestaranimal360.ui.theme.Primary
import com.example.bienestaranimal360.ui.theme.Secondary

data class PetMock(val name: String, val breed: String, val age: String, val imageUrl: String, val isVerified: Boolean = false)

@Composable
fun ProfileScreen() {
    val userPets = listOf(
        PetMock(
            "Bruno",
            "Golden Retriever",
            "3 años",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuAlSPbsNbgKtFiXnjNnlvedvL6ISWfTumy4AyUEvfh1pvVW9JP4VCmjKUwAyhRe28Bf3cLJHzel7y7UCtzn6pxyOJmmvOvwDMrUfUdxEHBtZ7eqlbLeeJ84wyTpzx-VDMtRjeN4Bsstpi1Sqc-gb4mis7kBFuIdkDPfON5UNWVqLgFjbFjxau2Gp1JQickJDgmNLQKSuR71Uw57D-5uljRNrMZ2VLclUbvkrAyIeo-t6Wa3sBakk8QoWsezOBRM39yV_FACiLOjKfM",
            isVerified = true
        ),
        PetMock(
            "Luna",
            "Husky Siberiano",
            "2 años",
            "https://images.unsplash.com/photo-1537151608828-ea2b11777ee8?q=80&w=388&auto=format&fit=crop"
        ),
        PetMock(
            "Mimi",
            "Gato Persa",
            "1 año",
            "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?q=80&w=400&auto=format&fit=crop"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header / User Info (Carlos)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Primary)
                .padding(top = 40.dp, bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(4.dp, Color.White, CircleShape)
                        .background(Color.White)
                ) {
                    AsyncImage(
                        model = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=400&auto=format&fit=crop",
                        contentDescription = "Carlos Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Carlos Mendoza",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "carlos.mendoza@example.com",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mis Mascotas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    TextButton(onClick = { /* Add pet */ }) {
                        Icon(Icons.Default.Add, contentDescription = null, size = 18.dp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Añadir")
                    }
                }
            }

            items(userPets) { pet ->
                PetCard(pet)
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Cuenta y Configuración",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            item {
                SettingsItem(icon = Icons.Default.Pets, title = "Carnet Digital de Vacunación")
                SettingsItem(icon = Icons.Default.LocationOn, title = "Direcciones Guardadas")
                SettingsItem(icon = Icons.Default.CreditCard, title = "Métodos de Pago")
                SettingsItem(icon = Icons.Default.Notifications, title = "Preferencias de Notificación")
                SettingsItem(icon = Icons.Default.ExitToApp, title = "Cerrar Sesión", color = Color.Red)
            }
            
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun PetCard(pet: PetMock) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = pet.imageUrl,
                contentDescription = pet.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(pet.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    if (pet.isVerified) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Default.Verified, contentDescription = null, tint = Primary, modifier = Modifier.size(14.dp))
                    }
                }
                Text(pet.breed, color = Color.Gray, fontSize = 14.sp)
                Text(pet.age, color = Secondary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
            IconButton(onClick = { /* Edit pet */ }) {
                Icon(Icons.Default.ChevronRight, contentDescription = "Edit", tint = Color.LightGray)
            }
        }
    }
}

@Composable
fun SettingsItem(icon: ImageVector, title: String, color: Color = MaterialTheme.colorScheme.onSurface) {
    Surface(
        onClick = { /* Navigate */ },
        color = Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, fontSize = 16.sp, color = color, modifier = Modifier.weight(1f))
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(20.dp))
        }
    }
}
