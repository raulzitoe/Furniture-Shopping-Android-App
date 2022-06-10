package com.group.furniture_shopping_android_app.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel() as T
    }
}