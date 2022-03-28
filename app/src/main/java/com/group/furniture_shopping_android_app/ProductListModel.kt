package com.group.furniture_shopping_android_app

import com.google.gson.annotations.SerializedName

data class ProductListModel (
    @SerializedName("productList", alternate = ["myCartList", "favoritesList"])
    var productList: ArrayList<ProductModel> = arrayListOf()
        )