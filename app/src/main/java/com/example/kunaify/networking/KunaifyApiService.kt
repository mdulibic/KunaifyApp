package com.example.kunaify.networking

import com.example.kunaify.model.ExchangeRateNetworkResponse
import retrofit2.Response
import retrofit2.http.*

interface KunaifyApiService {
    @GET("convert")
    suspend fun getExchangeRates(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") value: Double
    ): Response<ExchangeRateNetworkResponse>
}