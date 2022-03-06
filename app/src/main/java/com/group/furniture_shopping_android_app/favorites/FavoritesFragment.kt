package com.group.furniture_shopping_android_app.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentFavoritesBinding
import com.group.furniture_shopping_android_app.home.HomeAdapter
import com.group.furniture_shopping_android_app.home.HomeModel

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding

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
        val favoritesList = FavoritesModel(context).getFavoritesList()
        val recyclerView: RecyclerView = binding.recyclerFavorites
        recyclerView.adapter = FavoritesAdapter(favoritesList)
    }
}