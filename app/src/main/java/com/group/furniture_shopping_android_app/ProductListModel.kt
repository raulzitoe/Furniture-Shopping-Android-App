package com.group.furniture_shopping_android_app

import com.google.firebase.database.PropertyName

data class ProductListModel (
    @get:PropertyName("productList") @set:PropertyName("productList")
    var products: ArrayList<ProductModel> = arrayListOf()
        )