package com.group.furniture_shopping_android_app.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
    val viewState: LiveData<HomeViewState> = _viewState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.postValue(
                try {
                    HomeViewState.Success(HomeRepository().getProductList())
                } catch (e: Exception) {
                    HomeViewState.Error(e)
                })
        }
    }
}
