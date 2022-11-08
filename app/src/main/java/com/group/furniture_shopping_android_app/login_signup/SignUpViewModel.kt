package com.group.furniture_shopping_android_app.login_signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {

    private val database = Firebase.database.reference
    private lateinit var auth: FirebaseAuth

    fun createNewUser(name: String, email: String, password: String) {

        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Firebase", "createUserWithEmail:success")
                    val user = auth.currentUser
                    if (user != null) {
                        database.child("users").child(user.uid).setValue(UserModel(name, email))
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Firebase", "createUserWithEmail:failure", task.exception)
                }
            }




    }
}