package com.group.furniture_shopping_android_app.home

import android.content.Context
import com.group.furniture_shopping_android_app.R

class HomeModel(val context: Context?) {
    fun getProductList(): Array<String> {
        return context?.resources?.getStringArray(R.array.product_array) ?: arrayOf()
    }
}
