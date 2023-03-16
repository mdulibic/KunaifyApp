package com.example.kunaify.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kunaify.model.Currency
import com.example.kunaify.model.ExchangeRate

class ExchangeViewModel : ViewModel() {

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

    fun convert(value: Double) {
        when(toCurrency.value) {
            is Currency.Euro -> _convertedValue.value = value / ExchangeRate.EUR_HRK
            is Currency.Hrk -> _convertedValue.value = value * ExchangeRate.EUR_HRK
            else -> {
                Log.e("ExchangeViewModel", "Unknown currency")}
        }
    }
}