package com.group.furniture_shopping_android_app.profile

import com.group.furniture_shopping_android_app.repository.AppDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val database: AppDao) {
    fun getShippingAddressesQuantity(): Flow<Int> {
        return database.getShippingAddressesQuantity()
    }
}