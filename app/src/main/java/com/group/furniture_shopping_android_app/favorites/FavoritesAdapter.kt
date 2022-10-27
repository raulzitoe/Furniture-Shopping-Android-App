package com.group.furniture_shopping_android_app.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.group.furniture_shopping_android_app.databinding.ItemFavoritesBinding
import com.group.furniture_shopping_android_app.repository.FavoritesModel
import io.github.rosariopfernandes.firecoil.load

class FavoritesAdapter(
    val listener: FavoritesRecyclerListener
) :
    ListAdapter<FavoritesModel, FavoritesAdapter.FavoritesViewHolder>(
        AsyncDifferConfig.Builder(DiffCallback()).build()) {
    private lateinit var binding: ItemFavoritesBinding

    private class DiffCallback : DiffUtil.ItemCallback<FavoritesModel>() {
        override fun areItemsTheSame(
            oldItem: FavoritesModel,
            newItem: FavoritesModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavoritesModel,
            newItem: FavoritesModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class FavoritesViewHolder(
        val binding: ItemFavoritesBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = currentList[position]
            binding.tvFavProductName.text = item.name
            binding.tvFavPrice.text = item.price.toString()
            val storageRef = FirebaseStorage.getInstance().reference.child(item.image)
            binding.favProductImage.load(storageRef)
            binding.btnRemoveFromFavorite.setOnClickListener {
                listener.removeItemFromFavorites(currentList[position])
            }
        }
    }

    interface FavoritesRecyclerListener {
        fun removeItemFromFavorites(favoritesItem: FavoritesModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        binding = ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}