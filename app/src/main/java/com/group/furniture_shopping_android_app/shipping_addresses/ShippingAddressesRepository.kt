package com.group.furniture_shopping_android_app.shipping_addresses

import android.content.Context
import com.google.gson.Gson
import com.group.furniture_shopping_android_app.R

class ShippingAddressesRepository {
    fun getShippingAddresses(context: Context) : ShippingAddressesListModel {
        val json = context.resources.openRawResource(R.raw.shipping_addresses).bufferedReader().use{ it.readText() }
        return Gson().fromJson(json, ShippingAddressesListModel::class.java)
    }
}