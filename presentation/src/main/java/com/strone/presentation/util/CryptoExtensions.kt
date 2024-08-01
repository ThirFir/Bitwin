package com.strone.presentation.util

import com.strone.core.CryptoNamespace
import com.strone.presentation.model.TickerModel
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.absoluteValue

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

fun Double.toDisplayedDoubleFormat(): String {
    if(this.absoluteValue < 1_000) {
        return slicePriceDecimal()
    }
    val formatter = DecimalFormat("#,###")
    return formatter.format(this)
}

fun Double.toDisplayChangeRate(): String {
    val rate = this * 100
    return "%.2f%%".format(rate)
}

fun Double.toTradeVolume(): String {
    val dividedValue = (this / 1_000_000).toInt()
    val formatter = NumberFormat.getNumberInstance()
    val formattedValue = formatter.format(dividedValue)
    return "${formattedValue}백만"
}

fun List<TickerModel>.searched(input: String): List<TickerModel> {
    return this.filter {
        it.signature.contains(input, ignoreCase = true) ||
                CryptoNamespace.markets[it.code]?.koreanName?.contains(input, ignoreCase = true) == true ||
                CryptoNamespace.markets[it.code]?.englishName?.contains(input, ignoreCase = true) == true
    }
}

fun Double.getMinChangeablePrice(): Double {
    return when {
        this < 0.01 -> 0.000001
        this < 0.1 -> 0.00001
        this < 1.0 -> 0.0001
        this < 10.0 -> 0.001
        this < 100.0 -> 0.01
        this < 1000.0 -> 0.1
        this < 10_000.0 -> 1.0
        this < 100_000.0 -> 10.0
        this < 500_000.0 -> 50.0
        this < 1_000_000.0 -> 100.0
        else -> 1_000.0
    }
}