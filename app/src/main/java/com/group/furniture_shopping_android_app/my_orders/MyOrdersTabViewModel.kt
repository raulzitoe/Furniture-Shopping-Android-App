package com.group.furniture_shopping_android_app.my_orders

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyOrdersTabViewModel(context: Context) : ViewModel() {
    private val _viewState: MutableLiveData<List<MyOrdersModel>> = MutableLiveData()
    val viewState: LiveData<List<MyOrdersModel>> = _viewState

    init {
        val repository = MyOrdersRepository()
        _viewState.value = repository.getMyOrders(context).myOrdersList
    }
}