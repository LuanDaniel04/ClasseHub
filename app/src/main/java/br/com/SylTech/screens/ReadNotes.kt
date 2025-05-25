package br.com.SylTech.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.SylTech.model.NotesViewModel
import br.com.SylTech.repository.NotesRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(navController: NavController, viewModel: NotesViewModel) {
    val note = viewModel.notes!!
    val context = LocalContext.current

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFFE6DEFF),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Read Note") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Outlined.ArrowBack,"")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            NotesRepository(context).Delete(note.id!!)
                            navController.navigate("Home")
                        }) {
                            Icon(Icons.Outlined.Delete,"", tint = Color.Red)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF615690),
                        titleContentColor = Color(0xFFE6DEFF),
                        navigationIconContentColor = Color(0xFFE6DEFF)
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.select(note)
                        navController.navigate("EditNote")
                    },
                    containerColor = Color(0xFF615690),
                ) {
                    Icon(Icons.Outlined.Edit,"", tint = Color(0xFFE6DEFF))
                }
            }
        ) { innerPadding ->
            Column(
                Modifier.fillMaxSize().padding(innerPadding),
                ) {
                ElevatedCard(
                    Modifier.padding(16.dp).fillMaxSize(),
                    colors = androidx.compose.material3.CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = androidx.compose.material3.CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )

                ) {
                   LazyColumn(
                       Modifier.fillMaxSize().padding(12.dp),

                   ) {
                       item{
                           Text(
                               note.title,
                               Modifier.fillMaxWidth(),
                               style = MaterialTheme.typography.titleLarge,
                               fontWeight = FontWeight.Bold,
                               color = Color.Black,
                               maxLines = 1,
                               textAlign = TextAlign.Center
                           )
                       }
                       item{
                           Spacer(modifier = Modifier.height(12.dp))
                           Text(
                               note.note,
                               Modifier.fillMaxWidth(),
                               style = MaterialTheme.typography.bodyMedium,
                               color = Color.Black,
                               textAlign = TextAlign.Start
                               )
                       }
                   }
                }
            }
        }
    }

