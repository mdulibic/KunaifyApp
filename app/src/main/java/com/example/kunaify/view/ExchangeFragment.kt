package com.example.kunaify.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kunaify.databinding.FragmentExchangeBinding
import com.example.kunaify.viewModel.ExchangeViewModel

class ExchangeFragment : Fragment() {

    private var _binding: FragmentExchangeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExchangeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentExchangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ExchangeViewModel::class.java]
        observeLiveData()
        setOnClickClickListeners()
    }

    private fun observeLiveData() {
        viewModel.fromCurrency.observe(viewLifecycleOwner) {
            binding.fromCurrency.setCurrency(it)
            binding.tvCurrencyFrom.text = requireContext().getText(it.name)
        }
        viewModel.toCurrency.observe(viewLifecycleOwner) {
            binding.toCurrency.setCurrency(it)
            binding.tvCurrencyTo.text = requireContext().getText(it.name)
        }
        viewModel.convertedValue.observe(viewLifecycleOwner) {
            binding.tvConverted.text = it.toString()
        }
    }

    private fun setOnClickClickListeners() {
        binding.btnExchange.setOnClickListener {
            viewModel.exchangeCurrencies()
        }
        binding.btnConvert.setOnClickListener {
            viewModel.convert(value = binding.etAmount.text.toString().toDouble())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}