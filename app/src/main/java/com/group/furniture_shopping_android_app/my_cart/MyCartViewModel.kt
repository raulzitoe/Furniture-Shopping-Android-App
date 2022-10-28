package com.group.furniture_shopping_android_app.my_cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.furniture_shopping_android_app.repository.CartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCartViewModel @Inject constructor(private val repository: MyCartRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<MyCartViewState> = MutableStateFlow(
        MyCartViewState.Success(emptyList())
    )
    val uiState: StateFlow<MyCartViewState> = _uiState

    init {
        collectCartList()
    }

    private fun collectCartList() {
        if (_uiState.value is MyCartViewState.Success) {
            viewModelScope.launch {
                repository.gelAllCartItems().collect {
                    _uiState.value = MyCartViewState.Success(it)
                }
            }
        }
    }

    fun removeItemFromCart(cartItem: CartModel) {
        viewModelScope.launch {
            repository.removeFromCart(cartItem)
        }
    }

    fun updateItemFromCart(cartItem: CartModel) {
        viewModelScope.launch {
            repository.updateItemFromCart(cartItem)
        }
    }
}