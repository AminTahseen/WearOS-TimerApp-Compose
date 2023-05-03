package com.example.wearos_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.wearos_compose.presentation.routes.Routes
import com.example.wearos_compose.presentation.screens.LandingComposable
import com.example.wearos_compose.presentation.screens.TimerComposable
import com.example.wearos_compose.presentation.screens.TimerLapsComposable
import com.example.wearos_compose.presentation.viewmodels.TimerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val swipeDismissibleNavController = rememberSwipeDismissableNavController()
            val timerViewModel: TimerViewModel by viewModels()
            val elapsedTime by timerViewModel.elapsedTime.collectAsState()
            val timeInString = elapsedTime.toString().map {
                String.format("%02d : %02d", elapsedTime / 1000 / 60, elapsedTime / 1000 % 60)
            }
            val timeLapsList by timerViewModel.timeLapsList.collectAsState()
            SwipeDismissableNavHost(
                navController = swipeDismissibleNavController,
                startDestination = Routes.LANDING.name,
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {
                composable(Routes.LANDING.name) {
                    LandingComposable(
                        onNavigate = {
                            swipeDismissibleNavController.navigate(Routes.TIMER.name)
                        }
                    )
                }
                composable(Routes.TIMER.name) {
                    TimerComposable(
                        timeInString = timeInString,
                        resetTime = {
                            timerViewModel.resetTimer()
                        },
                        startTimer = {
                            timerViewModel.startTimer()
                        },
                        stopTimer = {
                            timerViewModel.stopTimer()
                        },
                        onNavigate = {
                            swipeDismissibleNavController.navigate(Routes.TIMER_LAPS.name)
                        },
                        onTimeLap = timerViewModel::addTimeLap
                    )
                }
                composable(Routes.TIMER_LAPS.name) {
                    TimerLapsComposable(
                        timeLapsList = timeLapsList,
                        onTapItem = timerViewModel::removeTimeLap
                    )
                }
            }
        }
    }
}


