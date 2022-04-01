package com.group.furniture_shopping_android_app.my_orders

import android.content.Context
import com.google.gson.Gson
import com.group.furniture_shopping_android_app.R

class MyOrdersRepository {

    fun getMyOrders(context: Context) : MyOrdersListModel{
        val json = context.resources?.openRawResource(R.raw.my_orders)?.bufferedReader().use{it?.readText()}
        return Gson().fromJson(json, MyOrdersListModel::class.java)
    }
}