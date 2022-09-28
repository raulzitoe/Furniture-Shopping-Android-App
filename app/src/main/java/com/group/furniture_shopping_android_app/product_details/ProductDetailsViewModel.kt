package com.group.furniture_shopping_android_app.product_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.group.furniture_shopping_android_app.ProductModel
import com.group.furniture_shopping_android_app.repository.AppRepository
import com.group.furniture_shopping_android_app.repository.CartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val appRepository: AppRepository
) : ViewModel() {
    val viewState: MutableLiveData<ProductDetailsViewState> = MutableLiveData()
    private lateinit var database: DatabaseReference

    init {
        getProductInfo()
    }

    private fun getProductInfo() {
        database = FirebaseDatabase.getInstance().reference.child("productInfo")
            .child(state.get<Int>("product_id").toString())
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.getValue(ProductModel::class.java)?.let { value ->
                    viewState.value = ProductDetailsViewState.Success(value)
                }

            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }

    fun addToCart() {
        (viewState.value as? ProductDetailsViewState.Success)?.let {
            val productInfo = it.data
            viewModelScope.launch {
                appRepository.insertToCart(
                    CartModel(
                        id = productInfo.id,
                        name = productInfo.name,
                        price = productInfo.price.toDouble(),
                        category = productInfo.category,
                        image = productInfo.image
                    )
                )
            }
        }
    }
}