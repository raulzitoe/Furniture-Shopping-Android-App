package com.group.furniture_shopping_android_app.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {

    //Cart Operations
    @Insert
    suspend fun insertToCart(cartItem: CartModel)

    @Delete
    suspend fun removeFromCart(cartItem: CartModel)

    @Update
    suspend fun updateItemFromCart(cartItem: CartModel)

    @Query("Select * from cart_table")
    fun getAllCartItems(): Flow<List<CartModel>>

    @Query("SELECT EXISTS(SELECT 1 FROM cart_table WHERE productId = :itemId)")
    suspend fun isOnCartDatabase(itemId: Int) : Boolean


    //Orders Operations
    @Insert
    suspend fun insertToOrders(orderItem: OrderModel)

    @Delete
    suspend fun removeFromOrders(orderItem: OrderModel)

    @Query("Select * from order_table")
    fun getAllOrderItems(): Flow<List<OrderModel>>

    @Query("Select COUNT(id) FROM order_table")
    fun getOrdersQuantity(): Flow<Int>


    //Shipping Addresses Operations
    @Insert
    suspend fun insertToShippingAddresses(shippingItem: ShippingAddressModel)

    @Delete
    suspend fun removeFromShipping(shippingItem: ShippingAddressModel)

    @Update
    suspend fun updateShippingAddress(shippingItem: ShippingAddressModel)

    @Query("Select * from shipping_addresses_table")
    fun getAllShippingItems(): Flow<List<ShippingAddressModel>>

    @Query("Select COUNT(id) FROM shipping_addresses_table")
    fun getShippingAddressesQuantity(): Flow<Int>


    //Favorites Operations
    @Insert
    suspend fun insertToFavorites(favoritesItem: FavoritesModel)

    @Delete
    suspend fun removeFromFavorites(favoritesItem: FavoritesModel)

    @Query("Select * from favorites_table")
    fun getAllFavoritesItems(): Flow<List<FavoritesModel>>
}