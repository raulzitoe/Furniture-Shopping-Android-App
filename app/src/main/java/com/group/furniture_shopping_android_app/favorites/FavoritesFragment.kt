package com.group.furniture_shopping_android_app.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.group.furniture_shopping_android_app.databinding.FragmentFavoritesBinding
import com.group.furniture_shopping_android_app.repository.FavoritesModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Favorites"
        binding.recyclerFavorites.adapter = FavoritesAdapter(listener = object :FavoritesAdapter.FavoritesRecyclerListener {
            override fun removeItemFromFavorites(favoritesItem: FavoritesModel) {
                viewModel.removeFavoriteItem(favoritesItem)
            }
        } )


        lifecycleScope.launch {
            viewModel.favoritesList.collect{
                (binding.recyclerFavorites.adapter as FavoritesAdapter).submitList(it)
            }
        }
    }
}