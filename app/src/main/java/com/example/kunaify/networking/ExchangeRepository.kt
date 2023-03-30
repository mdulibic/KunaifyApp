package com.example.kunaify.networking

import com.example.kunaify.model.ExchangeRateNetworkResponse
import com.example.kunaify.viewModel.CurrencyNetworkModel
import retrofit2.Response

class ExchangeRepository {

    private val apiService: KunaifyApiService = RetrofitBuilder.apiService

    suspend fun getExchangeRates(
        value: Double,
        from: CurrencyNetworkModel,
        to: CurrencyNetworkModel
    ): Response<ExchangeRateNetworkResponse> {
        return apiService.getExchangeRates(value = value, from = from.value, to = to.value)
    }
}