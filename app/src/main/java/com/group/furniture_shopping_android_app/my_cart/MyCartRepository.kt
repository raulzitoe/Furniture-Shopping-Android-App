package com.group.furniture_shopping_android_app.my_cart

import com.group.furniture_shopping_android_app.repository.AppDao
import com.group.furniture_shopping_android_app.repository.CartModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyCartRepository @Inject constructor(private val database: AppDao) {

    fun gelAllCartItems(): Flow<List<CartModel>> {
        return database.getAllCartItems()
    }

    suspend fun removeFromCart(cartItem: CartModel) {
        database.removeFromCart(cartItem)
    }

    suspend fun updateItemFromCart(cartItem: CartModel) {
        database.updateItemFromCart(cartItem)
    }
}