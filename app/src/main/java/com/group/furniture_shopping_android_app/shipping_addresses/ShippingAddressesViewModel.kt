package com.group.furniture_shopping_android_app.shipping_addresses

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShippingAddressesViewModel(context: Context): ViewModel() {
    private val _viewState: MutableLiveData<List<ShippingAddressesModel>> = MutableLiveData()
    val viewState: LiveData<List<ShippingAddressesModel>> = _viewState

    init {
        val repository = ShippingAddressesRepository()
        _viewState.value = repository.getShippingAddresses(context).shippingAddressesList
    }
}