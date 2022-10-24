package com.group.furniture_shopping_android_app.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val database: AppDao
) {

    suspend fun insertToCart(cartItem: CartModel) {
        database.insertToCart(cartItem)
    }

    suspend fun insertToOrders(orderItem: OrderModel) {
        database.insertToOrders(orderItem)
    }

    suspend fun insertToFavorites(favoritesItem: FavoritesModel) {
        database.insertToFavorites(favoritesItem)
    }

    suspend fun insertToShipping(shippingItem: ShippingAddressModel) {
        database.insertToShippingAddresses(shippingItem)
    }

    fun gelAllCartItems(): Flow<List<CartModel>> {
        return database.getAllCartItems()
    }

    fun getAllFavoritesItems(): Flow<List<FavoritesModel>> {
        return database.getAllFavoritesItems()
    }

    fun getAllOrderItems(): Flow<List<OrderModel>> {
        return database.getAllOrderItems()
    }

    fun getAllShippingAddresses(): Flow<List<ShippingAddressModel>> {
        return database.getAllShippingItems()
    }

    suspend fun removeFromCart(cartItem: CartModel) {
        database.removeFromCart(cartItem)
    }

    suspend fun removeFromOrders(orderItem: OrderModel) {
        database.removeFromOrders(orderItem)
    }

    suspend fun removeFromFavorites(favoritesItem: FavoritesModel) {
        database.removeFromFavorites(favoritesItem)
    }

    suspend fun removeFromShipping(shippingItem: ShippingAddressModel) {
        database.removeFromShipping(shippingItem)
    }

    suspend fun updateItemFromCart(cartItem: CartModel) {
        database.updateItemFromCart(cartItem)
    }

    suspend fun updateShippingAddress(shippingItem: ShippingAddressModel) {
        database.updateShippingAddress(shippingItem)
    }

    fun getShippingAddressesQuantity(): Flow<Int> {
        return database.getShippingAddressesQuantity()
    }

}