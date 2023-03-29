package com.example.kunaify.networking

import com.example.kunaify.model.ExchangeRateNetworkResponse
import retrofit2.Response
import retrofit2.http.*

interface KunaifyApiService {
    @GET("/exchangerates_data/convert")
    suspend fun getExchangeRates(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") value: Double,
    ): Response<ExchangeRateNetworkResponse>
}