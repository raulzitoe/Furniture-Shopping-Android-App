package com.group.furniture_shopping_android_app.shipping_addresses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.furniture_shopping_android_app.repository.ShippingAddressModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShippingAddressesViewModel @Inject constructor(private val repository: ShippingAddressesRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(ShippingAddressesState.Success(emptyList()))
    val uiState: StateFlow<ShippingAddressesState> = _uiState

    init {
        viewModelScope.launch {
            repository.getAllShippingAddresses().collect {
                _uiState.value = ShippingAddressesState.Success(it)
            }
        }
    }

    fun insertNewAddress(item: ShippingAddressModel) {
        viewModelScope.launch {
            repository.insertToShipping(item)
        }
    }

    fun deleteAddress(item: ShippingAddressModel) {
        viewModelScope.launch {
            repository.removeFromShipping(item)
        }
    }

    fun updateShippingAddress(item: ShippingAddressModel) {
        viewModelScope.launch {
            repository.updateShippingAddress(item)
        }
    }

    fun setDefaultAddress(item: ShippingAddressModel) {
        viewModelScope.launch {
            when (uiState.value) {
                is ShippingAddressesState.Success -> {
                    val allAddresses =
                        (uiState.value as ShippingAddressesState.Success).shippingAddressesList

                    for (address in allAddresses) {
                        if (address.id == item.id) {
                            repository.updateShippingAddress(address.copy(isDefaultAddress = true))
                        } else if (address.isDefaultAddress) {
                            repository.updateShippingAddress(address.copy(isDefaultAddress = false))
                        }
                    }
                }
                else -> {}
            }
        }
    }
}