package com.group.furniture_shopping_android_app.shipping_addresses

data class ShippingAddressesModel (
    var shipping_name: String = "",
    var shipping_address: String = "",
    var shipping_zipcode: String = "",
    var shipping_country: String = "",
    var shipping_province: String = "",
    var shipping_city: String = ""
        )

data class ShippingAddressesListModel (
    var shippingAddressesList: List<ShippingAddressesModel>
        )