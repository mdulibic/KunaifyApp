package com.example.kunaify.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kunaify.R
import com.example.kunaify.databinding.FragmentEuroCentsListBinding
import com.example.kunaify.model.EuroHrkConversion
import com.example.kunaify.utils.EuroCentsListAdapter
import com.example.kunaify.utils.VerticalSpaceItemDecorator
import com.example.kunaify.utils.euroCentList
import com.example.kunaify.utils.toPx

class EuroCentsListFragment : Fragment() {

    private var _binding: FragmentEuroCentsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var euroCentsListdapter: EuroCentsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEuroCentsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        euroCentsListdapter = EuroCentsListAdapter()
        val verticalSpace = VerticalSpaceItemDecorator(resources.getInteger(R.integer.margin_tv_item).toPx())
        binding.rvEuroCentsList.apply {
            adapter = euroCentsListdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(verticalSpace)
        }
        val items = euroCentList.map { EuroHrkConversion.convertEuroCentsToHrk(euroCents = it) }
        euroCentsListdapter.setData(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}