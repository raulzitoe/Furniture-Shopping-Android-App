package com.group.furniture_shopping_android_app.shipping_addresses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentShippingAddressesBinding

class ShippingAddressesFragment : Fragment() {
    private lateinit var binding: FragmentShippingAddressesBinding
    private val viewModel: ShippingAddressesViewModel by viewModels {
        ShippingAddressesViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShippingAddressesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myActivity = (activity as AppCompatActivity)
        val recyclerView = binding.recyclerShippingAddresses

        myActivity.setSupportActionBar(binding.topAppBarShippingAddresses)
        myActivity.supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.topAppBarShippingAddresses.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        val shippingAddressList = ShippingAddressesRepository().getShippingAddresses(requireContext())
        recyclerView.adapter = ShippingAddressesAdapter(shippingAddressList)
    }
}