package br.com.SylTech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
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
import br.com.SylTech.model.NotesViewModel
import br.com.SylTech.screens.EditNoteScreen
import br.com.SylTech.screens.NoteDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val viewModel: NotesViewModel = viewModel()


            ClasseHubTheme {
            NavHost(
                navController = navController,
                startDestination = "Home"
            ) {
                composable(route = "Splash") {
                    SplashScreen(navController)
                }
                composable(route = "Home") {
                    HomeScreen(navController, viewModel)
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

                composable(route = "ReadNote") {
                    NoteDetailScreen(navController, viewModel)
                }
                composable(route = "EditNote") {
                    EditNoteScreen(navController,viewModel)
                }
            }
            }
        }
    }
}