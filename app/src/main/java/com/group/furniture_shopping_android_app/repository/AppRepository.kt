package com.group.furniture_shopping_android_app.repository

import kotlinx.coroutines.flow.Flow
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

    fun gelAllCartItems(): Flow<List<CartModel>> {
        return database.getAllCartItems()
    }

    fun getAllFavoritesItems(): Flow<List<FavoritesModel>> {
        return database.getAllFavoritesItems()
    }

    fun gelAllOrderItems(): Flow<List<OrderModel>> {
        return database.getAllOrderItems()
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

    suspend fun updateItemFromCart(cartItem: CartModel) {
        database.updateItemFromCart(cartItem)
    }

}