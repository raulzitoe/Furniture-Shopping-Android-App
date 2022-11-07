package com.group.furniture_shopping_android_app.login_signup

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class NewUserModel(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null
)
