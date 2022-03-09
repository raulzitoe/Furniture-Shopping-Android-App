package com.group.furniture_shopping_android_app.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.ProductModel
import com.group.furniture_shopping_android_app.databinding.ItemProductBinding

class HomeAdapter () : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private lateinit var binding: ItemProductBinding
    var productList: ArrayList<ProductModel> = arrayListOf()

    class HomeViewHolder(val binding: ItemProductBinding, val productList: ArrayList<ProductModel>) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val product = productList[position]
            binding.productName.text = product.name
            binding.productPrice.text = product.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, productList)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int {
        return productList.size
    }

}