package com.group.furniture_shopping_android_app.shipping_addresses

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ShippingAddressesViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShippingAddressesViewModel(context) as T
    }
}