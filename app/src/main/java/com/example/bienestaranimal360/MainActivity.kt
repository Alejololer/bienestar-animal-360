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
            modifier = Modifier.padding(innerPadding) // This padding handles the bottom bar space
        ) {
            composable("home") {
                // We don't want the bottom bar to overlap the content,
                // but the design shows content behind it sometimes or specific spacing.
                // Scaffold padding usually ensures content doesn't go behind bottom bar.
                // However, our screens have their own internal padding/scroll.
                // We'll pass modifier or just let Scaffold handle it.
                // Since our screens have `padding(bottom = 80.dp)` hardcoded in them to allow scroll behind transparent nav
                // (if we had one), but here we are using a standard Scaffold BottomBar which pushes content up.
                // Let's remove the hardcoded bottom padding in screens if Scaffold handles it,
                // OR we make Scaffold use a transparent bottom bar over content.
                // The design shows the Nav Bar as a distinct block at the bottom.
                // Standard behavior: content is above bottom bar.

                // My screens currently have `padding(bottom = 80.dp)`.
                // If I use Scaffold innerPadding, I get double padding or correct padding.
                // Let's use a Box to ignore innerPadding for the bottom if we want "behind" effect,
                // but the design seems to have a solid white nav bar.
                // So standard Scaffold behavior is best.
                // I will wrap the screens in a Box that consumes the padding.

                Box(modifier = Modifier.fillMaxSize()) {
                    HomeScreen()
                }
            }
            composable("history") {
                Box(modifier = Modifier.fillMaxSize()) {
                    HistoryScreen()
                }
            }
            composable("map") {
                Box(modifier = Modifier.fillMaxSize()) {
                    MapScreen()
                }
            }
        }
    }
}
