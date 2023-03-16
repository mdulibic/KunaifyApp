package com.example.kunaify.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kunaify.databinding.LayoutCurrencyViewBinding
import com.example.kunaify.model.Currency

class CurrencyView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val binding: LayoutCurrencyViewBinding = LayoutCurrencyViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setCurrency(currency: Currency) {
        binding.ivCurrency.setImageResource(currency.icon)
        binding.tvCurrency.text = context.getString(currency.name)
    }
}