package com.group.furniture_shopping_android_app.my_cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.ProductModel
import com.group.furniture_shopping_android_app.databinding.ItemMyCartBinding

class MyCartAdapter :
    RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder>() {
    private lateinit var binding: ItemMyCartBinding
    var myCartList: ArrayList<ProductModel> = arrayListOf()

    class MyCartViewHolder(val binding: ItemMyCartBinding, private val myCartList: ArrayList<ProductModel>) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = myCartList[position]
            binding.tvCartProductName.text = item.name
            binding.tvCartPrice.text = item.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartViewHolder {
        binding = ItemMyCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCartViewHolder(binding, myCartList)
    }

    override fun onBindViewHolder(holder: MyCartViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int {
        return myCartList.size
    }

}