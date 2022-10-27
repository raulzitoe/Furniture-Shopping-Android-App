package com.group.furniture_shopping_android_app.shipping_addresses

import com.group.furniture_shopping_android_app.repository.AppDao
import com.group.furniture_shopping_android_app.repository.ShippingAddressModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ShippingAddressesRepository @Inject constructor(private val database: AppDao) {

    suspend fun insertToShipping(shippingItem: ShippingAddressModel) {
        database.insertToShippingAddresses(shippingItem)
    }

    suspend fun removeFromShipping(shippingItem: ShippingAddressModel) {
        database.removeFromShipping(shippingItem)
    }

    suspend fun updateShippingAddress(shippingItem: ShippingAddressModel) {
        database.updateShippingAddress(shippingItem)
    }

    fun getAllShippingAddresses(): Flow<List<ShippingAddressModel>> {
        return database.getAllShippingItems()
    }
}