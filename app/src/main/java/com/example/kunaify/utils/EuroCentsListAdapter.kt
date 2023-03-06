package com.example.kunaify.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kunaify.databinding.ItemEuroCentRateBinding

class EuroCentRate {
    // TODO
}

class EuroCentsListAdapter(
    private var euroCentsList: ArrayList<EuroCentRate> = arrayListOf(),
) : RecyclerView.Adapter< EuroCentsListAdapter.EuroCentsListViewHolder>() {

    inner class EuroCentsListViewHolder(private val binding: ItemEuroCentRateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rate: EuroCentRate) {
           // TODO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EuroCentsListViewHolder {
        val binding =
            ItemEuroCentRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EuroCentsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EuroCentsListViewHolder, position: Int) {
      // TODO
    }

    override fun getItemCount(): Int {
        return euroCentsList.size
    }

    fun setData(newData: List<EuroCentRate>) {
        // TODO
    }
}