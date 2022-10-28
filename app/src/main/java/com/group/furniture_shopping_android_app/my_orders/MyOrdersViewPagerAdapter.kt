package com.group.furniture_shopping_android_app.my_orders

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.group.furniture_shopping_android_app.R

class MyOrdersViewPagerAdapter(val fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return MyOrdersTabFragment.newInstance(fragment.resources.getStringArray(R.array.my_order_tabs_titles)[position])
    }
}