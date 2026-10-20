package com.sofiaverdugo.water_reminder_app.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.sofiaverdugo.water_reminder_app.models.Register

class WaterViewModel : ViewModel() {
    private val _registers = mutableStateListOf<Register>()
    val registers: List<Register> = _registers
    private var nextId = 1
    fun addRegister() {
        _registers.add(0, Register(id = nextId))
        nextId++
    }
    fun reply(id: Int, drankWater: Boolean) {
        val i = _registers.indexOfFirst { it.id == id }
        if (i != -1) {
            _registers[i] = _registers[i].copy(drankWater = drankWater)
        }
    }
}