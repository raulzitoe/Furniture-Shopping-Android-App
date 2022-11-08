package com.group.furniture_shopping_android_app.profile


data class ProfileStateData(
    val name: String = "",
    val email: String = "",
    val ordersQuantity: Int = 0,
    val shippingAddressesQuantity: Int = 0,
    val cardsQuantity: Int = 0,
    val reviewsQuantity: Int = 0
)