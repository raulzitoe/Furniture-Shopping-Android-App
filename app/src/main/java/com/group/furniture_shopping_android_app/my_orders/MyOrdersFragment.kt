package com.group.furniture_shopping_android_app.my_orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.group.furniture_shopping_android_app.MainActivity
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentMyOrdersBinding


class MyOrdersFragment : Fragment() {
    private lateinit var binding: FragmentMyOrdersBinding
    private lateinit var myOrdersViewPagerAdapter: MyOrdersViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyOrdersBinding.inflate(layoutInflater)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.myOrdersTabLayout
        val tabTitles = resources.getStringArray(R.array.my_order_tabs_titles)
        val myActivity = (activity as AppCompatActivity)

        myActivity.setSupportActionBar(binding.topAppBarMyOrders)
        myActivity.supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.topAppBarMyOrders.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }


        myOrdersViewPagerAdapter = MyOrdersViewPagerAdapter(this)
        viewPager = binding.myOrdersViewpager
        viewPager.adapter = myOrdersViewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
    }

}