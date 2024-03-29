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
import com.example.wearos_compose.presentation.constants.*
import com.example.wearos_compose.presentation.theme.WearOSComposeTheme

@Composable
fun TimerLapsComposable(timeLapsList: List<String>, onTapItem: (Int) -> Unit) {
    WearOSComposeTheme {
        val listState = rememberScalingLazyListState()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            positionIndicator = {
                PositionIndicator(
                    scalingLazyListState = listState,
                    modifier = Modifier
                )
            },
        ) {
            if (timeLapsList.isNotEmpty()) {
                ScalingLazyColumn(
                    contentPadding = PaddingValues(top = 40.dp),
                    state = listState,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(timeLapsList.size) {
                        TimerLapItem(
                            time = timeLapsList[it],
                            onTapItem = {
                                onTapItem(it)
                            }
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        contentDescription = "answer",
                        painter = painterResource(id = R.drawable.baseline_timelapse_24),
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        text = "No current laps\nfound.",
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}

@Composable
fun TimerLapItem(time: String, onTapItem: () -> Unit) {
    Chip(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        onClick = {
            onTapItem()
        },
        icon = {
            Icon(
                contentDescription = "answer",
                painter = painterResource(id = R.drawable.baseline_timelapse_24),
                tint = MaterialTheme.colors.primary
            )
        },
        label = { Text(time) },
        secondaryLabel = {
            Text(
                "Tap to remove", style = TextStyle(
                    fontSize = 12.sp, color = Color.Gray
                )
            )
        },
        colors = ChipDefaults.secondaryChipColors()
    )
}

@Preview(
    widthDp = WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun TimerLapsPreview() {
    TimerLapsComposable(timeLapsList = listOf("12:00", "01:00", "02:00"), onTapItem = {})

}