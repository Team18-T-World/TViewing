package com.tworld.tviewing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tviewing.R
import com.example.tviewing.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tabLayout = binding.mainTabLayout
        val viewPager = binding.frameLayout
        viewPager.isUserInputEnabled = false
        // adapter 준비 및 연결
        val adapter = TabPagerAdapter(this)
        viewPager.adapter = adapter

        // TabLayout, ViewPager 연결
        TabLayoutMediator(
            tabLayout, viewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_home)
                1 -> tab.setIcon(R.drawable.ic_search)
                2 -> tab.setIcon(R.drawable.ic_user)
            }
        }.attach()
    }

    fun setFragment(Fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, Fragment)
            .commit()
    }


}