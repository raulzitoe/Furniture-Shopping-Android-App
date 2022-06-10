package com.group.furniture_shopping_android_app.my_cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.group.furniture_shopping_android_app.ProductListModel
import com.group.furniture_shopping_android_app.R

class MyCartViewModel (private val appContext: Application) : AndroidViewModel (appContext) {

    private val _viewState: MutableLiveData<MyCartViewState> = MutableLiveData()
    val viewState: LiveData<MyCartViewState> = _viewState

    init {
        getProductList()
    }

    fun getProductList() {
        val json = appContext.resources.openRawResource(R.raw.my_cart).bufferedReader().use{ it.readText() }
        val data = Gson().fromJson(json, ProductListModel::class.java)
        _viewState.value =  MyCartViewState.Success(data.productList)
    }
}