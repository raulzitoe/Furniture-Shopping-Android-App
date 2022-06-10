package com.group.furniture_shopping_android_app.my_orders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyOrdersTabViewModel(appContext: Application) : AndroidViewModel(appContext) {
    private val _viewState: MutableLiveData<List<MyOrdersModel>> = MutableLiveData()
    val viewState: LiveData<List<MyOrdersModel>> = _viewState

    init {
        val repository = MyOrdersRepository()
        _viewState.value = repository.getMyOrders(appContext).myOrdersList
    }
}