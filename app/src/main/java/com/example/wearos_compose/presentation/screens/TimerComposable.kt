package com.example.wearos_compose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.example.wearos_compose.R
import com.example.wearos_compose.presentation.constants.*
import com.example.wearos_compose.presentation.theme.*

@Composable
fun TimerComposable(
    timeInString: List<String>,
    startTimer: () -> Unit,
    stopTimer: () -> Unit,
    resetTime: () -> Unit,
    onNavigate: () -> Unit,
    onTimeLap: (String) -> Unit
) {
    WearOSComposeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimerTopButtons(
                resetTime = resetTime,
                onNavigate = onNavigate
            )
            TimerClock(
                elapsedTime = timeInString
            )
            TimerClockActions(
                startTimer = startTimer,
                stopTimer = stopTimer,
                onTimeLap = onTimeLap,
                timeLap = timeInString[0]
            )
        }
    }
}

@Composable
fun TimerClockActions(
    startTimer: () -> Unit,
    stopTimer: () -> Unit,
    onTimeLap: (String) -> Unit,
    timeLap: String
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TimerLapButton(onTimeLap = onTimeLap, timeLap = timeLap)
        TimerStartPauseButton(startTimer = { startTimer() }, stopTimer = { stopTimer() })
    }
}

@Composable
fun TimerLapButton(onTimeLap: (String) -> Unit, timeLap: String) {
    Button(
        onClick = {
            onTimeLap(timeLap)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
        modifier = Modifier.padding(end = 5.dp)
    ) {
        Icon(
            contentDescription = "play",
            painter = painterResource(
                id = R.drawable.baseline_timelapse_24
            ),

            )

    }
}

@Composable
fun TimerClock(
    elapsedTime: List<String>
) {
    Text(
        text = elapsedTime[0], style = TextStyle(
            fontSize = 50.sp, color = Color.White
        )
    )
}

@Composable
fun TimerTopButtons(
    resetTime: () -> Unit,
    onNavigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(contentDescription = "answer",
            painter = painterResource(id = R.drawable.baseline_restart_alt_24),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    resetTime()
                })
        Icon(contentDescription = "answer",
            painter = painterResource(id = R.drawable.baseline_view_list_24),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    onNavigate()
                }

        )
    }
}

@Composable
fun TimerStartPauseButton(
    startTimer: () -> Unit, stopTimer: () -> Unit
) {
    var buttonState by remember { mutableStateOf(false) }
    Button(
        onClick = {
            buttonState = !buttonState
            if (buttonState) {
                startTimer()
            } else {
                stopTimer()
            }
        }, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
    ) {
        Icon(
            contentDescription = "play",
            painter = painterResource(
                id = if (buttonState) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24
            ),

            )

    }
}

@Preview(
    widthDp = WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun TimerComposablePreview() {
    TimerComposable(timeInString = listOf("00:00"),
        startTimer = { /*TODO*/ },
        stopTimer = { /*TODO*/ },
        resetTime = {/*TODO*/ },
        onNavigate = {/*TODO*/ },
        onTimeLap = {/*TODO*/ }
    )
}