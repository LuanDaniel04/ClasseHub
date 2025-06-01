package br.com.SylTech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.SylTech.model.NotesViewModel
import br.com.SylTech.model.ReminderViewModel
import br.com.SylTech.screens.CollectionScreen
import br.com.SylTech.screens.EditNoteScreen
import br.com.SylTech.screens.EditReminderScreen
import br.com.SylTech.screens.HomeScreen
import br.com.SylTech.screens.NewNote
import br.com.SylTech.screens.NewReminderScreen
import br.com.SylTech.screens.NoteDetailScreen
import br.com.SylTech.screens.ReminderScreen
import br.com.SylTech.screens.SplashScreen
import br.com.SylTech.ui.theme.ClasseHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val notesviewModel: NotesViewModel = viewModel()
            val reminderviewModel: ReminderViewModel = viewModel()


            ClasseHubTheme {
            NavHost(
                navController = navController,
                startDestination = "Splash"
            ) {
                composable(route = "Splash") {
                    SplashScreen(navController)
                }
                composable(route = "Home") {
                    HomeScreen(navController, notesviewModel)
                }
                composable(route = "NewNote") {
                    NewNote(navController)
                }

                composable(route = "Collections") {
                    CollectionScreen(navController)
                }

                composable(route = "Reminder") {

                    ReminderScreen(navController,reminderviewModel)
                }

                composable(route = "NewReminder") {
                    NewReminderScreen(navController)
                }

                composable(route = "ReadNote") {
                    NoteDetailScreen(navController, notesviewModel)
                }
                composable(route = "EditNote") {
                    EditNoteScreen(navController,notesviewModel)
                }
                composable(route = "ReadReminder") {
                    EditReminderScreen(navController, reminderviewModel)
                }
            }
            }
        }
    }
}