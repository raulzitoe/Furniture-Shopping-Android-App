package com.group.furniture_shopping_android_app.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {

    @Insert
    suspend fun insertToCart(cartItem: CartModel)

    @Insert
    suspend fun insertToOrders(orderItem: OrderModel)

    @Insert
    suspend fun insertToFavorites(favoritesItem: FavoritesModel)

    @Query("Select * from cart_table")
    fun getAllCartItems(): Flow<List<CartModel>>

    @Query("Select * from order_table")
    fun getAllOrderItems(): Flow<List<OrderModel>>

    @Query("Select * from favorites_table")
    fun getAllFavoritesItems(): Flow<List<FavoritesModel>>

    @Delete
    suspend fun removeFromCart(cartItem: CartModel)

    @Delete
    suspend fun removeFromOrders(orderItem: OrderModel)

    @Delete
    suspend fun removeFromFavorites(favoritesItem: FavoritesModel)

    @Update
    suspend fun updateItemFromCart(cartItem: CartModel)

}