package com.group.furniture_shopping_android_app.product_details

import com.group.furniture_shopping_android_app.ProductModel

sealed class ProductDetailsViewState {
    object Loading: ProductDetailsViewState()
    data class Success(val data: ProductModel): ProductDetailsViewState()
    data class Error(val error: Exception): ProductDetailsViewState()
}
