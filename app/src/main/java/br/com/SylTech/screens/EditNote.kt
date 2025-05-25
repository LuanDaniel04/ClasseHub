package br.com.SylTech.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.SylTech.model.NotesViewModel
import br.com.SylTech.repository.NotesRepository

@Composable
fun EditNoteScreen(navController: NavController,viewModel: NotesViewModel) {

    val note = viewModel.notes!!
    val context = LocalContext.current

    var titleText by remember { mutableStateOf(note.title) }
    var noteText by remember { mutableStateOf(note.note) }
    var titleError by remember { mutableStateOf(false) }
    var noteError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            CenterAlignedTopAppBar(
                title = { Text("Edit Note") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF615690),
                    titleContentColor = Color(0xFFE6DEFF),
                    navigationIconContentColor = Color(0xFFE6DEFF)
                ),
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "") }
                },
                modifier = Modifier.padding(
                    bottom = 12.dp
                )
            )
        },
        containerColor = Color(0xFFE6DEFF),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    val titleValid = titleText.trim().isNotEmpty()
                    val noteValid = noteText.trim().isNotEmpty()

                    titleError = !titleValid
                    noteError = !noteValid

                    if (titleValid && noteValid) {
                        val repository = NotesRepository(context)
                        val updatedNote = note.copy(
                            title = titleText.trim(),
                            note = noteText.trim(),
                        )
                        val result = repository.Update(updatedNote)
                        if (result > 0) {
                            navController.navigate("Home") {
                                popUpTo("Home")
                            }
                        }
                    }

                },
                elevation = FloatingActionButtonDefaults.elevation(6.dp),
                containerColor = Color(0xFF615690)
            ) {
                Icon(Icons.Outlined.Done, "", tint = Color(0xFFE6DEFF))
            }
        },
    ) { innerPadding ->
        Column(
            Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 12.dp
                )
                .fillMaxSize()
        ) {
            ElevatedCard(
                Modifier.fillMaxSize(),
                RoundedCornerShape(12.dp),
                CardDefaults.cardColors(Color(0xFFEFE4FF)),
                CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = titleText,
                        onValueChange = { value ->
                            titleText = value
                        },
                        isError = titleError,
                        supportingText = {
                            if (titleError) Text("Title cannot be empty")
                        },
                        placeholder = { Text("Title", color = Color(0xFF847F8E)) },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color(0xFFFFDAD6),

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            errorTextColor = Color.Black,

                            cursorColor = Color(0xFF615690),
                            errorCursorColor = Color(0xFFBA1A1A),

                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color(0xFFBA1A1A),

                            errorPlaceholderColor = Color(0xFFBA1A1A)
                        ),
                        singleLine = true,
                        maxLines = 1
                    )

                    OutlinedTextField(
                        value = noteText,
                        onValueChange = { value ->
                            noteText = value
                        },
                        isError = noteError,
                        supportingText = {
                            if (noteError) Text("Note cannot be empty")
                        },
                        placeholder = { Text("Write it down before you forget!", color = Color(0xFF847F8E)) },
                        modifier = Modifier
                            .fillMaxSize(),
                        colors = TextFieldDefaults.colors(

                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color(0xFFFFDAD6),

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            errorTextColor = Color.Black,

                            cursorColor = Color(0xFF615690),
                            errorCursorColor = Color(0xFFBA1A1A),

                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color(0xFFBA1A1A),

                            errorPlaceholderColor = Color(0xFFBA1A1A)

                        ),
                        singleLine = false,
                        maxLines = Int.MAX_VALUE
                    )
                }
            }
        }
    }
}
