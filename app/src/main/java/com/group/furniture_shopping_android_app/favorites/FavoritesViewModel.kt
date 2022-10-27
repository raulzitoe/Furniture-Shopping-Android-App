package com.group.furniture_shopping_android_app.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.furniture_shopping_android_app.repository.FavoritesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(val repository: FavoritesRepository): ViewModel() {
    var favoritesList: Flow<List<FavoritesModel>> = MutableStateFlow(emptyList())

    init {
        getFavorites()
    }

    private fun getFavorites() {
        favoritesList = repository.getAllFavoritesItems()
    }

    fun removeFavoriteItem(favoritesItem: FavoritesModel) {
        viewModelScope.launch {
            repository.removeFromFavorites(favoritesItem)
        }
    }
}