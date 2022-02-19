package com.group.furniture_shopping_android_app

import android.content.Context

class HomeModel(val context: Context?) {
    fun getProductList(): Array<String> {
        return context?.resources?.getStringArray(R.array.product_array) ?: arrayOf()
    }
}
