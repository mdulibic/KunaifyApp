package com.example.kunaify.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * RetrofitBuilder.kt
 * This class is used to build the Retrofit instance
 * and the ApiService
 */
object RetrofitBuilder {

    private const val BASE_URL = "https://api.apilayer.com/"
    private const val API_KEY = "R3IOT3TtqBPkwwaORLQflZ8U9cwev1U7"

    /**
     * This interceptor is used to log the request and response
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * This interceptor is used to add the apikey to the request
     * The header is added to every request
     * @see Interceptor
     */
    private val authInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("apikey", API_KEY)
        chain.proceed(requestBuilder.build())
    }

    /**
     * This client is used to set the timeout and the interceptor
     */
    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
    }.build()

    /**
     * This function is used to create the Retrofit instance
     * @return Retrofit
     * @see Retrofit
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    /**
     * This function is used to create the ApiService
     * @return ApiService
     */
    val apiService: KunaifyApiService = getRetrofit().create(KunaifyApiService::class.java)

}
