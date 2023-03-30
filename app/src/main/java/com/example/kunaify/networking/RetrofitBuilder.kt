package com.example.kunaify.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"
    private const val API_KEY = "R3IOT3TtqBPkwwaORLQflZ8U9cwev1U7"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("apikey", API_KEY)
        chain.proceed(requestBuilder.build())
    }

    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
    }.build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiService: KunaifyApiService = getRetrofit().create(KunaifyApiService::class.java)
}