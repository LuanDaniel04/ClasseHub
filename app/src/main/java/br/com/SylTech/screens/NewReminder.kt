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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NewReminderScreen(navController: NavController) {

    val textReminder = remember { mutableStateOf("") }
    val textReminder2 = remember { mutableStateOf("") }

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
                }
            )
        }
    ) {innerPadding ->
        Column(Modifier
            .padding(
                top = innerPadding.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp

            )
            .fillMaxSize()) {
        ElevatedCard(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 16.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color(0xFFEFE4FF)
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
        ) {
            Column(
                Modifier.fillMaxSize().padding(16.dp)
            ) {
                TextField(
                    value = textReminder.value,
                    onValueChange = { newReminder -> textReminder.value = newReminder },
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
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,

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

                TextField(
                    value = textReminder2.value,
                    onValueChange = {newReminder -> textReminder.value = newReminder},
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
                            "reminder description",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF8A8A8E)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,

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
                TextField(
                    value = textReminder2.value,
                    onValueChange = {newReminder -> textReminder.value = newReminder},
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
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,

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
            }
          }
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF615690)), modifier = Modifier.width(500.dp)) {
                Row {
                    Icon(Icons.Outlined.Done, "", tint = Color.White)
                    Text("Set Reminder", style = MaterialTheme.typography.labelLarge, color = Color.White)
                }
            }
        }
    }
}