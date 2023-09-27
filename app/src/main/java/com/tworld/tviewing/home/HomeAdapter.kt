package com.tworld.tviewing.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tviewing.databinding.MainSubjectItemBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(private val binding: MainSubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(MainSubjectItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }
}