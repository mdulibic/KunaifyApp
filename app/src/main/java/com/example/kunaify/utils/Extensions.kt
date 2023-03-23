package com.example.kunaify.utils

import android.content.res.Resources
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

/**
 *
 */

fun Int.toPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

private const val TWO_DECIMALS_FORMAT = "#.##"

fun Double.formatToTwoDecimals(): String {
    val decimalFormat = DecimalFormat(TWO_DECIMALS_FORMAT)
    decimalFormat.minimumFractionDigits = 2
    decimalFormat.maximumFractionDigits = 2
    return decimalFormat.format(this)
}