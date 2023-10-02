package com.tworld.tviewing.videoDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tviewing.R
import com.example.tviewing.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setFragment(VideoDetailFragment())
    }

    fun setFragment(Fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.lay_frag, Fragment)
            .commit()
    }

}