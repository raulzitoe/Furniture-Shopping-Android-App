package com.group.furniture_shopping_android_app.shipping_addresses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.databinding.ItemShippingAddressesBinding

class ShippingAddressesAdapter(var data: List<ShippingAddressesModel>) :
    RecyclerView.Adapter<ShippingAddressesAdapter.ShippingAddressesViewHolder>() {
    private lateinit var binding: ItemShippingAddressesBinding

    inner class ShippingAddressesViewHolder() :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val myAddress = data[position]
            val street = myAddress.shipping_address
            val zipcode = myAddress.shipping_zipcode
            val country = myAddress.shipping_country
            val city = myAddress.shipping_city
            val province = myAddress.shipping_province
            binding.shippingName.text = myAddress.shipping_name
            binding.shippingAddress.text = "$street, $zipcode, $city, $province, $country"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingAddressesViewHolder {
        binding = ItemShippingAddressesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShippingAddressesViewHolder()
    }

    override fun onBindViewHolder(holder: ShippingAddressesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}