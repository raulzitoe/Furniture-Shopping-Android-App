package com.group.furniture_shopping_android_app.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.group.furniture_shopping_android_app.ProductListModel
import com.group.furniture_shopping_android_app.R


class HomeViewModel (val context: Context?) : ViewModel () {

    private val _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
    val viewState: LiveData<HomeViewState> = _viewState

    init {
        getProductList()
    }



    fun getProductList() {
        val json = context?.resources?.openRawResource(R.raw.products)?.bufferedReader().use{it?.readText()}
        val data = Gson().fromJson(json, ProductListModel::class.java)
        _viewState.value =  HomeViewState(data.productList)
    }
}
