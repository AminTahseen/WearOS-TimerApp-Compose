package com.example.wearos_compose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private val _elapsedTime = MutableStateFlow(0L)
    private val _timeLapsList = MutableStateFlow<List<String>>(emptyList())

    val timeLapsList = _timeLapsList
    val elapsedTime: StateFlow<Long>
        get() = _elapsedTime

    private var _isRunning = false
    private var timer: Job? = null
    private var startTime = 0L

    fun startTimer() {
        _isRunning = true
        startTime = System.currentTimeMillis()
        timer = viewModelScope.launch {
            while (_isRunning) {
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - startTime
                _elapsedTime.emit(elapsedTime)
                delay(1000L)
            }
        }
    }

    fun stopTimer() {
        _isRunning = false
        timer?.cancel()
    }

    fun resetTimer() {
        stopTimer()
        viewModelScope.launch {
            _elapsedTime.emit(0L)
        }
    }

    fun addTimeLap(timeLap: String) {
        _timeLapsList.value += timeLap
    }

    fun removeTimeLap(index: Int) {
        val currentList = _timeLapsList.value.toMutableList() // Get the current list
        currentList.removeAt(index) // Remove the item at the specified index
        _timeLapsList.update { currentList }
    }
}