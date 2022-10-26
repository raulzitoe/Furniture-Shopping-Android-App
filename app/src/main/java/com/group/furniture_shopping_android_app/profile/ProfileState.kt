package com.group.furniture_shopping_android_app.profile

sealed class ProfileState {
    data class Success(val data: ProfileStateData) : ProfileState()
    data class Error(val exception: Throwable):ProfileState()
}