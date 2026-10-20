package com.sofiaverdugo.water_reminder_app.models

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Register(
    val id: Int,
    val date: Date = Date(),
    val drankWater: Boolean? = null
) {
    fun day(): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
    }
    fun hour(): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
    }
}