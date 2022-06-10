package com.group.furniture_shopping_android_app.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductDetailsViewModelFactory (private val productId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel(productId) as T
    }
}