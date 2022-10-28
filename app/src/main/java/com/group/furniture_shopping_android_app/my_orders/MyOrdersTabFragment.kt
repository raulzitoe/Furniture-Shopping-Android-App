package com.group.furniture_shopping_android_app.my_orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentMyOrdersTabBinding

class MyOrdersTabFragment : Fragment() {
    companion object {
        fun newInstance(orderStatus: String): MyOrdersTabFragment {
            return MyOrdersTabFragment().apply {
                arguments = Bundle().apply {
                    putString("orderStatus", orderStatus)
                }
            }
        }
    }

    private lateinit var binding: FragmentMyOrdersTabBinding
    private val viewModel: MyOrdersTabViewModel by viewModels ()

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
        val orderStatusTypes =
            context?.resources?.getStringArray(R.array.my_order_tabs_titles) ?: arrayOf()
        recyclerView.adapter = MyOrdersAdapter(
            arguments?.getString("orderStatus", "") ?: "",
            orderStatusTypes,
            requireContext()
        )
        viewModel.viewState.observe(viewLifecycleOwner) { list ->
            (recyclerView.adapter as MyOrdersAdapter).myOrdersList = list
        }
    }


}