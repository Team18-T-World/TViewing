package com.tworld.tviewing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.yourmediaapp_18.myVideo.MyVideoFragment
import com.tworld.tviewing.home.HomeFragment
import com.tworld.tviewing.search.SearchFragment

class TabPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private var fragments = arrayOf<Fragment>(HomeFragment(), SearchFragment(), MyVideoFragment())

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}