package com.group.furniture_shopping_android_app.my_cart

import com.group.furniture_shopping_android_app.ProductModel

sealed class MyCartViewState {
    object Loading : MyCartViewState()
    data class Success(val myCartList: ArrayList<ProductModel>) : MyCartViewState()
    data class Error(val error: Exception) : MyCartViewState()
}