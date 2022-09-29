package com.group.furniture_shopping_android_app

data class ProductModel (
    var id: Int = 0,
    var name: String = "",
    var price: String = "",
    var category: String = "",
    var image: String = "",
    var description: String = "",
    var rating: Float = 0.0f,
    var reviewsCount: Int = 0,
    var localImagePath: String = "",
    var quantity: Int = 1
        )