package com.group.furniture_shopping_android_app.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.group.furniture_shopping_android_app.databinding.ItemFavoritesBinding
import com.group.furniture_shopping_android_app.repository.FavoritesModel
import io.github.rosariopfernandes.firecoil.load

class FavoritesAdapter(
    val listener: FavoritesRecyclerListener
) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    private lateinit var binding: ItemFavoritesBinding
    var favoritesList: List<FavoritesModel> = arrayListOf()

    inner class FavoritesViewHolder(
        val binding: ItemFavoritesBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = favoritesList[position]
            binding.tvFavProductName.text = item.name
            binding.tvFavPrice.text = item.price.toString()
            val storageRef = FirebaseStorage.getInstance().reference.child(item.image)
            binding.favProductImage.load(storageRef)
            binding.btnRemoveFromFavorite.setOnClickListener {
                listener.removeItemFromFavorites(favoritesList[position])
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
        return favoritesList.size
    }

}