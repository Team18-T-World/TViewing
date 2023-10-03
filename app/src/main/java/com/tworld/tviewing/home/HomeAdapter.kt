package com.tworld.tviewing.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tviewing.databinding.MainMenuItemBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(val binding: MainMenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(MainMenuItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        //mainsubject에 있는 어댑터를 가져와서 연결

    }

    override fun getItemCount(): Int {
        return 0
    }
}