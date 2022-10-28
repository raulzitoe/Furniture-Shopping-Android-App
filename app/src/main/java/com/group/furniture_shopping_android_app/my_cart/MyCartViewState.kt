package com.group.furniture_shopping_android_app.my_cart

import com.group.furniture_shopping_android_app.repository.CartModel

sealed class MyCartViewState {
    object Loading : MyCartViewState()
    data class Success(val myCartList: List<CartModel>) : MyCartViewState()
    data class Error(val error: Exception) : MyCartViewState()
}