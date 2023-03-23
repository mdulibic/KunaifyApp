package com.example.kunaify.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kunaify.R
import com.example.kunaify.databinding.ItemEuroCentRateBinding
import com.example.kunaify.model.EuroHrkConversion

/**
 * Adapter for the list of euro cents.
 * @param euroCentsList List of euro cents.
 * @see [RecyclerView.Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter)
 */
class EuroCentsListAdapter(
    private var euroCentsList: ArrayList<EuroHrkConversion> = arrayListOf(),
) : RecyclerView.Adapter<EuroCentsListAdapter.EuroCentsListViewHolder>() {

    /**
     * View holder for the adapter.
     * @param binding Binding for the item layout.
     * @see [ItemEuroCentRateBinding](https://developer.android.com/topic/libraries/view-binding#kotlin)
     */
    inner class EuroCentsListViewHolder(private val binding: ItemEuroCentRateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(conversion: EuroHrkConversion) {
            binding.tvEuroCentRate.text = binding.root.context.getString(
                R.string.euro_cent_rate,
                conversion.euroValue,
                binding.root.context.getString(conversion.euroUnit),
                conversion.hrkValue,
                binding.root.context.getString(conversion.hrkUnit)
            )
        }
    }

    /**
     * Function to create the view holder.
     * @param parent Parent view group.
     * @param viewType Type of the view.
     * @return New instance of the view holder.
     * @see [EuroCentsListViewHolder](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EuroCentsListViewHolder {
        val binding =
            ItemEuroCentRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EuroCentsListViewHolder(binding)
    }

    /**
     * Function to bind the view holder to the data.
     * @param holder View holder to be bound.
     * @param position Position of the item in the list.
     */
    override fun onBindViewHolder(holder: EuroCentsListViewHolder, position: Int) {
        val item = euroCentsList[position]
        holder.bind(item)
    }

    /**
     * Function to get the size of the list.
     * @return Size of the list.
     */
    override fun getItemCount(): Int {
        return euroCentsList.size
    }

    /**
     * Function to set new data to the adapter.
     * @param newData List of new data to be set.
     * @see [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)
     * for more info on how to optimize this.
     */

    fun setData(newData: List<EuroHrkConversion>) {
        euroCentsList.clear()
        euroCentsList.addAll(newData)
        notifyDataSetChanged()
    }
}