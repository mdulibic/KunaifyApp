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
        /**
         * This is a setter for the from currency
         * It sets the value of the field and the value of the live data
         * @param value the currency to be set
         * @return Unit
         */
        set(value) {
            field = value
            _fromCurrency.value = value
        }

    private var toCurrencyValue: Currency = Currency.Hrk()
        /**
         * This is a setter for the to currency
         * It sets the value of the field and the value of the live data
         * @param value the currency to be set
         * @return Unit
         */
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

    /**
     * This function converts the value from one currency to another
     * The function calls the repository to get the exchange rate
     * If the call is successful, the value is set to the live data
     * If the call is not successful, the value is set to 0.0
     * @param from the currency to be converted from
     * @param to the currency to be converted to
     * @param value the value to be converted
     * @return Unit
     * @see ExchangeRepository.getExchangeRates
     * @see Currency.toNetworkingModel
     * @see CurrencyNetworkModel
     * @see Currency
     */
    fun convert(
        value: Double
    ) {
        /**
         * viewModelScope is used to launch coroutines in the viewModel
         * Dispatchers.IO is used to switch the context of the coroutine,
         * so that the heavy operations are done in the IO context, not in the main thread !!!
         * The coroutines are automatically cancelled when the viewModel is cleared
         * This is useful when the viewModel is used in a fragment
         * The coroutines are cancelled when the fragment is destroyed
         * This prevents memory leaks
         * @see <a href="https://developer.android.com/topic/libraries/architecture/coroutines">Coroutines in Android</a>
         */
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

    /**
     * This function converts the currency to the networking model
     * @param this the currency to be converted
     * @return CurrencyNetworkModel
     * @see CurrencyNetworkModel
     */
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