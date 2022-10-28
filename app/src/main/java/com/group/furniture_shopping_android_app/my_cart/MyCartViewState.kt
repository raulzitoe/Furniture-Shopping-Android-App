package com.group.furniture_shopping_android_app.my_cart

import com.group.furniture_shopping_android_app.repository.CartModel
import kotlinx.coroutines.flow.Flow

sealed class MyCartViewState {
    object Loading : MyCartViewState()
    data class Success(val myCartList: Flow<List<CartModel>>) : MyCartViewState()
    data class Error(val error: Exception) : MyCartViewState()
}