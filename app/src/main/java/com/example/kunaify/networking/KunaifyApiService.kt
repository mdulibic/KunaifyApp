package com.example.kunaify.networking

import com.example.kunaify.model.ExhangeRateAPI
import retrofit2.Response
import retrofit2.http.*

class KunaifyApiService {

    @GET("api/v1/exchange-rates")
    fun getExchangeRates(): Response<ExhangeRateAPI> {
        return Response.success(ExhangeRateAPI(7.53450))
    }
}