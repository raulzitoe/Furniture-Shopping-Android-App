package com.group.furniture_shopping_android_app.login_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.group.furniture_shopping_android_app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_sign_up)
    }
}