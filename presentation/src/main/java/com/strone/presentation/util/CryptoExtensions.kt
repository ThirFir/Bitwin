package com.strone.presentation.util

import java.text.NumberFormat

fun Double.slicePriceDecimal(): String {
    return when {
        this < 0.01 -> "%.6f".format(this)
        this < 0.1 -> "%.5f".format(this)
        this < 1.0 -> "%.4f".format(this)
        this < 10.0 -> "%.3f".format(this)
        this < 100.0 -> "%.2f".format(this)
        this < 1000.0 -> "%.1f".format(this)
        else -> "%.0f".format(this)
    }
}

fun Double.toDisplayPrice(): String {
    val decimalFormattedPrice = this.slicePriceDecimal()
    val formatter = NumberFormat.getNumberInstance()
    return formatter.format(decimalFormattedPrice.toDouble())
}

fun Double.toDisplayChangeRate(): String {
    val rate =  this * 100
    return "%.2f%%".format(rate)
}

fun Double.toTradeVolume(): String {
    val dividedValue = (this / 1_000_000).toInt()
    val formatter = NumberFormat.getNumberInstance()
    val formattedValue = formatter.format(dividedValue)
    return "${formattedValue}백만"
}