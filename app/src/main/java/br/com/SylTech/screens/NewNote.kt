package br.com.SylTech.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NewNote(navController: NavController) {
    val noteText = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            CenterAlignedTopAppBar(
                title = { Text("New Reminder") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF615690),
                    titleContentColor = Color(0xFFE6DEFF),
                    navigationIconContentColor = Color(0xFFE6DEFF)
                ),
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "",) }
                },
                modifier = Modifier.padding(
                    bottom = 12.dp
                )
            )
        },
        containerColor = Color(0xFFE6DEFF),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.popBackStack()},
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
                Box(
                    Modifier.fillMaxSize().padding(16.dp),
                    Alignment.TopStart
                ) {
                    OutlinedTextField(
                        value = noteText.value,
                        onValueChange = {
                            NewnoteText -> noteText.value = NewnoteText
                        },
                        placeholder = {
                            Text("Write it down before you forget!",
                            color = Color(0xFF847F8E))
                        },
                        modifier = Modifier.fillMaxSize(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            cursorColor = Color(0xFF615690),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = false,
                        maxLines = Int.MAX_VALUE
                    )
                }
            }
        }
    }
}
