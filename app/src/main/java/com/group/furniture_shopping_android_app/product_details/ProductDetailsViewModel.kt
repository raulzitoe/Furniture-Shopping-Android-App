package com.group.furniture_shopping_android_app.product_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.group.furniture_shopping_android_app.ProductModel
import com.group.furniture_shopping_android_app.repository.CartModel
import com.group.furniture_shopping_android_app.repository.FavoritesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val repository: ProductDetailsRepository
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

    fun changeQuantity(newQuantity: Int) {
        (viewState.value as? ProductDetailsViewState.Success)?.let {
            it.data.quantity = newQuantity
        }
    }

    fun addToCart() {
        (viewState.value as? ProductDetailsViewState.Success)?.let {
            val itemId = (viewState.value as ProductDetailsViewState.Success).data.id
            viewModelScope.launch {
                if (!repository.isOnCartDatabase(itemId)) {
                    val productInfo = it.data
                    repository.insertToCart(
                        CartModel(
                            productId = productInfo.id,
                            name = productInfo.name,
                            price = productInfo.price.toDouble(),
                            category = productInfo.category,
                            image = productInfo.image,
                            quantity = productInfo.quantity
                        )
                    )
                }
            }
        }
    }

    fun addToFavorites() {
        (viewState.value as? ProductDetailsViewState.Success)?.let {
            val productInfo = it.data
            viewModelScope.launch {
                repository.insertToFavorites(
                    FavoritesModel(
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