package br.com.SylTech.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.SylTech.R

@Composable
fun HomeScreen(navController: NavController) {

    val zoando = 1
    val zoado = 0

    Scaffold(
        topBar = {
            CustomSearchBar()
                 },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("NewNote")},
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
        if (zoando >= zoado) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(Color(0xFFE6DEFF)).padding(innerPadding),
            ) {
                items(20) {
                    ContentCard()
                }
            }
        }
        else {
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFE6DEFF)).padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomAlertCard(navController)
        }
            }
    }
}

@Composable
fun CustomBottomBar(navController: NavController) {
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
                Icon(Icons.Outlined.Home, null,Modifier.size(35.dp), Color(0xFFE6DEFF))
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
fun CustomAlertCard(navController: NavController) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.size(width = 312.dp, height = 237.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Welcome!",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp, top = 5.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "ClassHub makes it easy to organize your studies, set reminders, and take quick notes.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF48454E)
                )
            }
            HorizontalDivider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 3.dp)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {navController.navigate("NewNote")},
                    colors = ButtonDefaults.buttonColors(Color(0xFF615690)),
                ) {
                    Text("New Note", color = Color.White)
                }
                Spacer(modifier = Modifier.padding(2.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF615690)),
                ) {
                    Text("New Reminder", color = Color.White)
                }
            }
        }
    }
}

//Barra de Pesquisa
@Composable
fun CustomSearchBar() {
val query = remember { mutableStateOf("") }
    TextField(
        value = query.value,
        onValueChange = {newQuery -> query.value = newQuery},
        modifier = Modifier.fillMaxWidth().padding(8.dp).statusBarsPadding(),
        placeholder = {Text("Search on library", style = MaterialTheme.typography.labelMedium)},
        singleLine = true,
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
            cursorColor = Color(0xFFE6DEFF)
        ),
        trailingIcon = { IconButton(onClick = {}) { Icon(Icons.Outlined.Search, null, )}}
    )
}

@Composable
fun ContentCard() {
    ElevatedCard(
        Modifier.fillMaxWidth().padding(4.dp),
        RoundedCornerShape(6.dp),
        CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFF7F2FA)
        ),
        CardDefaults.cardElevation(4.dp)
    ) {
        Row() {
            Box(
                Modifier.padding(12.dp).clip(shape = CircleShape).background(Color(0xFFE6DEFF)).size(50.dp),
                Alignment.Center,
            ) {

                Text("L", style = MaterialTheme.typography.bodySmall, color = Color.Black)
            }
            Column(Modifier.padding(8.dp)) {
                Text("Header", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium, color = Color.Black)
                Spacer(Modifier.height(4.dp))
                Text("Subhead", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
            }
            Spacer(Modifier.width(160.dp))
            Image(painterResource(R.drawable.livro), "", Modifier.size(80.dp))
        }
    }
}