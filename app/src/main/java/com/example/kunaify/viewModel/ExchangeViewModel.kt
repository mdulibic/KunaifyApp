package com.example.kunaify.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kunaify.model.Currency
import com.example.kunaify.networking.ExchangeRepository

class ExchangeViewModel: ViewModel() {

    private lateinit var exchangeRepository: ExchangeRepository

    init {
        initializeRepo()
    }

    private fun initializeRepo() {
        exchangeRepository = ExchangeRepository()
    }

    private val _fromCurrency = MutableLiveData<Currency>(Currency.Euro)
    val fromCurrency: LiveData<Currency>
        get() = _fromCurrency

    private val _toCurrency = MutableLiveData<Currency>(Currency.Hrk())
    val toCurrency: LiveData<Currency>
        get() = _toCurrency

    private val _convertedValue = MutableLiveData<Double>()
    val convertedValue: LiveData<Double>
        get() = _convertedValue

    fun exchangeCurrencies() {
        val temp = _fromCurrency.value
        temp?.let {
            _fromCurrency.value = _toCurrency.value
            _toCurrency.value = it
        }
    }

    suspend fun convert(
        value: Double,
        from: String,
        to: String,
    ) {
        exchangeRepository.getExchangeRates(value, from, to).let {
            if (it.isSuccessful) {
                _convertedValue.value = it.body()?.result
            }
            else {
                _convertedValue.value = 0.0
            }
        }

    }
}