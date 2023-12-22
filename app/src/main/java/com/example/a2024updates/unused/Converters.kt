package com.example.a2024updates.unused

import java.text.NumberFormat
import java.util.Currency

class Converters {

    // indian rupee converter
    fun indianRupee(amount: Double): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("INR")
        return format.format(amount)
    }

}