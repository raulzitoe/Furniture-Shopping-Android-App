package com.group.furniture_shopping_android_app.my_cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.databinding.FragmentMyCartBinding


class MyCartFragment : Fragment() {

    private lateinit var binding: FragmentMyCartBinding
    private val viewModel: MyCartViewModel by viewModels {
        MyCartViewModelFactory(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerMyCart.adapter = MyCartAdapter()
        val viewStateObserver = Observer<MyCartViewState> { viewState ->
            when(viewState){
                is MyCartViewState.Success -> {(binding.recyclerMyCart.adapter as MyCartAdapter).myCartList =
                    viewState.myCartList
                    (binding.recyclerMyCart.adapter as MyCartAdapter).notifyDataSetChanged()}
            }
        }
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
    }

}