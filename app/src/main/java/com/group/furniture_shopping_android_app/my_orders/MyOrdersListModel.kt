package com.group.furniture_shopping_android_app.my_orders


data class MyOrdersListModel(
    var myOrdersList: List<MyOrdersModel> = listOf()
)

data class MyOrdersModel(
    var order_number: Int = 0,
    var order_date: String = "",
    var order_quantity: Int = 0,
    var order_total_amount: Float = 0.0f,
    var order_status: String = ""
)