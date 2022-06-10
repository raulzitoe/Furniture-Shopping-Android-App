package com.group.furniture_shopping_android_app.shipping_addresses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShippingAddressesViewModel(appContext: Application): AndroidViewModel(appContext) {
    private val _viewState: MutableLiveData<List<ShippingAddressesModel>> = MutableLiveData()
    val viewState: LiveData<List<ShippingAddressesModel>> = _viewState

    init {
        val repository = ShippingAddressesRepository()
        _viewState.value = repository.getShippingAddresses(appContext).shippingAddressesList
    }
}