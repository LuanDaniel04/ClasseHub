package br.com.SylTech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.SylTech.screens.CollectionScreen
import br.com.SylTech.screens.HomeScreen
import br.com.SylTech.screens.NewNote
import br.com.SylTech.screens.NewReminderScreen
import br.com.SylTech.screens.ReminderScreen
import br.com.SylTech.screens.SplashScreen
import br.com.SylTech.ui.theme.ClasseHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        val navController = rememberNavController();
            ClasseHubTheme {
            NavHost(
                navController = navController,
                startDestination = "Splash"
            ) {
                composable(route = "Splash") {
                    SplashScreen(navController)
                }
                composable(route = "Home") {
                    HomeScreen(navController)
                }
                composable(route = "NewNote") {
                    NewNote(navController)
                }

                composable(route = "Collections") {
                    CollectionScreen(navController)
                }

                composable(route = "Reminder") {
                    ReminderScreen(navController)
                }

                composable(route = "NewReminder") {
                    NewReminderScreen(navController)
                }
            }
            }
        }
    }
}