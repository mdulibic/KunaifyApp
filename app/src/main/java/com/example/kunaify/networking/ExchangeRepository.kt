package com.example.kunaify.networking

import com.example.kunaify.model.ExchangeRateAPI
import retrofit2.Response

class ExchangeRepository {

    lateinit var retrofitInstance: KunaifyApiService
    init {
        initiateRetrofit()
    }

    private fun initiateRetrofit() {
        retrofitInstance = RetrofitInstance
            .getRetrofitInstance()
            .create(KunaifyApiService::class.java)
    }

    suspend fun getExchangeRates(value: Double, from: String, to: String): Response<ExchangeRateAPI> {
        return retrofitInstance.getExchangeRates(value, from, to)
    }
}