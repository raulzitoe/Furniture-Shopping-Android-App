package com.group.furniture_shopping_android_app.home

import com.group.furniture_shopping_android_app.ProductModel

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Success(val productList: ArrayList<ProductModel>) : HomeViewState()
    data class Error(val error: Exception) : HomeViewState()
}