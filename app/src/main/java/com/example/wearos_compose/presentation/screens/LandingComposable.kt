package com.example.wearos_compose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.example.wearos_compose.R
import com.example.wearos_compose.presentation.theme.WearOSComposeTheme
import kotlin.system.exitProcess

@Composable
fun LandingComposable(onNavigate:()->Unit) {
    WearOSComposeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationIcon()
            Greeting()
            ActionsRow(onNavigate= onNavigate)
        }
    }
}

@Composable
fun LocationIcon() {
    Icon(
        contentDescription = "close",
        painter = painterResource(id = R.drawable.baseline_timelapse_24),
        tint = MaterialTheme.colors.primary,
        modifier = Modifier.size(30.dp)
    )
}

@Composable
fun ActionsRow(onNavigate: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                exitProcess(0)
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Icon(
                contentDescription = "close",
                painter = painterResource(id = R.drawable.baseline_close_24),
            )

        }
        Button(
            onClick = {
                onNavigate()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Icon(
                contentDescription = "answer",
                painter = painterResource(id = R.drawable.baseline_start_24),
                tint = Color.Black
            )

        }
    }
}

@Composable
fun Greeting() {
 Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
     Text(
         modifier = Modifier
             .fillMaxWidth(),
         textAlign = TextAlign.Center,
         color = Color.White,
         text = "WristTimer",
         style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)

     )
     Text(
         modifier = Modifier
             .fillMaxWidth()
             .padding(bottom = 5.dp),
         textAlign = TextAlign.Center,
         color = Color.Gray,
         text = "Your one time timer assistant",
         style = TextStyle(fontSize = 12.sp)

     )
 }
}
@Preview
@Composable
fun WearAppPreview(){
    // WearApp()
}
