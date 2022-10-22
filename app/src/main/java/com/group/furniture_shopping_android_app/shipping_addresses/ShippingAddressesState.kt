package com.group.furniture_shopping_android_app.shipping_addresses

import com.group.furniture_shopping_android_app.repository.ShippingAddressModel

sealed class ShippingAddressesState {
    data class Success(val shippingAddressesList: List<ShippingAddressModel>): ShippingAddressesState()
    data class Error(val exception: Throwable): ShippingAddressesState()
}
