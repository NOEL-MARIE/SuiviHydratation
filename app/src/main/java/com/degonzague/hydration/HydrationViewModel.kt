package com.degonzague.hydration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

data class HydrationState(
    val currentAmountMl: Int = 0,
    val targetAmountMl: Int = 2000,
    val history: List<WaterLog> = emptyList()
) {
    val progressFraction: Float
        get() = (currentAmountMl.toFloat() / targetAmountMl.toFloat()).coerceIn(0f, 1.5f)

    val isGoalAchieved: Boolean
        get() = currentAmountMl >= targetAmountMl
}

data class WaterLog(
    val id: Long,
    val amountMl: Int,
    val timestamp: Long
)

class HydrationViewModel : ViewModel() {

    private val _state = MutableStateFlow(HydrationState())
    val state: StateFlow<HydrationState> = _state.asStateFlow()

    fun addWater(amountMl: Int) {
        val currentAmount = _state.value.currentAmountMl
        val newAmount = currentAmount + amountMl
        val log = WaterLog(
            id = System.currentTimeMillis(),
            amountMl = amountMl,
            timestamp = System.currentTimeMillis()
        )
        
        _state.value = _state.value.copy(
            currentAmountMl = newAmount,
            history = _state.value.history + log
        )
    }

    fun reset() {
        _state.value = _state.value.copy(
            currentAmountMl = 0,
            history = emptyList()
        )
    }

    fun undoLast() {
        val currentHistory = _state.value.history
        if (currentHistory.isNotEmpty()) {
            val lastLog = currentHistory.last()
            _state.value = _state.value.copy(
                currentAmountMl = (_state.value.currentAmountMl - lastLog.amountMl).coerceAtLeast(0),
                history = currentHistory.dropLast(1)
            )
        }
    }
}
