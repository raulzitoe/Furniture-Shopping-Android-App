package com.group.furniture_shopping_android_app.product_details

import com.group.furniture_shopping_android_app.repository.AppDao
import com.group.furniture_shopping_android_app.repository.CartModel
import com.group.furniture_shopping_android_app.repository.FavoritesModel
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(private val database: AppDao) {

    suspend fun insertToFavorites(favoritesItem: FavoritesModel) {
        database.insertToFavorites(favoritesItem)
    }

    suspend fun insertToCart(cartItem: CartModel) {
        database.insertToCart(cartItem)
    }

    suspend fun isOnCartDatabase(id: Int) : Boolean {
        return database.isOnCartDatabase(id)
    }

}