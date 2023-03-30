package com.example.kunaify.model

import com.google.gson.annotations.SerializedName

data class ExchangeRateNetworkResponse(
    @SerializedName("result")
    val result: Double,
)