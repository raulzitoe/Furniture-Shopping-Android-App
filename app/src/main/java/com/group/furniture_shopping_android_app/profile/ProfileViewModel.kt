package com.group.furniture_shopping_android_app.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.furniture_shopping_android_app.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val shippingAddressesQuantity: MutableStateFlow<Int> = MutableStateFlow(0)

    init {
        getShippingAddressesQuantity()
    }

    private fun getShippingAddressesQuantity() {
        viewModelScope.launch {
            repository.getShippingAddressesQuantity().collect{
                shippingAddressesQuantity.value = it
            }
        }
    }

}