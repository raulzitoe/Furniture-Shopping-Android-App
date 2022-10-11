package com.group.furniture_shopping_android_app.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    var productFilterList: ArrayList<HomeFilter> = arrayListOf()

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            binding = FragmentHomeBinding.inflate(layoutInflater)
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        (activity as AppCompatActivity).setSupportActionBar(binding.homeTopAppBar)
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.top_app_bar_home, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.cart -> {
                        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_myCartFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val viewStateObserver = Observer<HomeViewState> { viewState ->
            when(viewState){
                is HomeViewState.Success -> {(binding.recyclerHomeProducts.adapter as HomeAdapter).productList =
                    viewState.productList
                (binding.recyclerHomeProducts.adapter as HomeAdapter).notifyDataSetChanged()}
                else -> {}
            }
        }
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)

        with(binding.recyclerHomeProducts) {
            adapter = HomeAdapter(object: HomeAdapter.HomeItemListener{
                override fun itemClick(id: Int) {
                    val action = HomeFragmentDirections.actionHomeFragmentToProductFragment(id)
                    Navigation.findNavController(view).navigate(action)
                }
            })
            autoFitColumns(157)
        }
        toggleButtonClicked()
    }

    private fun RecyclerView.autoFitColumns(columnWidth: Int) {
        val displayMetrics = this.context.resources.displayMetrics
        val noOfColumns =
            ((displayMetrics.widthPixels / displayMetrics.density) / columnWidth).toInt()
        this.layoutManager = GridLayoutManager(this.context, noOfColumns)
    }

    private fun toggleButtonClicked() {
        binding.homeToggleButtonGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            Log.e("asd", "group: $group, checkedId: $checkedId, isChecked: $isChecked")
            val filter = when(checkedId){
                R.id.btn_popular -> HomeFilter.POPULAR
                R.id.btn_chair -> HomeFilter.CHAIR
                R.id.btn_table -> HomeFilter.TABLE
                R.id.btn_armchair -> HomeFilter.ARMCHAIR
                R.id.btn_bed -> HomeFilter.BED
                R.id.btn_lamp -> HomeFilter.LAMP
                else -> null
            }

            filter?.let {
                if(isChecked) productFilterList.add(it) else productFilterList.remove(it)
            }
            (binding.recyclerHomeProducts.adapter as HomeAdapter).setFilters(productFilterList)
            (binding.recyclerHomeProducts.adapter as HomeAdapter).notifyDataSetChanged()

        }
    }
}