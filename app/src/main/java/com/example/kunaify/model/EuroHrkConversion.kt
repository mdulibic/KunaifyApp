package com.example.kunaify.model

import com.example.kunaify.R
import com.example.kunaify.utils.formatToTwoDecimals
import java.text.DecimalFormat
import java.text.NumberFormat

enum class HrkUnit(val value: Int) {
    LP(value = R.string.lp),
    KN(value = R.string.kn);
}

data class EuroHrkConversion(
    val euroValue: String,
    val euroUnit: Int = R.string.eur,
    val hrkValue: String,
    val hrkUnit: Int
) {
    companion object {
        fun convertEuroCentsToHrk(euroCents: Int): EuroHrkConversion {
            val euro = (euroCents / 100.0)
            val hrk = (euro * ExchangeRate.EUR_HRK)
            return if (hrk < 1) {
                val value = (hrk * 100).formatToTwoDecimals()
                EuroHrkConversion(
                    euroValue = euro.formatToTwoDecimals(),
                    hrkValue = value,
                    hrkUnit = HrkUnit.LP.value
                )
            } else {
                EuroHrkConversion(
                    euroValue = euro.formatToTwoDecimals(),
                    hrkValue = hrk.formatToTwoDecimals(),
                    hrkUnit = HrkUnit.KN.value
                )
            }
        }
    }
}