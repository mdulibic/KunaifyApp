package com.example.kunaify.networking

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

    suspend fun getExchangeRates(value: Double) {
        retrofitInstance.getExchangeRates(value)
    }
}