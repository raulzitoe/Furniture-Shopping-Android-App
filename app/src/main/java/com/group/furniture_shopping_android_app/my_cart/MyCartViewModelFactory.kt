package com.group.furniture_shopping_android_app.my_cart

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyCartViewModelFactory(val context: Context?) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyCartViewModel(context) as T
    }
}