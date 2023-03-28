package com.example.kunaify.networking

class ExchangeRepository(
    private val kunaifyApiService: KunaifyApiService
) {

    suspend fun getExchangeRates() {
        kunaifyApiService.getExchangeRates()
    }
}