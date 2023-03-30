package com.example.kunaify.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kunaify.model.Currency
import com.example.kunaify.networking.ExchangeRepository
import com.example.kunaify.utils.formatToTwoDecimals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class CurrencyNetworkModel(val value: String) {
    EUR("EUR"),
    HRK("HRK")
}

class ExchangeViewModel : ViewModel() {

    private val exchangeRepository: ExchangeRepository = ExchangeRepository()

    private val _fromCurrency = MutableLiveData<Currency>(Currency.Euro)
    val fromCurrency: LiveData<Currency>
        get() = _fromCurrency

    private val _toCurrency = MutableLiveData<Currency>(Currency.Hrk())
    val toCurrency: LiveData<Currency>
        get() = _toCurrency

    private val _convertedValue = MutableLiveData<String>()
    val convertedValue: LiveData<String>
        get() = _convertedValue

    private var fromCurrencyValue: Currency = Currency.Euro
        set(value) {
            field = value
            _fromCurrency.value = value
        }

    private var toCurrencyValue: Currency = Currency.Hrk()
        set(value) {
            field = value
            _toCurrency.value = value
        }

    fun exchangeCurrencies() {
        val temp = fromCurrencyValue
        temp.let {
            fromCurrencyValue = toCurrencyValue
            toCurrencyValue = it
        }
    }

    fun convert(
        value: Double,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            exchangeRepository.getExchangeRates(
                value = value,
                from = fromCurrencyValue.toNetworkingModel(),
                to = toCurrencyValue.toNetworkingModel()
            ).let {
                val result = it.body()?.result?.formatToTwoDecimals() ?: "0.0"
                if (it.isSuccessful) _convertedValue.postValue(result)
            }
        }
    }

    private fun Currency.toNetworkingModel(): CurrencyNetworkModel {
        return when (this) {
            Currency.Euro -> CurrencyNetworkModel.EUR
            Currency.Hrk() -> CurrencyNetworkModel.HRK
            else -> {
                throw IllegalArgumentException("Currency not supported")
            }
        }
    }
}