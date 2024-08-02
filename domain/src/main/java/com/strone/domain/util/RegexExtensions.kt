package com.strone.domain.util

fun String.checkFloatRegex(intLength: Int, decimalLength: Int): Boolean {
    return Regex("^([1-9]\\d{0,$intLength}|0)(\\.\\d{0,$decimalLength})?$").matches(this)
}
