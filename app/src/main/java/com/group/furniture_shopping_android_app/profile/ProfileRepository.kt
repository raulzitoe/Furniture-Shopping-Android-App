package com.group.furniture_shopping_android_app.profile

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.group.furniture_shopping_android_app.login_signup.UserModel
import com.group.furniture_shopping_android_app.repository.AppDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ProfileRepository @Inject constructor(private val database: AppDao) {

    private val firebaseDatabase = Firebase.database.reference.child("users")

    fun getShippingAddressesQuantity(): Flow<Int> {
        return database.getShippingAddressesQuantity()
    }

    suspend fun getProfileData(): UserModel {
        return suspendCoroutine { continuation ->
            Firebase.auth.currentUser?.let {
                firebaseDatabase.child(it.uid)
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            snapshot.getValue(UserModel::class.java)?.let { user ->
                                continuation.resume(user)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            
                        }
                    })
            }
        }
    }
}