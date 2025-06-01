package br.com.SylTech.screens



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.SylTech.model.ReminderViewModel
import br.com.SylTech.repository.ReminderRepository
import java.util.Calendar
import java.util.TimeZone

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditReminderScreen(navController: NavController, viewModel: ReminderViewModel) {

    val context = LocalContext.current
    val reminder = viewModel.reminder!!

    var textTitle by remember { mutableStateOf(reminder.titulo) }
    var textNote  by  remember { mutableStateOf(reminder.nota) }
    var textDate by remember { mutableStateOf(reminder.data) }
    var textTime by remember { mutableStateOf(reminder.hora) }

    var errorTitle by remember { mutableStateOf(false) }
    var errorNote by remember { mutableStateOf(false) }
    var errorDate by remember { mutableStateOf(false) }
    var errorTime by remember { mutableStateOf(false) }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }




    Scaffold(
        Modifier.fillMaxSize(),
        containerColor = Color(0xFFE6DEFF),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("New Reminder", style = MaterialTheme.typography.titleLarge, color = Color(0xFFE6DEFF))
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF615690)
                ),
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {Icon(Icons.AutoMirrored.Outlined.ArrowBack,"", tint = Color(0xFFE6DEFF))}
                },
                actions = {
                    IconButton(onClick = {
                        showDialog = true
                    }) {
                        Icon(Icons.Outlined.Delete, null, tint = Color.Red)
                    }
                }
            )
        },
    ) {innerPadding ->
        Column(Modifier.padding(
            top = innerPadding.calculateTopPadding(),
            start = 16.dp,
            end = 16.dp
        ).fillMaxSize()
        ) {
            ElevatedCard(
                Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(top = 16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(0xFFEFE4FF)
                ),
                elevation = CardDefaults.elevatedCardElevation(4.dp),
            ) {
                Column(
                    Modifier.fillMaxSize().padding(16.dp)
                ) {

                    // Titulo

                    TextField(
                        value = textTitle,
                        onValueChange = { newReminder -> textTitle = newReminder },
                        modifier = Modifier.width(400.dp),
                        label = {
                            Text(
                                "Title",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Black
                            )
                        },
                        placeholder = {
                            Text(
                                "Write your title",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF8A8A8E)
                            )
                        },
                        isError = errorTitle,
                        supportingText = {
                            if (errorTitle) Text("Title cannot be empty")
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            errorContainerColor = Color(0xFFFFDAD6),

                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,

                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red,

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            errorTextColor = Color.Black
                        )
                    )

                    Spacer(Modifier.height(8.dp))

                    //Anotação

                    TextField(
                        value = textNote,
                        onValueChange = {newNote -> textNote = newNote},
                        modifier = Modifier.size(400.dp, 100.dp),
                        label = {
                            Text(
                                "Note",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Black
                            )
                        },
                        placeholder = {
                            Text(
                                "Reminder description",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF8A8A8E)
                            )
                        },
                        isError = errorNote,
                        supportingText = {
                            if (errorNote) Text("Note cannot be empty")
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            errorContainerColor = Color(0xFFFFDAD6),

                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,

                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red,

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            errorTextColor = Color.Black
                        )
                    )

                    Spacer(Modifier.height(8.dp))

                    //Data

                    TextField(
                        value = textDate,
                        onValueChange = {textDate = it},
                        modifier = Modifier.width(400.dp),
                        label = {
                            Text(
                                "Date",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Black
                            )
                        },
                        placeholder = {
                            Text(
                                "13/05/1888",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF8A8A8E)
                            )
                        },
                        isError = errorDate,
                        supportingText = {
                            if (errorDate) Text("Date cannot be empty")
                        },
                        trailingIcon = { IconButton(
                            onClick = {
                                showDatePicker = true
                            }
                        ) {
                            Icon(Icons.Outlined.DateRange, "", tint = Color(0xFF48454E))
                        } },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            errorContainerColor = Color(0xFFFFDAD6),

                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,

                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red,

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            errorTextColor = Color.Black
                        ),
                        readOnly = true,
                    )

                    Spacer(Modifier.height(8.dp))

                    //Horas

                    TextField(
                        value = textTime,
                        onValueChange = { textTime = it },
                        modifier = Modifier.width(400.dp),
                        label = {
                            Text("Time", style = MaterialTheme.typography.bodySmall, color = Color.Black)
                        },
                        placeholder = {
                            Text("11:30", style = MaterialTheme.typography.bodySmall, color = Color(0xFF8A8A8E))
                        },
                        isError = errorTime,
                        supportingText = {
                            if (errorTime) Text("Time cannot be empty")
                        },
                        trailingIcon = {
                            IconButton(onClick = { showTimePicker = true }) {
                                Icon(Icons.Outlined.DateRange, "", tint = Color(0xFF48454E))
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            errorContainerColor = Color(0xFFFFDAD6),

                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            errorLeadingIconColor = Color.Black,

                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red,

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            errorTextColor = Color.Black
                        ),
                        readOnly = true
                    )

                    Spacer(Modifier.weight(1f))

                    Button(onClick = {
                        val titleValid = textTitle.trim().isNotEmpty()
                        val noteValid = textNote.trim().isNotEmpty()
                        val dateValid = textDate.trim().isNotEmpty()
                        val timeValid = textTime.trim().isNotEmpty()

                        errorTitle = !titleValid
                        errorNote = !noteValid
                        errorDate = !dateValid
                        errorTime = !timeValid

                        if (titleValid && noteValid && dateValid && timeValid) {
                            val repository = ReminderRepository(context)
                            val updateReminder = reminder.copy(
                                titulo = textTitle.trim(),
                                nota = textNote.trim(),
                                data = textDate.trim(),
                                hora = textTime.trim()
                            )
                            val result = repository.Update(updateReminder)
                            if (result > 0) {
                                navController.navigate("Reminder") {
                                    popUpTo("Reminder") {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                    }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF615690)), modifier = Modifier.width(400.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Outlined.Done, "", tint = Color.White)

                            Spacer(Modifier.width(4.dp))

                            Text("Edit Reminder", style = MaterialTheme.typography.bodyLarge, color = Color.White)
                        }
                    }

                }
            }

            if (showDatePicker) {
                Data(
                    onDateSelected = {
                            millis -> millis?.let {
                        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                        calendar.timeInMillis = it
                        val day = calendar.get(Calendar.DAY_OF_MONTH)
                        val month = calendar.get(Calendar.MONTH) + 1
                        val year = calendar.get(Calendar.YEAR)
                        textDate = String.format("%02d/%02d/%04d", day, month, year)
                    }
                        showDatePicker = false
                    },
                    onDismiss = { showDatePicker = false}
                )
            }

            if (showTimePicker) {
                Horario(
                    onTimeSelected = { hour, minute ->
                        textTime = String.format("%02d:%02d", hour, minute)
                        showTimePicker = false
                    },
                    onDismiss = { showTimePicker = false }
                )
            }

            if (showDialog) {
                CustomAlertDialog(
                    titulo = "Are You Sure?",
                    texto = "Are you sure you want to delete this Reminder? This action cannot be undone.",
                    icone = Icons.Outlined.Delete,
                    onDismissRequest = {
                        showDialog = false
                    },
                    onConfirm = {
                        ReminderRepository(context).Delete(reminder.id!!)
                        showDialog = false
                        navController.navigate("Reminder") {
                            popUpTo("Reminder") {inclusive = true}
                        }
                    },
                )  }


        }
    }
}


