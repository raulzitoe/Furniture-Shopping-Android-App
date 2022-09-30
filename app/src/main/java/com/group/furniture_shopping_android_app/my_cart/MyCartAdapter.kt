package com.group.furniture_shopping_android_app.my_cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.group.furniture_shopping_android_app.databinding.ItemMyCartBinding
import com.group.furniture_shopping_android_app.repository.CartModel
import io.github.rosariopfernandes.firecoil.load

class MyCartAdapter (val listener: CartRecyclerListener) :
    RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder>() {
    private lateinit var binding: ItemMyCartBinding
    var myCartList: List<CartModel> = arrayListOf()

    inner class MyCartViewHolder(val binding: ItemMyCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = myCartList[position]
            binding.tvCartProductName.text = item.name
            binding.tvCartPrice.text = item.price.toString()
            binding.fragmentMyCartQuantity.text = item.quantity.toString()
            val storageRef = FirebaseStorage.getInstance().reference.child(item.image)
            binding.cartProductImage.load(storageRef)
            binding.btnRemoveFromCart.setOnClickListener {
                listener.removeItemFromCart(item)
            }
            binding.btnPlus.setOnClickListener {
                myCartList[position].quantity += 1
                listener.updateItem(myCartList[position])
            }
            binding.btnMinus.setOnClickListener {
                if (item.quantity >= 2){
                    myCartList[position].quantity -= 1
                    listener.updateItem(myCartList[position])
                }
            }
        }
    }

    interface CartRecyclerListener {
        fun removeItemFromCart(cartItem: CartModel)
        fun updateItem(cartItem: CartModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartViewHolder {
        binding = ItemMyCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyCartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return myCartList.size
    }

}