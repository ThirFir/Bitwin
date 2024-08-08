package com.strone.presentation.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat

fun BigDecimal.slicePriceDecimal(): String {
    return when {
        this < BigDecimal("0.01") -> "%.6f".format(this)
        this < BigDecimal("0.1") -> "%.5f".format(this)
        this < BigDecimal("1.0") -> "%.4f".format(this)
        this < BigDecimal("10.0") -> "%.3f".format(this)
        this < BigDecimal("100.0") -> "%.2f".format(this)
        this < BigDecimal("1000.0") -> "%.1f".format(this)
        else -> "%.0f".format(this)
    }
}

//TODO 예외처리
fun BigDecimal.toDisplayedDoubleFormat(): String {
    if(this.abs() < BigDecimal(1_000)) {
        return this.slicePriceDecimal()
    }
    val formatter = DecimalFormat("#,###")
    return formatter.format(this)
}

fun BigDecimal.toDecimalFormattedString(dec: Int): String {
    var pattern = "#,##0."
    for (i in 0 until dec) {
        pattern += "0"
    }
    val formatter = DecimalFormat(pattern)
    return formatter.format(this)
}

fun String.toBigDecimalRemoveComma(): BigDecimal {
    if (this.isEmpty()) return BigDecimal(0)
    return BigDecimal(this.replace(",", ""))
}

fun String.removeComma(): String {
    return this.replace(",", "")
}

fun BigDecimal.toDisplayChangeRate(): String {
    val rate = this * BigDecimal(100)
    return "%.2f%%".format(rate)
}

fun BigDecimal.toTradeVolume(): String {
    val dividedValue = (this / BigDecimal(1_000_000)).toInt()
    val formatter = NumberFormat.getNumberInstance()
    val formattedValue = formatter.format(dividedValue)
    return "${formattedValue}백만"
}

fun BigDecimal.getUnitPrice(): BigDecimal {
    return when {
        this < BigDecimal("0.01") -> BigDecimal("0.000001")
        this < BigDecimal("0.1") -> BigDecimal("0.00001")
        this < BigDecimal(1) -> BigDecimal("0.0001")
        this < BigDecimal(10) -> BigDecimal("0.001")
        this < BigDecimal(100) -> BigDecimal("0.01")
        this < BigDecimal(1000) -> BigDecimal("0.1")
        this < BigDecimal(10_000) -> BigDecimal(1)
        this < BigDecimal(100_000) -> BigDecimal(10)
        this < BigDecimal(500_000)-> BigDecimal(50)
        this < BigDecimal(1_000_000) -> BigDecimal(100)
        else -> BigDecimal(1_000)
    }
}

fun BigDecimal.getUnitAmount(): BigDecimal {
    return when {
        this < BigDecimal("0.01") -> BigDecimal(1_000_000_00)
        this < BigDecimal("0.1") -> BigDecimal(100_000_00)
        this < BigDecimal(1) -> BigDecimal(10_000_00)
        this < BigDecimal(10) -> BigDecimal(1_000_00)
        this < BigDecimal(100) -> BigDecimal(100_00)
        this < BigDecimal(1000) -> BigDecimal(10_00)
        this < BigDecimal(10_000) -> BigDecimal(1_00)
        this < BigDecimal(100_000) -> BigDecimal(10)
        this < BigDecimal(100_000_0) -> BigDecimal(1)
        else -> BigDecimal("0.1")
    }
}

