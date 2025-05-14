package br.com.SylTech.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CollectionScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomSearchBar()
        },
        bottomBar = { CustomBottomBar2(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = Color(0xFF615690),
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "",
                    tint = Color(0xFFE6DEFF)
                )

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFE6DEFF)).padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomAlertCard2(navController)
        }
    }
}

//Barra de Navegação (Lembrar de arrumar uns detalhes)

@Composable
fun CustomBottomBar2(navController: NavController) {
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
                navController.navigate("Home")
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
                navController.navigate("Collections")
            }) {
                Icon(Icons.Outlined.Star, null, Modifier.size(35.dp),Color(0xFFE6DEFF))
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
                navController.navigate("Reminder")
            }) {
                Icon(Icons.Outlined.Notifications, null, Modifier.size(35.dp), Color.Black)
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
fun CustomAlertCard2(navController: NavController) {
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
                    "No Collections Yet!",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp, top = 5.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    "You haven't created any collections yet. Start by making one to organize your notes.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF48454E)
                )
            }
            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {navController.navigate("NewCollection")},
                colors = ButtonDefaults.buttonColors(Color(0xFF615690)),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("New Collection", color = Color.White)
            }

        }
    }
}
