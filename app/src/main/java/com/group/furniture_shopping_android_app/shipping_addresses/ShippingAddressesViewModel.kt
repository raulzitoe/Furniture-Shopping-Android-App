package com.group.furniture_shopping_android_app.shipping_addresses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.furniture_shopping_android_app.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShippingAddressesViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {
    private val _uiState = MutableStateFlow(ShippingAddressesState.Success(emptyList()))
    val uiState: StateFlow<ShippingAddressesState> = _uiState

    init {
        viewModelScope.launch {
            repository.getAllShippingAddresses().collect {
                _uiState.value = ShippingAddressesState.Success(it)
            }
        }
    }
}