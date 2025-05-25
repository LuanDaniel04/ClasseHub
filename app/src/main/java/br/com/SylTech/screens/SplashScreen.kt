package br.com.SylTech.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.SylTech.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("home") {
            popUpTo("Splash") { inclusive = true }
        }
    }
    Scaffold(
        containerColor = Color(0xFF0A0A0A)
    ) { innerPadding ->
        Column(
            Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Spacer(Modifier.height(100.dp))
            Image(painterResource(R.drawable.syltech), "", Modifier.size(260.dp))
            Spacer(Modifier.height(16.dp))
            Image(
                painterResource(R.drawable.classhub_2),
                "",
                Modifier.size(width = 255.dp, height = 68.dp)
            )
        }
    }
}