package com.group.furniture_shopping_android_app.favorites

import com.group.furniture_shopping_android_app.repository.AppDao
import com.group.furniture_shopping_android_app.repository.FavoritesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val database: AppDao) {


    suspend fun removeFromFavorites(favoritesItem: FavoritesModel) {
        database.removeFromFavorites(favoritesItem)
    }

    fun getAllFavoritesItems(): Flow<List<FavoritesModel>> {
        return database.getAllFavoritesItems()
    }
}