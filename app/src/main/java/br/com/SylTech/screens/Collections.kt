package br.com.SylTech.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.SylTech.R
import br.com.SylTech.model.Colecao
import br.com.SylTech.repository.CollectionRepository

@Composable
fun CollectionScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var titleError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val listOfCollections = CollectionRepository(context).Read()
    val query = remember { mutableStateOf("") }
    val filteredColletions = listOfCollections.filter { it.titulo.contains(query.value, ignoreCase = true) }


    Scaffold(
        topBar = { CustomSearchBar(query) },
        bottomBar = { CustomCollectionBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
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
                if (filteredColletions.isEmpty()) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .background(Color(0xFFE6DEFF))
                                .padding(innerPadding),
                            Arrangement.Center,
                            Alignment.CenterHorizontally
                            ) { CollectionVazia(onClick = { showDialog = true }) }
                } else {
                    LazyColumn(
                        Modifier
                        .fillMaxSize()
                        .background(Color(0xFFE6DEFF))
                        .padding(innerPadding)) {
                        items(filteredColletions.size) { index ->
                            val collection: Colecao = filteredColletions[index]
                            CollectionCard(collection) { collectionDelete ->
                                CollectionRepository(context).Delete(collectionDelete.id!!)
                                navController.navigate("Collections") {
                                    popUpTo("Collections") { inclusive = true }
                                }
                            }

                    }
                   }
                   }
                }
    if (showDialog) {
        CustomAlertDialog(
            title = "New Collection",
            onDismissRequest = { showDialog = false },
            onConfirm = { newTitle ->
                val titleValid = newTitle.trim().isNotEmpty()
                titleError = !titleValid

                if (titleValid) {
                    val repository = CollectionRepository(context)
                    val collection = Colecao(titulo = newTitle.trim())
                    val result = repository.Create(collection)
                    if (result > 0) {
                        showDialog = false
                        navController.navigate("Collections") {
                            popUpTo("Collections") { inclusive = true }
                        }
                    }
                }
            }
        )
    }
}




@Composable
fun CustomCollectionBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color(0xFF615690),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            IconButton(onClick = {
                navController.navigate("Home") {
                    popUpTo("Collections") { inclusive = true }
                }
            }) {
                Icon(Icons.Outlined.Home, contentDescription = null, Modifier.size(35.dp), Color.Black)
            }
            Text(
                text = "Home",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            IconButton(onClick = {
                navController.navigate("Collections") {
                    popUpTo("Collections") { inclusive = true }
                }
            }) {
                Icon(Icons.Outlined.Star, contentDescription = null, Modifier.size(35.dp), Color(0xFFE6DEFF))
            }
            Text(
                text = "Collections",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            IconButton(onClick = {
                navController.navigate("Reminder") {
                    popUpTo("Collections") { inclusive = true }
                }
            }) {
                Icon(Icons.Outlined.Notifications, contentDescription = null, Modifier.size(35.dp), Color.Black)
            }
            Text(
                text = "Reminders",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun CollectionVazia(onClick: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.size(width = 312.dp, height = 227.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "No Collections Yet!",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp, top = 5.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "You haven't created any collections yet. Start by making one to organize your notes.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF48454E)
                )
            }
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(Color(0xFF615690)),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("New Collection", color = Color.White)
            }
        }
    }
}

@Composable
fun CustomAlertDialog(
    title: String,
    initialText: String = "",
    onDismissRequest: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var collectionName by remember { mutableStateOf(initialText) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Black) },
        text = {
            OutlinedTextField(
                value = collectionName,
                onValueChange = { collectionName = it },
                placeholder = { Text("Collection Name", color = Color.Black) },
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

                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black,
                    errorIndicatorColor = Color(0xFFBA1A1A),

                    errorPlaceholderColor = Color(0xFFBA1A1A)
                ),
            )
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(collectionName) },
                colors = ButtonDefaults.buttonColors(Color(0xFF6750A4))
            ) {
                Text("Confirm", color = Color.White)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(Color(0xFF6750A4))
            ) {
                Text("Cancel", color = Color.White)
            }
        },
        containerColor = Color(0xFFECE6F0)
    )
}

@Composable
fun CollectionCard(collection: Colecao, delete: (Colecao) -> Unit) {
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { delete(collection) },
        RoundedCornerShape(6.dp),
        CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFF7F2FA)
        ),
        CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            Modifier.fillMaxSize().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(collection.titulo, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium, color = Color.Black, maxLines = 1)
            Spacer(Modifier.weight(1f))
            Image(painterResource(R.drawable.capa_3), "", Modifier.size(80.dp))
        }
    }
}

