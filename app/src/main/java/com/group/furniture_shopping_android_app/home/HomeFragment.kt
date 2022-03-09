package com.group.furniture_shopping_android_app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels{
        HomeViewModelFactory(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewStateObserver = Observer<HomeViewState> {viewState ->
            (binding.recyclerHomeProducts.adapter as HomeAdapter).productList = viewState.productList
            (binding.recyclerHomeProducts.adapter as HomeAdapter).notifyDataSetChanged()
        }
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)




        activity?.title = "Home"
        with(binding.recyclerHomeProducts){
            adapter = HomeAdapter()
            autoFitColumns(157)
        }
    }

    private fun RecyclerView.autoFitColumns(columnWidth: Int) {
        val displayMetrics = this.context.resources.displayMetrics
        val noOfColumns = ((displayMetrics.widthPixels / displayMetrics.density) / columnWidth).toInt()
        this.layoutManager = GridLayoutManager(this.context, noOfColumns)
    }

}