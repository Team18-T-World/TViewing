package com.tworld.tviewing.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tviewing.databinding.MainSubjectItemBinding

class MainSubjectAdapter : RecyclerView.Adapter<MainSubjectAdapter.MainSubjectViewHolder>() {

    class MainSubjectViewHolder(private val binding: MainSubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainSubjectViewHolder {
        return MainSubjectViewHolder()
    }

    override fun onBindViewHolder(holder: MainSubjectViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }
}