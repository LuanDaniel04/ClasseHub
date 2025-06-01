package br.com.SylTech.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.SylTech.model.Reminder
import br.com.SylTech.model.ReminderViewModel
import br.com.SylTech.repository.ReminderRepository

@Composable
fun ReminderScreen(navController: NavController, viewModel: ReminderViewModel) {

    val query = remember { mutableStateOf("") }
    val context = LocalContext.current
    val listOfReminder = ReminderRepository(context).Read()
    val filteredReminder = listOfReminder.filter {
                it.titulo.contains(query.value, ignoreCase = true) || it.data.contains(query.value, ignoreCase = true) ||
                it.hora.contains(query.value, ignoreCase = true)
    }

    Scaffold(
        Modifier.fillMaxSize(),
        containerColor = Color(0xFFE6DEFF),
        topBar = {
            CustomSearchBar(query)
        },
        bottomBar = { CustomReminderBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("NewReminder")},
                containerColor = Color(0xFF615690),
            ) {
                Icon(Icons.Filled.Add, null, tint = Color(0xFFE6DEFF))
            }
        }
    ) { innerPadding ->

        if (filteredReminder.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomEmptyReminder(navController)
            }
            } else {
                LazyColumn(
                    Modifier.fillMaxSize().padding(innerPadding)
                ) {

                    items(filteredReminder.size) { index ->
                        val reminder: Reminder = filteredReminder[index]
                        CustomReminderCard(navController,viewModel,reminder) {
                            ReminderRepository(context).Delete(reminder.id!!)
                            navController.navigate("Reminder") {
                                popUpTo("Reminder") { inclusive = true }
                            }

                        }
                        Spacer(Modifier.height(8.dp))
                }

            }
        }
    }
}


@Composable
fun CustomReminderBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color(0xFF615690),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        //Icon1
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            IconButton(onClick = {
                navController.navigate("Home") {
                    popUpTo("Reminder") { inclusive = true }
                }
            }) {
                Icon(Icons.Outlined.Home, null,Modifier.size(35.dp), Color.Black)
            }
            Text(
                text = "Home",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }
        //Icon2
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            IconButton(onClick = {
                navController.navigate("Collections") {
                    popUpTo("Reminder") { inclusive = true }
                }
            }) {
                Icon(Icons.Outlined.Star, null, Modifier.size(35.dp),Color.Black)
            }
            Text(
                text = "Collections",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }
        //Icon3
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            IconButton(onClick = {
                navController.navigate("Reminder") {
                    popUpTo("Reminder") { inclusive = true }
                }
            }) {
                Icon(Icons.Outlined.Notifications, null, Modifier.size(35.dp), Color(0xFFE6DEFF))
            }
            Text(
                text = "Reminders",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

//Card da Tela Vazia

@Composable
fun CustomEmptyReminder(navController: NavController) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.size(width = 312.dp, height = 227.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(4.dp))
                Text(
                    "No Reminders Found!",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp, top = 5.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    "You're all caught up! Add a reminder to keep track of your tasks and study goals",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF48454E)
                )
            }
            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {navController.navigate("NewReminder")},
                colors = ButtonDefaults.buttonColors(Color(0xFF615690)),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Add Reminder", color = Color.White)
            }

        }
    }
}

@Composable
fun CustomReminderCard( navController: NavController,viewModel: ReminderViewModel,reminder: Reminder, delete: (Reminder) -> Unit) {
    var checked by remember { mutableStateOf(true) }
    ElevatedCard(
        Modifier.fillMaxWidth().height(80.dp).clickable {
            viewModel.select(reminder)
            navController.navigate("ReadReminder")
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            Modifier.fillMaxSize().padding(6.dp),
            Arrangement.Center) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    thumbContent = {
                        if (checked) {
                            Icon(Icons.Outlined.Check, null, Modifier.size(SwitchDefaults.IconSize))
                        } else {
                            Icon(Icons.Outlined.Close, null, Modifier.size(SwitchDefaults.IconSize))
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFF615690),
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.Gray,
                        uncheckedBorderColor = Color.Transparent,
                        checkedIconColor = Color.Black,
                        uncheckedIconColor = Color.Black
                    )
                )
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(reminder.titulo, style = MaterialTheme.typography.titleMedium, color = Color.Black, maxLines = 1)
                    Text(reminder.nota, style = MaterialTheme.typography.titleSmall, color = Color.Black, maxLines = 1)
                }
                Spacer(Modifier.weight(1f))
                Column {
                    Text(reminder.data, style = MaterialTheme.typography.titleMedium, color = Color.Black)
                    Text(reminder.hora, style = MaterialTheme.typography.titleSmall, color = Color.Black, maxLines = 1)
                }
                Spacer(Modifier.width(16.dp))
            }
        }
    }
}
