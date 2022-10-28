package com.group.furniture_shopping_android_app.shipping_addresses

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

        binding.recyclerShippingAddresses.adapter = ShippingAddressesAdapter(listener = object :
            ShippingAddressesAdapter.ShippingRecyclerListener {
            override fun clickUpdateButton(item: ShippingAddressModel) {
                showShippingAddressDialog(confirmCallback = {
                    viewModel.updateShippingAddress(it)
                }, deleteCallback = { viewModel.deleteAddress(item) }, ActionType.UPDATE, item)
            }

            override fun clickSetDefaultAddressButton(item: ShippingAddressModel) {
                viewModel.setDefaultAddress(item)
            }
        })

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
                    (binding.recyclerShippingAddresses.adapter as ShippingAddressesAdapter).submitList(
                        when (it) {
                            is ShippingAddressesState.Success -> it.shippingAddressesList
                            is ShippingAddressesState.Error -> throw Exception("Failed to load Shipping Addresses")
                        }
                    )
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            showShippingAddressDialog(confirmCallback = { address ->
                viewModel.insertNewAddress(address)
            }, deleteCallback = { }, ActionType.INSERT, ShippingAddressModel(0))
        }
    }

    enum class ActionType {
        UPDATE, INSERT
    }

    private fun showShippingAddressDialog(
        confirmCallback: (ShippingAddressModel) -> Unit,
        deleteCallback: (ShippingAddressModel) -> Unit,
        type: ActionType,
        itemToUpdate: ShippingAddressModel
    ) {
        val dialog = AlertDialog.Builder(requireContext())
        val bind = DialogShippingAddressesBinding.inflate(layoutInflater)
        dialog.apply {
            var id = 0
            if (type == ActionType.UPDATE) {
                id = itemToUpdate.id
                bind.etName.setText(itemToUpdate.name)
                bind.etStreet.setText(itemToUpdate.street)
                bind.etPostalCode.setText(itemToUpdate.zip)
                bind.etCity.setText(itemToUpdate.city)
                bind.etProvince.setText(itemToUpdate.province)
                bind.etCountry.setText(itemToUpdate.country)
                setNeutralButton(getString(R.string.delete)) { _, _ ->
                    showConfirmDeleteDialog(deleteCallback = { deleteCallback(itemToUpdate) })
                }
            }
            setPositiveButton(getString(R.string.ok)) { _, _ ->
                confirmCallback(
                    ShippingAddressModel(
                        id,
                        bind.etName.text.toString(),
                        bind.etStreet.text.toString(),
                        bind.etCity.text.toString(),
                        bind.etPostalCode.text.toString(),
                        bind.etProvince.text.toString(),
                        bind.etCountry.text.toString()
                    )
                )
            }
            setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
        }
        dialog.setView(bind.root).create().show()
    }

    private fun showConfirmDeleteDialog(deleteCallback: () -> Unit) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.apply {
            setTitle(getString(R.string.do_delete_confirm))
            setPositiveButton(getString(R.string.ok)) { _, _ -> deleteCallback() }
            setNegativeButton(getString(R.string.cancel)) { _, _ -> }
        }
        dialog.create().show()
    }
}