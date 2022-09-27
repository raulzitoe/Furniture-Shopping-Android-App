package com.group.furniture_shopping_android_app.my_cart

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentMyCartBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MyCartFragment : Fragment() {

    private lateinit var binding: FragmentMyCartBinding
    private val viewModel: MyCartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyCartBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerMyCart.adapter = MyCartAdapter()
        val myActivity = (activity as AppCompatActivity)

        myActivity.setSupportActionBar(binding.topAppBarMyCart)
        myActivity.supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.topAppBarMyCart.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

//        val viewStateObserver = Observer<MyCartViewState> { viewState ->
//            when (viewState) {
//                is MyCartViewState.Success -> {
//                    (binding.recyclerMyCart.adapter as MyCartAdapter).myCartList =
//                        viewState.myCartList
//                    (binding.recyclerMyCart.adapter as MyCartAdapter).notifyDataSetChanged()
//                }
//                else -> {}
//            }
//        }
//        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        lifecycleScope.launch {
            viewModel.cartList.collect {
                (binding.recyclerMyCart.adapter as MyCartAdapter).myCartList = it
                (binding.recyclerMyCart.adapter as MyCartAdapter).notifyDataSetChanged()
            }
        }
    }
}