package com.example.kunaify.networking

import com.example.kunaify.model.ExchangeRateAPI
import retrofit2.Response
import retrofit2.http.*

interface KunaifyApiService {
    @GET("/convert")
    suspend fun getExchangeRates(
        @Query("value") value: Double,
        @Query("from") from: String,
        @Query("to") to: String
    ): Response<ExchangeRateAPI>
}