package com.group.furniture_shopping_android_app.my_cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.furniture_shopping_android_app.repository.CartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCartViewModel @Inject constructor(private val repository: MyCartRepository) : ViewModel() {

    private val _viewState: MutableLiveData<MyCartViewState> = MutableLiveData()
    val viewState: LiveData<MyCartViewState> = _viewState

    var cartList: Flow<List<CartModel>> = MutableStateFlow(emptyList())

    init {
        getCartList()
    }

    fun getCartList() {
        cartList = repository.gelAllCartItems()
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