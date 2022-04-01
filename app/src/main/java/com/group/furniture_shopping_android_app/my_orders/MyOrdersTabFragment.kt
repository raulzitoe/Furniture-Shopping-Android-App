package com.group.furniture_shopping_android_app.my_orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.group.furniture_shopping_android_app.databinding.FragmentMyOrdersTabBinding

class MyOrdersTabFragment : Fragment() {
    private lateinit var binding: FragmentMyOrdersTabBinding
    private val viewModel: MyOrdersTabViewModel by viewModels {
        MyOrdersTabViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyOrdersTabBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerMyOrders
        recyclerView.adapter = MyOrdersAdapter()
        viewModel.viewState.observe(viewLifecycleOwner) { list ->
            (recyclerView.adapter as MyOrdersAdapter).myOrdersList = list
        }
    }

}