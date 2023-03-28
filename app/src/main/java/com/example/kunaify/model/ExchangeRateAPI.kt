package com.example.kunaify.model

import com.google.gson.annotations.SerializedName

data class ExchangeRateAPI(
    @SerializedName("result")
    val result: Double,
)