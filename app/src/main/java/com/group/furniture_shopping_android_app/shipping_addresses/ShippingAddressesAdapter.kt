package com.group.furniture_shopping_android_app.shipping_addresses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.ItemShippingAddressesBinding
import com.group.furniture_shopping_android_app.repository.ShippingAddressModel

class ShippingAddressesAdapter() :
    RecyclerView.Adapter<ShippingAddressesAdapter.ShippingAddressesViewHolder>() {
    private lateinit var binding: ItemShippingAddressesBinding
    var shippingAddressesList: List<ShippingAddressModel> = emptyList()

    inner class ShippingAddressesViewHolder(val binding: ItemShippingAddressesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = shippingAddressesList[position]
            binding.shippingName.text = item.name
            binding.shippingAddress.apply {
                text = context.getString(R.string.full_address, item.street, item.zip, item.city, item.province, item.country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingAddressesViewHolder {
        binding = ItemShippingAddressesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShippingAddressesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShippingAddressesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return shippingAddressesList.size
    }
}