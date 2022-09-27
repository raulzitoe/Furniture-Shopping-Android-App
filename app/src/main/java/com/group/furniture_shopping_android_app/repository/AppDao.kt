package com.group.furniture_shopping_android_app.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {

    @Insert
    suspend fun insertToCart(cartItem: CartModel)

    @Insert
    suspend fun insertToOrders(orderItem: OrderModel)

    @Query("Select * from cart_table")
    fun gelAllCartItems(): Flow<List<CartModel>>

    @Query("Select * from order_table")
    fun gelAllOrderItems(): Flow<List<OrderModel>>

}