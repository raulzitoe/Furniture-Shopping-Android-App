package com.group.furniture_shopping_android_app.shipping_addresses

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.DialogShippingAddressesBinding
import com.group.furniture_shopping_android_app.databinding.FragmentShippingAddressesBinding
import com.group.furniture_shopping_android_app.repository.ShippingAddressModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShippingAddressesFragment : Fragment() {
    private lateinit var binding: FragmentShippingAddressesBinding
    private val viewModel: ShippingAddressesViewModel by viewModels()

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
        binding.recyclerShippingAddresses.adapter = ShippingAddressesAdapter()
        val recyclerView = (binding.recyclerShippingAddresses.adapter as ShippingAddressesAdapter)

        myActivity.setSupportActionBar(binding.topAppBarShippingAddresses)
        myActivity.supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.topAppBarShippingAddresses.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    recyclerView.shippingAddressesList = when (it) {
                        is ShippingAddressesState.Success -> it.shippingAdressesList
                        is ShippingAddressesState.Error -> throw Exception("Failed to load Shipping Addresses")

                    }
                }
            }
        }
        binding.floatingActionButton.setOnClickListener {
            showShippingAddressDialog(clickCallback = { address ->
                viewModel.insertNewAddress(address)
            })
        }

    }

    private fun showShippingAddressDialog(clickCallback: (ShippingAddressModel) -> Unit) {
        val dialog = AlertDialog.Builder(requireContext())
        val bind = DialogShippingAddressesBinding.inflate(layoutInflater)
        dialog.apply {
            setPositiveButton(getString(R.string.ok),
                DialogInterface.OnClickListener { _, _ ->
                    clickCallback(
                        ShippingAddressModel(
                            0,
                            bind.etName.text.toString(),
                            bind.etStreet.text.toString(),
                            bind.etPostalCode.text.toString(),
                            bind.etCity.text.toString(),
                            bind.etProvince.text.toString(),
                            bind.etCountry.text.toString()
                        )
                    )
                })
            setNegativeButton(getString(R.string.cancel),
                DialogInterface.OnClickListener { _, _ ->
                    // User cancelled the dialog
                })
        }
        dialog.setView(bind.root).create()
        dialog.show()
    }
}