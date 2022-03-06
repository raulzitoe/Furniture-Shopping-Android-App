package com.group.furniture_shopping_android_app.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.databinding.ItemFavoritesBinding

class FavoritesAdapter(private val favoritesList: Array<String>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    private lateinit var binding: ItemFavoritesBinding

    class FavoritesViewHolder(val binding: ItemFavoritesBinding, private val favoritesList: Array<String>) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.tvFavProductName.text = favoritesList[position]
            binding.tvFavPrice.text = "50.00"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        binding = ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding, favoritesList)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int {
        return favoritesList.size
    }

}