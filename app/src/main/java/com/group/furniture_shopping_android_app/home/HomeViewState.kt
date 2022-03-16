package com.group.furniture_shopping_android_app.home

import com.group.furniture_shopping_android_app.ProductModel
import com.group.furniture_shopping_android_app.product_details.ProductDetailsViewState

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Success(val productList: ArrayList<ProductModel>) : HomeViewState()
    data class Error(val error: Exception) : HomeViewState()
}