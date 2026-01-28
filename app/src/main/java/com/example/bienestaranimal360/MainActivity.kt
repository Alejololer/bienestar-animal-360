package com.example.bienestaranimal360

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bienestaranimal360.ui.components.BottomNavBar
import com.example.bienestaranimal360.ui.screens.HistoryScreen
import com.example.bienestaranimal360.ui.screens.HomeScreen
import com.example.bienestaranimal360.ui.screens.MapScreen
import com.example.bienestaranimal360.ui.screens.ProfileScreen
import com.example.bienestaranimal360.ui.theme.BienestarAnimal360Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BienestarAnimal360Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen()
            }
            composable("history") {
                HistoryScreen()
            }
            composable("map") {
                MapScreen()
            }
            composable("profile") {
                ProfileScreen()
            }
        }
    }
}
